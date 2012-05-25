package com.flexymind.alpha.player;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.JetPlayer;
import android.media.SoundPool;
import com.flexymind.alpha.R;

/**
 * Сlass for playing defined tone when pressed the button.
 */
public class PianoPlayer {
    private  SoundPool   soundPool;
    private  int         toneID;
    private MidiNote    midiNote;
    private static JetPlayer jetPlayer;
    private static AssetFileDescriptor melody;

    /**
     * @param context from class GameScreen
     */
    public PianoPlayer(Context context, Note note) {

        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);

        midiNote = new MidiNote(note);
        toneID = soundPool.load(context, midiNote.getMidiFileId(), 1);
    }

    /**
     * @param context Context for SoundPool
     * @param song id of song(R.raw.song)
     */
    public PianoPlayer(Context context, int song) {

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

    private static void startJetPlayer(Context context) {

        jetPlayer = JetPlayer.getJetPlayer();
        jetPlayer.setEventListener(JetPlayerEventListener);
        setJetPlayerMelody(context);
        jetPlayer.play();
    }

    private static void setJetPlayerMelody(Context context){

        melody = context.getResources().openRawResourceFd(R.raw.gooses);
        jetPlayer.loadJetFile(melody);
        jetPlayer.clearQueue();
        jetPlayer.queueJetSegment(1, -1, 0, 0, 0, (byte) 0);
    }

    static JetPlayer.OnJetEventListener JetPlayerEventListener = new JetPlayer.OnJetEventListener() {
        @Override
        public void onJetEvent(JetPlayer player, short segment, byte track, byte channel,
                               byte controller, byte value) {
            if (value == 80) {
                //highlightNextNote();
            }
        }

        @Override
        public void onJetUserIdUpdate(JetPlayer jetPlayer, int i, int i1) {
        }

        @Override
        public void onJetNumQueuedSegmentUpdate(JetPlayer jetPlayer, int i) {
        }

        @Override
        public void onJetPauseUpdate(JetPlayer jetPlayer, int i) {
        }
    };

}