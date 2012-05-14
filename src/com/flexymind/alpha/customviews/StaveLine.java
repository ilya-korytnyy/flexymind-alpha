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

public class StaveLine extends View {
    private         int      lineHeight;
    private         int      lineWidth;

    private static  SVG      lineSvg;
    private static  Picture  lineSvgPicture;



    public StaveLine(Context context, int lineWidth, int lineHeight) {

        super(context);
        this.lineWidth  = lineWidth;
        this.lineHeight = lineHeight;

        lineSvg = SVGParser.getSVGFromResource( getResources()
                , R.raw.line);
        lineSvgPicture = lineSvg.getPicture();
    }

    public StaveLine(Context context, AttributeSet attrs) {

        super(context, attrs);
    }

    public StaveLine(Context context, AttributeSet attrs, int defStyle) {

        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        setMeasuredDimension(lineWidth, lineHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawPicture( lineSvgPicture
                          , new Rect( 0
                                    , 0
                                    , this.lineWidth
                                    , this.lineHeight) );
        super.onDraw(canvas);
    }

}
