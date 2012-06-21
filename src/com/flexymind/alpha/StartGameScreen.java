package com.flexymind.alpha;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import com.flexymind.alpha.startbutton.StartButton;

public class StartGameScreen extends Activity
                    implements View.OnClickListener {

    private StartButton     startButton;
    private ImageButton     settingsButton;
    private Bitmap          settingsBitmap;
    private RelativeLayout  startScreen;

    private static final int SETTINGS_BUTTON = 1001;

    // settings data
    private String selectedSong       = "default";
    private String selectedInstrument = "default";
    private int numberOfOctaves       = 1;
    private int orientation           =
            ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE;
    private String[] availableSongs = {};

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
        setSettingsButtonSize();
    }

    private void setSettingsButtonSize() {
        settingsBitmap = BitmapFactory.decodeResource( getResources()
                                                     , R.drawable.settings);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        settingsBitmap =
                Bitmap.createScaledBitmap( settingsBitmap
                                         , metrics.heightPixels / 6
                                         , metrics.heightPixels / 6
                                         , true);
        settingsButton.setImageBitmap(settingsBitmap);
    }

    @Override
    public void onClick(View view) {

        switch(view.getId()) {
            case R.id.startbutton: {
                createGameScreen();
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
        intent.putExtra("selectedSong", selectedSong);
        intent.putExtra("selectedInstrument", selectedInstrument);
        intent.putExtra("numberOfOctaves", numberOfOctaves);
        intent.putExtra("orientation", orientation);
        startActivity(intent);
        finish();
    }

    private void createSettingsScreen() {

        Intent intent = new Intent(this, Settings.class);         // TODO: pass a list of songs in the intent
        int requestCode = 1;
        startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onActivityResult( int requestCode
                                   , int resultCode
                                   , Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        // orientation
        setRequestedOrientation(data.getIntExtra( "orientation"
                  , ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE));
        this.orientation = data.getIntExtra("orientation"
                  , ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);

        // song
        selectedSong = data.getStringExtra("selectedSong");

        // instrument
        selectedInstrument = data.getStringExtra("selectedInstrument");

        // octaves
        numberOfOctaves = data.getIntExtra("numberOfOctaves", 1);
    }
}