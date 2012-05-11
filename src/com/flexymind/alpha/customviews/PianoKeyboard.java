package com.flexymind.alpha.customviews;

import android.content.Context;
import android.graphics.Picture;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.flexymind.alpha.R;
import com.larvalabs.svgandroid.SVG;
import com.larvalabs.svgandroid.SVGParser;

public class PianoKeyboard extends RelativeLayout {

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
        addBlackKeys();
    }

    private void addBlackKeys() {
        SVG svg = SVGParser.getSVGFromResource(getResources(),
                R.raw.blackkey);

        RelativeLayout.LayoutParams params = new
                RelativeLayout.LayoutParams(
                LayoutParams.FILL_PARENT,
                LayoutParams.FILL_PARENT);

        int startId = 108;

            params.addRule(RelativeLayout.RIGHT_OF, 100);



            addKey(svg.getPicture(),
                    keyboardH / 2,
                    keyboardW / COUNT_OF_WHITE_KEYS / 2,
                    params,
                    startId++);
    }

    private void addWhiteKeys() {
        SVG svg = SVGParser.getSVGFromResource(getResources(),
                R.raw.whitekey);

        RelativeLayout.LayoutParams params = new
                RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);

        int startId = 100;

        addKey(svg.getPicture(),
               keyboardH,
               keyboardW / COUNT_OF_WHITE_KEYS,
               params,
               startId++);



        for (int i = 0; i < COUNT_OF_WHITE_KEYS; i++) {

            RelativeLayout.LayoutParams paramss = new
                    RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);

            paramss.addRule(RelativeLayout.RIGHT_OF, startId - 1);

            addKey(svg.getPicture(),
                    keyboardH,
                    keyboardW / COUNT_OF_WHITE_KEYS,
                    paramss,
                    startId++);
        }
    }

    private void addKey(Picture picture, int keyH, int keyW, RelativeLayout.LayoutParams params, int id) {
        PianoKey key = new PianoKey(getContext(),
                                    keyH,
                                    keyW,
                                    picture);
        key.setId(id);
        addView(key, params);
    }
}