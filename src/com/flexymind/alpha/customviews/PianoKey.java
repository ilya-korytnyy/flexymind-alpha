package com.flexymind.alpha.customviews;

import android.content.Context;
import android.graphics.Picture;
import android.view.MotionEvent;
import com.flexymind.alpha.player.Note;
import com.flexymind.alpha.player.PianoPlayer;
//
//import static com.flexymind.alpha.customviews.PictureStorage.blackKeyNotPressed;
//import static com.flexymind.alpha.customviews.PictureStorage.blackKeyPressed;
//import static com.flexymind.alpha.customviews.PictureStorage.whiteKeyNotPressed;
//import static com.flexymind.alpha.customviews.PictureStorage.whiteKeyPressed;


public class PianoKey extends ParentSelfDrawingView {

    private PianoPlayer player;
    private  boolean whiteKey;

    /**
     *
     * @param context
     * @param keyH key height
     * @param keyW key width
     * @param picture
     */

    public PianoKey(Context context, int keyH, int keyW,
                            Picture picture,  Note note, boolean whiteKey) {

        super(context, keyW, keyH);
        this.picture    = picture;
        this.player     = new PianoPlayer(context, note);
        this.whiteKey   = whiteKey;
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

    private void switchPicture(Picture switchPicture) {

        picture = switchPicture;
    }

    public boolean isWhite(){
        return whiteKey;
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {

        if(motionEvent.getAction() ==  MotionEvent.ACTION_DOWN) {

            playOwnSound();
            if (whiteKey)
                switchPicture(PictureStorage.whiteKeyPressed);
            else
                switchPicture(PictureStorage.blackKeyPressed);
        }

        if(motionEvent.getAction() ==  MotionEvent.ACTION_UP) {
            if (whiteKey)
                switchPicture(PictureStorage.whiteKeyNotPressed);
            else
                switchPicture(PictureStorage.blackKeyNotPressed);
        }

        if(motionEvent.getAction() ==  MotionEvent.ACTION_CANCEL) {
            if (whiteKey)
                switchPicture(PictureStorage.whiteKeyNotPressed);
            else
                switchPicture(PictureStorage.blackKeyNotPressed);
        }

        if( motionEvent.getAction() ==  MotionEvent.ACTION_POINTER_DOWN ||
            motionEvent.getAction() ==  MotionEvent.ACTION_POINTER_UP ) {
        }

         invalidate();


        return true;
    }
}