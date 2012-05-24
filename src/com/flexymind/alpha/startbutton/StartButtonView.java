package com.flexymind.alpha.startbutton;

import android.content.Context;
import com.flexymind.alpha.customviews.ParentSelfDrawingView;
import com.flexymind.alpha.customviews.PictureStorage;

/**
 * The class for the transparent layout with the start button..
 *
 */
public class StartButtonView extends ParentSelfDrawingView {


    /**
     * Constructor for class StartButtonView
     * @param context context of Application
     * */
    public StartButtonView(Context context, int width, int height) {
        super(context, width, height);
        picture = PictureStorage.startButton;
    }

}
