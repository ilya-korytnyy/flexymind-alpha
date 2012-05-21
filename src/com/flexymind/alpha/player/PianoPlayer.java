package com.flexymind.alpha.player;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

/**
 * Сlass for playing defined tone when pressed the button.
 */
public class PianoPlayer {
    private SoundPool   soundPool;
    private Context context;

    private static PianoPlayer instance;

    public static PianoPlayer getInstance(Context context) {
        if (instance == null) {
            instance = new PianoPlayer(context);
        }
        return instance;
    }

    /**
     * @param context from class GameScreen
     */
    private PianoPlayer(Context context) {

        this.context = context;
        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
    }

    /**
     * Gets the .mid file for that Note and plays it.
     */
    public void play(Note note) {
        MidiNote midiNote = new MidiNote(note);
        int toneID = soundPool.load(context, midiNote.getMidiFileId(), 1);

        // params of playing
        float   leftVolume      =   1.0f;
        float   rightVolume     =   1.0f;
        int     priority        =   0;
        int     loop            =   0;
        float   playbackSpeed   =   1.5f;

        soundPool.play(toneID, leftVolume, rightVolume, priority, loop, playbackSpeed);
    }

    /**
     * Plays song
     *
     * @param song id of song(R.raw.song)
     */
    public void play(int song) {

        int toneID = soundPool.load(context, song, 1);

        // params of playing
        float   leftVolume      =   1.0f;
        float   rightVolume     =   1.0f;
        int     priority        =   0;
        int     loop            =   0;
        float   playbackSpeed   =   1.5f;

        soundPool.play(toneID, leftVolume, rightVolume, priority, loop, playbackSpeed);
    }

}