package com.flexymind.alpha.customviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Picture;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PictureDrawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.app.Activity;
import android.widget.ImageView;
import com.flexymind.alpha.R;
import com.larvalabs.svgandroid.SVGParser;
import com.larvalabs.svgandroid.SVG;

import static com.larvalabs.svgandroid.SVGParser.getSVGFromResource;

public class PianoKey extends ImageView {
    private Rect keyPosition;
    private Drawable pianoKey;

    public PianoKey(Context context, AttributeSet attrs) {
        super(context, attrs);
        makeDrawableKey();
    }

    public PianoKey(Context context,  Rect position) {
        super(context);
        makeDrawableKey();
        this.keyPosition = new Rect(position);
    }

    @Override
    public void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(false, 0, 0, 100, 100);
    }

    private void makeDrawableKey() {
        this.setBackgroundColor(Color.WHITE);
        SVG svg = getSVGFromResource(getResources(),
                R.raw.whitekey);

       PictureDrawable drawable = svg.createPictureDrawable();
       this.setImageDrawable(drawable);
    }

}