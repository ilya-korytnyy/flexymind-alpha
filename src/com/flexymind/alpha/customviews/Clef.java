package com.flexymind.alpha.customviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Picture;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import com.flexymind.alpha.R;
import com.larvalabs.svgandroid.SVG;
import com.larvalabs.svgandroid.SVGParser;

public class Clef extends View {
    private int width;
    private int height;

    public Clef(Context context, int width, int height) {
        super(context);
        this.width = width;
        this.height = height;
    }

    public Clef(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Clef(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        SVG lineSvg = SVGParser.getSVGFromResource( getResources()
                                                   , R.raw.line);
        Picture linesPicture = lineSvg.getPicture();
        canvas.drawPicture( linesPicture
                , new Rect( 0
                , 0
                , this.width
                , this.height) );
    }
}
