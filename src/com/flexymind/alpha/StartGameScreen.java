package com.flexymind.alpha;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.flexymind.alpha.player.Note;
import com.flexymind.alpha.player.PianoPlayer;
import com.flexymind.alpha.startbutton.StartButton;

public class StartGameScreen extends Activity implements View.OnClickListener {

    private StartButton startButton;
    PianoPlayer play;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);

        startButton = (StartButton) findViewById(R.id.startbutton);
        startButton.setOnClickListener(this);

        StaticResources.res = getResources();
        play = new PianoPlayer(this, R.raw.gooses);
    }

    public void createGameScreen() {

        Intent intent = new Intent(this, GameScreen.class);
        startActivity(intent);
		finish();
    }


    @Override
    public void onClick(View view) {

        createGameScreen();
        playOwnSound();
    }


    public void playOwnSound() {

        Thread soundThread = new Thread(new Runnable() {
            @Override
            public void run() {
                play.play();
            }
        });
        soundThread.start();
    }

}