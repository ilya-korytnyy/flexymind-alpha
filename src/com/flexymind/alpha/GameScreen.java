package com.flexymind.alpha;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import com.flexymind.alpha.Game.Game;
import com.flexymind.alpha.customviews.NoteBoard;
import com.flexymind.alpha.startbutton.StartButtonView;

public class GameScreen extends Activity {

    private Game game;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        StaticResources.res = getResources();

        NoteBoard noteBoard = (NoteBoard)
                            findViewById(R.id.noteboard);
        game = new Game(noteBoard);

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