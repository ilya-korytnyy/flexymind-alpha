package com.flexymind.alpha.customviews;

import android.content.Context;
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
        Rect initPosition = new Rect(100,100,150,200);
        addKey(initPosition);
        initPosition.set(300,300, 350, 400);
        addKey(initPosition);
    }

    private void addKey(Rect initPosition) {
        PianoKey key = new PianoKey(getContext(), initPosition);
        addView(key);
    }
}
