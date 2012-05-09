package com.flexymind.alpha.player;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import com.flexymind.alpha.R;

/**
 * Класс необходимо вызывать в методе onClick в классе GameScreen
 */
public class PianoPlayer {
    public  SoundPool   soundPool;
    public  int         toneID;
    private Context     context;


    /**
     * Constructor
     * @param context from class GameScreen
     */
    public PianoPlayer(Context context) {
        this.context = context;
    }

    /**
     * Gets the .mid file for that Note and plays it.
     */
    public void play(Note note) {
        getTone(note);
        float   leftVolume  =   1.0f;
        float   rightVolume =   1.0f;
        int     priority    =   0;
        int     loop        =   0;
        float   rate        =   1.0f;
        soundPool.play(toneID, leftVolume, rightVolume, priority, loop, rate);
    }


    /** Get id of mid file*/
    private void getTone(Note note) {
        int maxStreams  =   1;
        int streamType  =   AudioManager.STREAM_MUSIC;
        int srcQuality  =   0;

        soundPool = new SoundPool(maxStreams, streamType, srcQuality);

        /** note.getTone() должна возвращать R.raw.<example> */
        // toneID = soundPool.load(context, note.getTone(), 1);
        toneID = soundPool.load(context, R.raw.a, 1);
    }
}