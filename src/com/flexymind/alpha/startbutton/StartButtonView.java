package com.flexymind.alpha.startbutton;

import android.content.Context;
import android.graphics.Picture;
import com.flexymind.alpha.R;
import com.flexymind.alpha.customviews.ParentSelfDrawingView;
import com.flexymind.alpha.customviews.PictureStorage;
import com.flexymind.alpha.player.PianoPlayer;

/**
 * The class for the transparent layout with the start button..
 *
 */
public class StartButtonView extends ParentSelfDrawingView {


    /**
     * Constructor for class StartButtonView
     * @param context context of Application
     * @param height height of image of startbutton
     * @param width  width of image of startbutton
     * */
    public StartButtonView(Context context, int width, int height) {
        super(context, width, height);
        picture = PictureStorage.startButton;
    }


}
