package com.flexymind.alpha.startbutton;

import android.content.Context;
import android.graphics.Picture;
import android.view.MotionEvent;
import com.flexymind.alpha.R;
import com.flexymind.alpha.customviews.ParentSelfDrawingView;
import com.flexymind.alpha.*;
import com.flexymind.alpha.customviews.PianoKeyboard;
import com.flexymind.alpha.customviews.PictureStorage;
import com.flexymind.alpha.player.PianoPlayer;
import com.larvalabs.svgandroid.SVGParser;

/**
 * The class for the transparent layout with the start button..
 *
 */
public class StartButtonView extends ParentSelfDrawingView {

    private static  final   Picture     pressedPictrure;
    private static          PianoPlayer player;
    static {
        pressedPictrure  = PictureStorage.pressedStartButton;

    }

    /**
     * Constructor for class StartButtonView
     * @param context context of Application
     * @param height height of image of startbutton
     * @param width
     * */
    public StartButtonView(Context context, int width, int height) {
        super(context, width, height);
        picture = PictureStorage.startButton;
        player = new PianoPlayer(context, R.raw.song);
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
                picture = pressedPictrure;
                postInvalidate();
                break;
            }

            case MotionEvent.ACTION_UP: {
                postInvalidate();
                playOwnSound();
                PianoKeyboard.startDialog.hide();
                break;

            }
            //       if(motionEvent.getAction() ==  MotionEvent.ACTION_UP) {
            /**
             * change picture to another
             * play song
             * make this layout invisible
             * set visible GameScreen layout
             */

        }
        return true;
    }
}
