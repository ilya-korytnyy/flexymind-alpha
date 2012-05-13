package com.flexymind.alpha.player;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.view.View;

/**
 * Сlass for playing defined tone when pressed the button.
 * An example of calling a particular melody in GameScreen class:
 *
 *  public PianoPlayer pPlayerX;    // one instance for defined tone
 *  public void onCreate.... {
 *      ...
 *      pPlayerX = new PlaySound(this, Note.X);
 *  }
 *  and
 *  public void onClick... {
 *      pPlayerX.play();
 *  }
 *
 *
 */

/*
 * [review] mandrigin:
 * 1. Maybe it is better to move Tone to the "play" method? Then we won't need the player for every key.
 * 2. Why is it inherited from the View? Looks like it's not. Maybe better to just save the 'context'?
 * 3. If it will be slow on soundpool.load, we can preload all notes.
 */
public class PianoPlayer extends View {
    public  SoundPool   soundPool;
    public  int         toneID;
    private MidiNote note;

    /**
     * @param context from class GameScreen
     */
    public PianoPlayer(Context context, Note note) {
        super(context);

        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);

        this.note = new MidiNote(note);
        toneID = soundPool.load(context, this.note.getRawName(), 1);
    }

    /**
     * Gets the .mid file for that MidiNote and plays it.
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