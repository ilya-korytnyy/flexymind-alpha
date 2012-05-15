package com.flexymind.alpha.customviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Picture;
import android.graphics.Rect;
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

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);

        SVG clef = SVGParser.getSVGFromResource( getResources()
                                               , R.raw.scaledclef);
        Picture linesPicture = clef.getPicture();
        canvas.drawPicture( linesPicture
                          , new Rect( 0
                                    , 0
                                    , this.width
                                    , this.height) );
    }
}
