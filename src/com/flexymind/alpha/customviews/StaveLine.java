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
    private  int      lineHeight;
    private  int      lineWidth;
    private  Picture  picture;


    public StaveLine(Context context, int lineWidth, int lineHeight, Picture picture) {

        super(context);
        this.lineWidth  = lineWidth;
        this.lineHeight = lineHeight;
        this.picture    = picture;
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

        canvas.drawPicture( picture
                          , new Rect( 0
                                    , 0
                                    , this.lineWidth
                                    , this.lineHeight) );
        super.onDraw(canvas);
    }

}
