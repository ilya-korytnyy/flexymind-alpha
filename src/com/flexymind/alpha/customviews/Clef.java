package com.flexymind.alpha.customviews;

import android.content.Context;
import static com.flexymind.alpha.customviews.PictureStorage.clefPicture;

public class Clef extends ParentSelfDrawingView {

    public Clef(Context context, int width, int height) {

        super(context, width, height);
        picture = clefPicture;
    }
}