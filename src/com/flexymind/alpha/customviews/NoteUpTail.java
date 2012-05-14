package com.flexymind.alpha.customviews;

import android.content.Context;
import android.graphics.Picture;
import android.util.AttributeSet;
import android.view.View;
import com.larvalabs.svgandroid.SVG;

public class NoteUpTail extends View {

    private static SVG      upTailSvg;
    private static Picture  upTailPicture;

    private final  int      tailHeight;
    private final  int      tailWidth;




    public NoteUpTail( Context context, int tailWidth
                     , int tailHeight) {

        super(context);
        this.tailWidth  = tailWidth;
        this.tailHeight = tailHeight;
    }
}
