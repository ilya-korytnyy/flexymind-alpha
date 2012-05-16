package com.flexymind.alpha.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public abstract class Board extends RelativeLayout{

    protected int    width;
    protected int    height;

    public Board(Context context) {
        super(context);
    }

    public Board(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Board(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    @Override
    protected void onMeasure ( int widthMeasureSpec
                             , int heightMeasureSpec) {

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        width  = getMeasuredWidth();
        height = getMeasuredHeight();
    }

}
