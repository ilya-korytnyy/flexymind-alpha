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
    private int lineHeight;
    private int lineWidth;

    public StaveLine(Context context, int lineWidth, int lineHeight) {
        super(context);
    }

    public StaveLine(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public StaveLine(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        SVG lineSvg = SVGParser.getSVGFromResource( getResources()
                                                   , R.raw.line);
        Picture linesPicture = lineSvg.getPicture();
        canvas.drawPicture( linesPicture
                          , new Rect( 0
                                    , 0
                                    , this.lineHeight
                                    , this.lineWidth) );
        super.onDraw(canvas);
    }

}
