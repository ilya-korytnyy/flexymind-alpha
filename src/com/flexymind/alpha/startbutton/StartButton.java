package com.flexymind.alpha.startbutton;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import com.flexymind.alpha.R;
import com.flexymind.alpha.customviews.Board;
import android.graphics.Color;
import com.flexymind.alpha.player.PianoPlayer;


public class StartButton extends Board {

    public         StartButtonView  sButtonView;
    private static PianoPlayer      player;


    public StartButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        player = PianoPlayer.getInstance(super.getContext());

    }

    public StartButton(Context context) {
        super(context);
    }


    @Override
    public void onLayout( boolean changed, int l
                        , int t, int r, int b)  {

        super.onLayout(changed, l, t, r, b);

        setAllNeedSize();
        drawStartButtonView();
    }

    private void setAllNeedSize() {
        double screenWidth  = 0.7 * this.getHeight();
        double screenHeight = 0.7 * this.getHeight();
        height = (int) screenHeight;
        width  = (int) screenWidth;
    }


    private void drawStartButtonView() {

        sButtonView = new StartButtonView( getContext()
                                         , width
                                         , height );

        LayoutParams layoutParams = new LayoutParams( LayoutParams.WRAP_CONTENT
                                              , LayoutParams.WRAP_CONTENT);

        layoutParams.addRule(CENTER_IN_PARENT);
        sButtonView.setId(0);
        this.addView(sButtonView, layoutParams);

    }

    public static void playOwnSound() {

        Thread soundThread = new Thread(new Runnable() {
            @Override
            public void run() {
                player.play(R.raw.song);
            }
        });
        soundThread.start();
    }

}

