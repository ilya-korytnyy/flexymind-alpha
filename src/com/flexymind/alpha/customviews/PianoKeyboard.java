package com.flexymind.alpha.customviews;

import android.content.Context;
import android.graphics.Picture;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import com.flexymind.alpha.R;
import com.flexymind.alpha.player.Note;
import com.flexymind.alpha.player.PianoPlayer;
import com.larvalabs.svgandroid.SVG;
import com.larvalabs.svgandroid.SVGParser;

public class PianoKeyboard extends RelativeLayout {

    private final int    COUNT_OF_WHITE_KEYS     =   8;
    private final int    START_ID_FOR_KEY_VIEWS  = 100;
    private final int[]  BLACK_KEY_POSITIONS     =  {1, 2, 4, 5, 6};
            // in future this array will be create automatic,

    private       int    keyboardW               =   0;
    private       int    keyboardH               =   0;

    public PianoKeyboard(Context context, AttributeSet attrs) {

        super(context, attrs);
    }

    @Override
    protected void onMeasure ( int widthMeasureSpec
                             , int heightMeasureSpec) {

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
        addPlayMidiButton();
    }

    private void addWhiteKeys() {

        int id = START_ID_FOR_KEY_VIEWS;

        SVG svgUnpress = SVGParser.getSVGFromResource(getResources(),
                         R.raw.whitekey);
        SVG svgPress = SVGParser.getSVGFromResource(getResources(),
                R.raw.whitekeypressed);

        LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);

        params.addRule(ALIGN_LEFT);

        addKey( svgUnpress.getPicture()
              , svgPress.getPicture()
              , getWhiteKeyHeight()
              , getWhiteKeyWidth()
              , params
              , id++
              , Note.C );

        for (int i = 0; i < COUNT_OF_WHITE_KEYS - 1; i++) {
            addKey( svgUnpress.getPicture()
                  , svgPress.getPicture()
                  , getWhiteKeyHeight()
                  , getWhiteKeyWidth()
                  , paramsWithRightOf(id - 1)
                  , id++
                  , Note.getNotesForWhiteKeys()[i+1] );
        }
    }

    private RelativeLayout.LayoutParams paramsWithRightOf(int id) {

        LayoutParams params = new LayoutParams
                (LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.addRule(RIGHT_OF, id);

        return  params;
    }

    private void addPlayMidiButton() {

        final Button playButton = new Button(getContext());
        final PianoPlayer song = new PianoPlayer( this.getContext()
                                                , R.raw.song );
        LayoutParams layoutParams = new LayoutParams
                (LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        layoutParams.addRule(CENTER_HORIZONTAL);

        OnClickListener onClickListener = new OnClickListener() {
            @Override
            public void onClick(View view) {
                song.play();
                playButton.setVisibility(View.INVISIBLE);
            }
        };

        playButton.setText("Play");

        playButton.setOnClickListener(onClickListener);

        this.addView(playButton, layoutParams);
    }

    private void addBlackKeys() {

        SVG svgUnpress = SVGParser.getSVGFromResource(getResources(),
                R.raw.blackkey);
        SVG svgPress = SVGParser.getSVGFromResource(getResources(),
                R.raw.blackkeypressed);

        int id = START_ID_FOR_KEY_VIEWS + COUNT_OF_WHITE_KEYS;

        int j = 0;
        for (int whiteKeyNumber: BLACK_KEY_POSITIONS) {
            addKey( svgUnpress.getPicture()
                  , svgPress.getPicture()
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

    private void addKey( Picture pictureUnpress, Picture picturePress
                       , int keyH, int keyW
                       , LayoutParams params, int id, Note note) {

        PianoKey key = new PianoKey( getContext()
                                   , keyH
                                   , keyW
                                   , pictureUnpress
                                   , picturePress
                                   , note);
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