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

    private final int    COUNT_OF_WHITE_KEYS     =   8;
    //[review] mandrigin: remove unused constants
    private final int    COUNT_OF_BLACK_KEYS     =   5;
    private final int    START_ID_FOR_KEY_VIEWS  = 100;
    private final int[]  BLACK_KEY_POSITIONS     =  {1, 2, 4, 5, 6};

    private       int    keyboardW               =   0;
    private       int    keyboardH               =   0;

    public PianoKeyboard(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //[review] mandrigin: ',' should be on the new line:
    // Make:
    //protected void onMeasure( int widthMeasureSpec
    //                        , int heightMeasureSpec) {
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

    private void addWhiteKeys() {
        int id = START_ID_FOR_KEY_VIEWS;

        SVG svg = SVGParser.getSVGFromResource(getResources(),
                R.raw.whitekey);

        LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);

        params.addRule(ALIGN_LEFT);

        addKey(svg.getPicture(),
                getWhiteKeyHeight(),
                getWhiteKeyWidth(),
                params,
                id++);

        for (int i = 0; i < COUNT_OF_WHITE_KEYS - 1; i++) {
            addKey(svg.getPicture(),
                    getWhiteKeyHeight(),
                    getWhiteKeyWidth(),
                    paramsWithRightOf(id - 1),
                    id++);
        }
    }

    private RelativeLayout.LayoutParams paramsWithRightOf(int id) {
        LayoutParams params = new LayoutParams
                (LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.addRule(RIGHT_OF, id);

        return  params;
    }

    private void addBlackKeys() {
        SVG svg = SVGParser.getSVGFromResource(getResources(),
                R.raw.blackkey);

        int id = START_ID_FOR_KEY_VIEWS + COUNT_OF_WHITE_KEYS;

        for (int whiteKeyNumber: BLACK_KEY_POSITIONS) {
            addKey(svg.getPicture(),
                   getBlackKeyHeight(),
                   getBlackKeyWidth(),
                   paramsWithMargin(whiteKeyNumber * getWhiteKeyWidth()
                                              - getBlackKeyWidth() / 2),
                   ++id);
        }
    }

    private LayoutParams paramsWithMargin(int margin) {
        LayoutParams params = new LayoutParams
                (LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.leftMargin = margin;

        return params;
    }

    private void addKey(Picture picture, int keyH, int keyW,
                                LayoutParams params, int id) {

        PianoKey key = new PianoKey(getContext(),
                                    keyH,
                                    keyW,
                                    picture);
        key.setId(id);
        addView(key, params);
    }

    private int getWhiteKeyWidth() {
        return keyboardW / COUNT_OF_WHITE_KEYS;
    }

    private int getWhiteKeyHeight() {
        return  keyboardH;
    }

    private int getBlackKeyWidth() {
        return  getWhiteKeyWidth() / 2;
    }

    private int getBlackKeyHeight() {
        return getWhiteKeyHeight() / 2;
    }
}