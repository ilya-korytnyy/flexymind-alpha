package com.flexymind.alpha.customviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class PianoKeyboard extends LinearLayout {

    public PianoKeyboard(Context context, AttributeSet attrs) {
        super(context, attrs);
        onCreate();
    }

    public PianoKeyboard(Context context) {
        super(context);
        onCreate();
    }

    private void onCreate() {
        for (int i=0; i<8; i++) {
            addKey();
        }
    }



    private void addKey() {
        PianoKey key = new PianoKey(getContext());
        addView(key);
    }
}
