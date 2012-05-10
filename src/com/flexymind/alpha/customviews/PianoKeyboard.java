package com.flexymind.alpha.customviews;

import android.content.Context;
import android.graphics.Picture;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import com.flexymind.alpha.R;
import com.larvalabs.svgandroid.SVG;
import com.larvalabs.svgandroid.SVGParser;

public class PianoKeyboard extends LinearLayout {

    private final static int    COUNT_OF_WHITE_KEYS = 8;
    private              int    keyboardW           = 0;
    private              int    keyboardH           = 0;

    public PianoKeyboard(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure (int widthMeasureSpec,
                              int heightMeasureSpec) {

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        keyboardW = getMeasuredWidth();
        keyboardH = getMeasuredHeight();
    }

    @Override
    public void onLayout(boolean changed, int l,
                             int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        addWhiteKeys();
    }

    private void addWhiteKeys() {
        SVG svg = SVGParser.getSVGFromResource(getResources(),
                R.raw.whitekey);

        for (int i = 0; i < COUNT_OF_WHITE_KEYS; i++) {
            addKey(svg.getPicture());
        }
    }

    private void addKey(Picture picture) {
        PianoKey key = new PianoKey(getContext(),
                                    keyboardH,
                                    keyboardW / COUNT_OF_WHITE_KEYS,
                                    picture);
        addView(key);
    }
}