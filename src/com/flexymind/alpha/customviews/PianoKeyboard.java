package com.flexymind.alpha.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class PianoKeyboard extends LinearLayout {
    static boolean isKeyboardAlreadyDraw = false;

    public PianoKeyboard(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure (int widthMeasureSpec, int heightMeasureSpec){
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        final int keyboardW= getMeasuredWidth();
        final int keyboardH= getMeasuredHeight();

        if (!isKeyboardAlreadyDraw) {
            addWhiteKeys(keyboardH, keyboardW);
            isKeyboardAlreadyDraw = true;
        }
    }

    public PianoKeyboard(Context context) {
        super(context);
    }

    private void addWhiteKeys(int keyboardH, int keyboardW) {
        for (int i=0; i<8; i++) {
            addKey(keyboardH, keyboardW);
        }
    }

    private void addKey(int keyboardH, int keyboardW) {
        PianoKey key = new PianoKey(getContext(), keyboardH*2/3, keyboardW/8);
        addView(key);
    }
}
