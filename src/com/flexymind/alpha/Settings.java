package com.flexymind.alpha;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.*;

/**
 * TODO: pass list of songs and instruments with the Intent
 */
public class Settings extends Activity implements AdapterView.OnItemSelectedListener,
                                                  SeekBar.OnSeekBarChangeListener {
    private String selectedSong = "default";
    private String selectedInstrument = "default";
    private int numberOfOctaves = 1;
    private int orientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE;

    private AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_layout);

        // create song spinner
        Spinner songSpinner = (Spinner) findViewById(R.id.songSpinner);
        ArrayAdapter<CharSequence> songAdapter =
                ArrayAdapter.createFromResource(this, R.array.songs, android.R.layout.simple_spinner_item);
        songAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        songSpinner.setAdapter(songAdapter);
        songSpinner.setOnItemSelectedListener(this);

        // create instrument spinner
        Spinner instrumentSpinner = (Spinner) findViewById(R.id.instrumentSpinner);
        ArrayAdapter<CharSequence> instrumentAdapter =
                ArrayAdapter.createFromResource(this, R.array.instruments, android.R.layout.simple_spinner_item);
        instrumentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        instrumentSpinner.setAdapter(instrumentAdapter);
        instrumentSpinner.setOnItemSelectedListener(this);

        // create volume seek bar
        SeekBar volumeSeekBar = (SeekBar) findViewById(R.id.volumeSeekBar);
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        volumeSeekBar.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
    }

    // Spinner

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        if (adapterView.getId() == R.id.songSpinner) {
            selectedSong = adapterView.getItemAtPosition(position).toString();
        } else if (adapterView.getId() == R.id.instrumentSpinner) {
            selectedInstrument = adapterView.getItemAtPosition(position).toString();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        selectedSong = "default";
    }

    // SeekBar listeners

    @Override
    public void onProgressChanged(SeekBar seekBar, int value, boolean fromUser) {
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, value, AudioManager.FLAG_PLAY_SOUND);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        // do nothing
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        // do nothing
    }

    // Checkbox listeners

    public void onToggleClicked(View view) {
        if (((ToggleButton) view).isChecked()) {
            orientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE;
        } else {
            orientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
        }
    }

    // returning result

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("selectedSong", selectedSong);
        intent.putExtra("selectedInstrument", selectedInstrument);
        intent.putExtra("numberOfOctaves", numberOfOctaves);
        setResult(RESULT_OK, intent);
        finish();
    }
}
