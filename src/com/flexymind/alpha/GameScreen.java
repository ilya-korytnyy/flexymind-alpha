package com.flexymind.alpha;

import android.app.Activity;
import android.os.Bundle;
import com.flexymind.alpha.customviews.NoteBoard;
import com.flexymind.alpha.customviews.PianoKeyboard;
import com.flexymind.alpha.game.Game;

public class GameScreen extends Activity {
    private Game game;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        // set orientation selected in the settings
        //setRequestedOrientation(savedInstanceState.getInt("orientation"));
        StaticResources.res = getResources();

        NoteBoard noteBoard = (NoteBoard)
                            findViewById(R.id.noteboard);

        PianoKeyboard pianoKeyboard = (PianoKeyboard)
                            findViewById(R.id.pianokeyboard);

        game = new Game(noteBoard, pianoKeyboard);
    }

    @Override
    protected void onStart() {
        super.onStart();
        game.gameStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}