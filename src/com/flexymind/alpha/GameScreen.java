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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        StaticResources.res = getResources();

        NoteBoard noteBoard = (NoteBoard)
                            findViewById(R.id.noteboard);


        Game game = new Game(noteBoard);
    }


}