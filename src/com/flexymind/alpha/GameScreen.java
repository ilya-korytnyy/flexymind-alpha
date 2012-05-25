package com.flexymind.alpha;

import android.app.Activity;
import android.os.Bundle;
import com.flexymind.alpha.Game.Game;
import com.flexymind.alpha.customviews.NoteBoard;

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

        Game game = new Game(noteBoard);
    }

    @Override
    protected void onStart() {
        super.onStart();    //To change body of overridden methods use File | Settings | File Templates.
        game.gameStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    protected void onResume() {
        super.onResume();    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    protected void onStop() {
        super.onStop();    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();    //To change body of overridden methods use File | Settings | File Templates.

    }
}