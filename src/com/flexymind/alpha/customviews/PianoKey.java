package com.flexymind.alpha.customviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Picture;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PictureDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.flexymind.alpha.R;
import com.larvalabs.svgandroid.SVG;

import static com.larvalabs.svgandroid.SVGParser.getSVGFromResource;


public class PianoKey extends ImageView {
    private Drawable pianoKey;
    private int keyHeight;
    private int keyWidth;
    private float svgWidth;
    private float svgHeight;


    public PianoKey(Context context, AttributeSet attrs) {
        super(context, attrs);
        makeDrawableKey();
    }

    public PianoKey(Context context) {
        super(context);
        makeDrawableKey();
    }

    public PianoKey(Context context, int keyH, int keyW) {
        super(context);
        makeDrawableKey();
        keyHeight  = keyH;
        keyWidth   = keyW;
    }

    @Override
    protected void onMeasure (int widthMeasureSpec, int heightMeasureSpec){
       setMeasuredDimension(keyWidth, keyHeight);
    }

    @Override
    protected void onDraw (Canvas canvas) {
        //canvas.scale((float)1.0, (float)1.2333);
        SVG svg = getSVGFromResource(getResources(),
                R.raw.whitekey);

        getHeightAndWidthOfSVG (svg);
        Picture picture = svg.getPicture();

        canvas.drawPicture(picture, new RectF(0,0,keyWidth, keyHeight));

        //canvas.translate((float)0.0, (float)-100);
        super.onDraw(canvas);
    }

    private void makeDrawableKey() {
        this.setBackgroundColor(Color.RED);

     //   this.setImageDrawable(drawable);
    }

    private void getHeightAndWidthOfSVG(SVG svg) {
        RectF dimensions = new RectF(svg.getLimits());
        svgHeight = Math.abs(dimensions.bottom - dimensions.top);
        svgWidth  = Math.abs(dimensions.right  - dimensions.left);
    }
}