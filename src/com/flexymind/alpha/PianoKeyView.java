package com.flexymind.alpha;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.app.Activity;
import android.widget.ImageView;
import com.larvalabs.svgandroid.SVGParser;
import com.larvalabs.svgandroid.SVG;

import static com.larvalabs.svgandroid.SVGParser.getSVGFromResource;

public class PianoKeyView extends ImageView {
    private Drawable pianoKey;

    public PianoKeyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        makeDrawableKey();
    }

    public PianoKeyView(Context context) {
        super(context);
        
        makeDrawableKey();
    }

    private void makeDrawableKey() {
        this.setBackgroundColor(Color.WHITE);
        SVG svg = getSVGFromResource(getResources(),
                R.raw.bigkey);
       this.setImageDrawable(svg.createPictureDrawable());
    }

}