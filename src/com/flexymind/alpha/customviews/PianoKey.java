package com.flexymind.alpha.customviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Picture;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.flexymind.alpha.player.Note;
import com.flexymind.alpha.player.PianoPlayer;


public class PianoKey extends ParentSelfDrawingView {

    private PianoPlayer player;

    /**
     *
     * @param context
     * @param keyH key height
     * @param keyW key width
     * @param picture
     */

    public PianoKey(Context context, int keyH, int keyW,
                            Picture picture,  Note note) {

        super(context, keyW, keyH);
        this.picture    = picture;
        this.player     = PianoPlayer.getInstance(context, note);
    }

    public void playOwnSound() {

        Thread soundThread = new Thread(new Runnable() {
            @Override
            public void run() {
                player.play();
            }
        });
        soundThread.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {

        if(motionEvent.getAction() ==  MotionEvent.ACTION_DOWN) {
            playOwnSound();
        }
        return true;
    }
}