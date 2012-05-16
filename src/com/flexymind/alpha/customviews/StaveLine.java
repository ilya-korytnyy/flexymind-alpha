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

public class StaveLine extends ParentSelfDrawingView {

    public StaveLine(Context context, int lineWidth, int lineHeight) {

        super(context, lineWidth, lineHeight);

        picture = SVGParser.getSVGFromResource( getResources()
                , R.raw.line).getPicture();
    }
}
