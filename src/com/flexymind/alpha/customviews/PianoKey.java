package com.flexymind.alpha.customviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Picture;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;


public class PianoKey extends View {

    private int     keyHeight;
    private int     keyWidth;
    private Picture picture;

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
     * @param picture
     */

    public PianoKey(Context context, int keyH, int keyW, Picture picture) {

        super(context);
        this.keyHeight  = keyH;
        this.keyWidth   = keyW;
        this.picture    = picture;
    }

    @Override
    protected void onMeasure (int widthMeasureSpec, int heightMeasureSpec) {

       setMeasuredDimension(keyWidth, keyHeight);
    }

    @Override
    protected void onDraw (Canvas canvas) {

        canvas.drawPicture( picture
                          , new RectF(0, 0, keyWidth, keyHeight));

        super.onDraw(canvas);
    }
}