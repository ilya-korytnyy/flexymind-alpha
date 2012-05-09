package com.flexymind.alpha.customviews;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PictureDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.flexymind.alpha.R;
import com.larvalabs.svgandroid.SVG;

import static com.larvalabs.svgandroid.SVGParser.getSVGFromResource;

public class PianoKey extends ImageView {
    private Drawable pianoKey;

    public PianoKey(Context context, AttributeSet attrs) {
        super(context, attrs);
        makeDrawableKey();
    }

    public PianoKey(Context context) {
        super(context);
        makeDrawableKey();
    }

    private void makeDrawableKey() {
        this.setBackgroundColor(Color.WHITE);
        SVG svg = getSVGFromResource(getResources(),
                R.raw.whitekey);

       PictureDrawable drawable = svg.createPictureDrawable();
       this.setImageDrawable(drawable);
    }
}