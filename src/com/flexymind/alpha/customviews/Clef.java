package com.flexymind.alpha.customviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Picture;
import android.graphics.Rect;
import android.view.View;
import com.flexymind.alpha.R;
import com.larvalabs.svgandroid.SVG;
import com.larvalabs.svgandroid.SVGParser;

public class Clef extends ParentSelfDrawingView {

    public Clef(Context context, int width, int height) {

        super(context, width, height);

        SVG clef = SVGParser.getSVGFromResource( getResources()
                                               , R.raw.scaledclef);
        picture = clef.getPicture();
    }
}