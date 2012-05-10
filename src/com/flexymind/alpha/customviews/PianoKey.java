package com.flexymind.alpha.customviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Picture;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.flexymind.alpha.R;
import com.larvalabs.svgandroid.SVG;

import static com.larvalabs.svgandroid.SVGParser.getSVGFromResource;


public class PianoKey extends ImageView {
    private Drawable    pianoKey;
    private int         keyHeight;
    private int         keyWidth;

    public PianoKey(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PianoKey(Context context) {
        super(context);
    }

    public PianoKey(Context context, int keyH, int keyW) {
        super(context);
        keyHeight  = keyH;
        keyWidth   = keyW;
    }

    @Override
    protected void onMeasure (int widthMeasureSpec, int heightMeasureSpec){
       setMeasuredDimension(keyWidth, keyHeight);
    }

    @Override
    protected void onDraw (Canvas canvas) {
        SVG svg = getSVGFromResource(getResources(),
                R.raw.whitekey);

        Picture picture = svg.getPicture();

        canvas.drawPicture(picture,
                           new RectF(0, 0, keyWidth, keyHeight) );

        super.onDraw(canvas);
    }
}