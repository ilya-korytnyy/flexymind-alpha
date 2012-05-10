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
 *      pPlayerX = new PlaySound(this, Tone.X);
 *  }
 *  and
 *  public void onClick... {
 *      pPlayerX.play();
 *  }
 *
 */
public class PianoPlayer extends View {
    public  SoundPool   soundPool;
    public  int         toneID;
    private Note        note;

    /**
     * @param context from class GameScreen
     */
    public PianoPlayer(Context context, Tone tone) {
        super(context);

        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);

        note = new Note(tone, 1, true, true);
        toneID = soundPool.load(context, note.getRawName(), 1);
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