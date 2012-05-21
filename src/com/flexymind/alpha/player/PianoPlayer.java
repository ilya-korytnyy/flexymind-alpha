package com.flexymind.alpha.player;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.view.View;

/**
 * Сlass for playing defined tone when pressed the button.
 */
public class PianoPlayer extends View {
    public  SoundPool   soundPool;
    public  int         toneID;
    private MidiNote    midiNote;

    /**
     * @param context from class GameScreen
     */
    public PianoPlayer(Context context, Note note) {

        super(context);

        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);

        midiNote = new MidiNote(note);
        toneID = soundPool.load(context, midiNote.getMidiFileId(), 1);
    }


    public PianoPlayer(Context context, int song) {

        super(context);

        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        toneID = soundPool.load(context, song, 1);
    }
    /**
     * Gets the .mid file for that Note and plays it.
     */
    public void play() {
        // params of playing
        float   leftVolume      =   1.0f;
        float   rightVolume     =   1.0f;
        int     priority        =   0;
        int     loop            =   0;
        float   playbackSpeed   =   1.5f;

        soundPool.play(toneID, leftVolume, rightVolume, priority, loop, playbackSpeed);
    }

}