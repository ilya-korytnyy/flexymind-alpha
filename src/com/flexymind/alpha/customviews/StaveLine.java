package com.flexymind.alpha.customviews;

import android.content.Context;
import static com.flexymind.alpha.customviews.PictureStorage.linePicture;

public class StaveLine extends ParentSelfDrawingView {

    public StaveLine(Context context, int lineWidth, int lineHeight) {

        super(context, lineWidth, lineHeight);
        picture = linePicture;
    }
}