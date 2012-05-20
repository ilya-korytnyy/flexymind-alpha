package com.flexymind.alpha.customviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Picture;
import android.graphics.Rect;
import android.view.View;

/**
 * This class was created to solve the problem of itself
 * note rendering.
 *
 * Brief tutorial:
 *   1. create an object of this class in parent ViewGroup
 *      and send to constructor just a note, note width and note height
 *   2. now you should find a correct position in your ViewGroup
 *      and create an object of class RelativeLayout.LayoutParams
 *   3. to set position of note in relation to left corner of your
 *      View Group set the leftMargin and topMargin of your
 *      LayoutParamsMargin.
 *      Or if you want set position in relation
 *      to another view in your ViewGroup
 *      use the LayoutParams method addRule (for this feature, each
 *      component, should have an unique Id)
 *
 * The left top corner of whole note (include tail) is a left top corner
 * of note herself (ellipse of note). that's why you don't need to worry
 * about (not)inversion of note tail
 */

public abstract class ParentSelfDrawingView extends View {

    protected       Picture picture;
    protected final int     width;
    protected final int     height;



    public ParentSelfDrawingView(Context context, int width, int height) {

        super(context);
        this.width  = width;
        this.height = height;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawPicture( picture
                          , new Rect( 0, 0, this.width, this.height) );
        super.onDraw(canvas);

    }



}