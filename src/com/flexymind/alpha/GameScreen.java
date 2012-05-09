package com.flexymind.alpha;

import android.app.Activity;
import android.os.Bundle;
import android.view.Display;

public class GameScreen extends Activity {

    public static Integer windowW = 0;
    public static Integer windowH = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Display display = getWindowManager().getDefaultDisplay();
        windowW = display.getWidth();
        windowH = display.getHeight();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
