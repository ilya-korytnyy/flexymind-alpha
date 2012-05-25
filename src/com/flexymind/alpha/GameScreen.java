package com.flexymind.alpha;

import android.app.Activity;
import android.os.Bundle;
import com.flexymind.alpha.Game.Game;
import com.flexymind.alpha.customviews.NoteBoard;


public class GameScreen extends Activity {
    private NoteBoard noteBoard;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        StaticResources.res = getResources();

        noteBoard = (NoteBoard)
                            findViewById(R.id.noteboard);

        Game game = new Game(noteBoard);
        game.gameStart();

    }

    @Override
    public void onResume() {
        super.onResume();
        noteBoard.invalidate();
    }



}