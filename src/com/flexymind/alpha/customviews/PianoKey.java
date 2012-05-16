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


public class PianoKey extends View {

    private int         keyHeight;
    private int         keyWidth;
    private Picture     picture;
    private Picture     picturePress;
    private Picture     pictureUnpress;
    private PianoPlayer player;

    public PianoKey(Context context, AttributeSet attrs) {

        super(context, attrs);
    }

    public PianoKey(Context context) {

        super(context);
    }

    /**
     *
     * @param context
     * @param keyH key height
     * @param keyW key width
     * @param picturePress
     */

    public PianoKey( Context context, int keyH, int keyW
                   , Picture pictureUnpress, Picture picturePress
                   , Note note) {

        super(context);
        this.keyHeight  = keyH;
        this.keyWidth   = keyW;
        this.pictureUnpress = pictureUnpress;
        this.picturePress = picturePress;
        this.picture    = pictureUnpress;
        this.player     = new PianoPlayer(context, note);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

       setMeasuredDimension(keyWidth, keyHeight);
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

        switch (motionEvent.getAction()) {

            case MotionEvent.ACTION_DOWN: {

                picture = picturePress;
                postInvalidate();
                playOwnSound();
                break;
            }

            case MotionEvent.ACTION_UP: {

                picture = pictureUnpress;
                postInvalidate();
                break;
            }

        }

        return true;
    }

    @Override
    protected void onDraw (Canvas canvas) {

        super.onDraw(canvas);
        canvas.drawPicture( picture
                          , new Rect(0, 0, keyWidth, keyHeight) );
    }
}