package com.flexymind.alpha;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Settings extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.settings_screen);
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, StartGameScreen.class);
        startActivity(intent);
        finish();
    }
}
