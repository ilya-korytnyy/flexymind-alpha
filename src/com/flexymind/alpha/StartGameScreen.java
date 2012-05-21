package com.flexymind.alpha;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import com.flexymind.alpha.player.PianoPlayer;
import com.flexymind.alpha.startbutton.StartButton;

public class StartGameScreen extends Activity implements View.OnClickListener {

    private StartButton startButton;
    private ImageButton settingsButton;
    private Bitmap      settingsBitmap;
    private RelativeLayout startScreen;

    private static final int SETTINGS_BUTTON = 1001;
    private PianoPlayer player;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);

        StaticResources.res = getResources();

        startScreen = (RelativeLayout) findViewById(R.id.startScreen);

        startButton = (StartButton) findViewById(R.id.startbutton);
        startButton.setOnClickListener(this);

        settingsButton = new ImageButton(this);
        settingsButton.setId(SETTINGS_BUTTON);

        startScreen.addView(settingsButton);

        settingsButton.setOnClickListener(this);
        setSizeSettingsButton();
    }

    private void setSizeSettingsButton() {
        settingsBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.settings);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        settingsBitmap = Bitmap.createScaledBitmap(settingsBitmap, metrics.heightPixels / 6, metrics.heightPixels / 6, true);

        settingsButton.setImageBitmap(settingsBitmap);

        player = PianoPlayer.getInstance(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.startbutton: {
                createGameScreen();
                playOwnSound();
                break;
            }
            case SETTINGS_BUTTON: {
                createSettingsScreen();
                break;
            }
        }

    }

    public void createGameScreen() {

        Intent intent = new Intent(this, GameScreen.class);
        startActivity(intent);
        finish();
    }

    public void playOwnSound() {

        Thread soundThread = new Thread(new Runnable() {
            @Override
            public void run() {
                player.play(R.raw.song);
            }
        });
        soundThread.start();
    }

    private void createSettingsScreen() {
        Intent intent = new Intent(this, Settings.class);

        startActivity(intent);
        finish();
    }

}