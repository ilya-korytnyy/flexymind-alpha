package com.flexymind.alpha;

import android.app.Activity;
import android.os.Bundle;

public class GameScreen extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        StaticResources.res = getResources();
        getWindow().setWindowAnimations(0);


    }
}