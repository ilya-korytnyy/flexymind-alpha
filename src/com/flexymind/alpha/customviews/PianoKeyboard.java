package com.flexymind.alpha.customviews;

import android.content.Context;
import android.graphics.Picture;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import com.flexymind.alpha.GameScreen;
import com.flexymind.alpha.R;
import com.flexymind.alpha.player.Note;

import static com.flexymind.alpha.customviews.PictureStorage.greenKey;
import static com.flexymind.alpha.customviews.
              PictureStorage.whiteKeyNotPressed;
import static com.flexymind.alpha.customviews.
              PictureStorage.blackKeyNotPressed;

public class PianoKeyboard extends Board {

    private final int    COUNT_OF_WHITE_KEYS     =   8;
    private final int    START_ID_FOR_KEY_VIEWS  = 100;
    private final int[]  BLACK_KEY_POSITIONS     =  {1, 2, 4, 5, 6};
            //TODO fill this array automatic



    public PianoKeyboard(Context context, AttributeSet attrs) {

        super(context, attrs);
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

        LayoutParams params = new LayoutParams(
                    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        params.addRule(ALIGN_LEFT);

        addKey( greenKey
              , getWhiteKeyHeight()
              , getWhiteKeyWidth()
              , params
              , id++
              , Note.C );

        for (int i = 0; i < COUNT_OF_WHITE_KEYS - 1; i++) {
            addKey( whiteKeyNotPressed
                  , getWhiteKeyHeight()
                  , getWhiteKeyWidth()
                  , paramsWithRightOf(id - 1)
                  , id++
                  , Note.getNotesForWhiteKeys()[i+1] );
        }
    }

    private LayoutParams paramsWithRightOf(int id) {

        LayoutParams params = new LayoutParams
                (LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.addRule(RIGHT_OF, id);

        return  params;
    }

    private void addBlackKeys() {

        int id = START_ID_FOR_KEY_VIEWS + COUNT_OF_WHITE_KEYS;

        int j = 0;
        for (int whiteKeyNumber: BLACK_KEY_POSITIONS) {
            addKey( blackKeyNotPressed
                  , getBlackKeyHeight()
                  , getBlackKeyWidth()
                  , paramsWithMargin(whiteKeyNumber * getWhiteKeyWidth()
                                              - getBlackKeyWidth() / 2)
                  , ++id
                  , Note.getNotesForBlackKeys()[j++] );
        }
    }

    private LayoutParams paramsWithMargin(int margin) {
        LayoutParams params = new LayoutParams
                (LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.leftMargin = margin;

        return params;
    }

    private void addKey(Picture picture, int keyH, int keyW,
                        LayoutParams params, int id, Note note) {

        PianoKey key = new PianoKey( getContext()
                                   , keyH
                                   , keyW
                                   , picture
                                   , note);
        key.setId(id);
        addView(key, params);
    }

    private int getWhiteKeyWidth() {
        return this.width / COUNT_OF_WHITE_KEYS;
    }

    private int getWhiteKeyHeight() {
        return  this.height;
    }

    private int getBlackKeyWidth() {
        return  getWhiteKeyWidth() / 2;
    }

    private int getBlackKeyHeight() {
        return getWhiteKeyHeight() / 2;
    }
}