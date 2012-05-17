package com.flexymind.alpha.customviews;

import android.content.Context;
import android.graphics.Picture;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import com.flexymind.alpha.R;
import com.flexymind.alpha.player.Note;
import com.flexymind.alpha.player.PianoPlayer;

import java.util.ArrayList;

import static com.flexymind.alpha.customviews.PictureStorage.blackKeyNotPressed;
import static com.flexymind.alpha.customviews.PictureStorage.whiteKeyNotPressed;

public class PianoKeyboard extends Board implements View.OnTouchListener {

    private final int    COUNT_OF_WHITE_KEYS     =   8;
    private final int    START_ID_FOR_KEY_VIEWS  = 100;
    private final int[]  BLACK_KEY_POSITIONS     =  {1, 2, 4, 5, 6};
            //TODO fill this array automatic

    private View parent;

    private PianoPlayer player;



    public PianoKeyboard(Context context, AttributeSet attrs) {

        super(context, attrs);

        parent = getRootView();
        parent.setOnTouchListener(this);
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

        LayoutParams params = new LayoutParams(
                    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        params.addRule(ALIGN_LEFT);

        addKey( whiteKeyNotPressed
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
        key.setOnTouchListener(this);
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

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        for (int ptrIndex = 0; ptrIndex < motionEvent.getPointerCount(); ptrIndex++) {

            // index of the pointer which starts this Event
            int actionPointerIndex = motionEvent.getActionIndex();

            // resolve the action as a basic type (up, down or move)
            int actionResolved = motionEvent.getAction() & MotionEvent.ACTION_MASK;
            if (actionResolved < 7 && actionResolved > 4) {
                actionResolved = actionResolved - 5;
            }

            if (actionResolved == MotionEvent.ACTION_MOVE) {

                dealEvent(ptrIndex, motionEvent, view, actionResolved);


            } else {
                dealEvent(actionPointerIndex, motionEvent, view, actionResolved);

            }
        }
        return false;
    }

    private void dealEvent(int actionPointerIndex, MotionEvent event,
                           View eventView, int actionresolved) {
        int rawX, rawY;
        int location[] = { 0, 0 };
        eventView.getLocationOnScreen(location);

        rawX = (int) event.getX(actionPointerIndex) + location[0];
        rawY = (int) event.getY(actionPointerIndex) + location[1];

        ArrayList<View> views = getTouchedViews(rawX, rawY, actionresolved);

        // dumpEvent(event);
        for (View view : views) {
            int x, y;
            view.getLocationOnScreen(location);
            x = rawX - location[0];
            y = rawY - location[1];

            MotionEvent me = MotionEvent.obtain(event.getDownTime(),
                    event.getEventTime(), actionresolved, x, y,
                    event.getPressure(actionPointerIndex),
                    event.getPressure(actionPointerIndex),
                    event.getMetaState(), event.getXPrecision(),
                    event.getYPrecision(), event.getDeviceId(),
                    event.getEdgeFlags());
            me.setLocation(x, y);



            if (!me.equals(event)) {
                /*
             * Log.v("tag", "actionindex: " + actionPointerIndex +
             * " resolved: " + actionPointerIndex + " to " + view.toString()
             * + " y:" + view.getTop() + "-" + (view.getTop() +
             * view.getHeight()));
             */
                // me.setAction(actionresolved);
//   playOwnSound();
                view.onTouchEvent(me);
            }

            if (actionresolved == MotionEvent.ACTION_MOVE) {
            }
        }

    }

    private ArrayList<View> getTouchedViews(int x, int y, int action) {

        int moveGap = 0;

        if (action == MotionEvent.ACTION_MOVE) {
            moveGap = 0;
        }

        ArrayList<View> touchedViews = new ArrayList<View>();
        ArrayList<View> possibleViews = new ArrayList<View>();

        if (parent instanceof ViewGroup) {
            possibleViews.add(parent);
            for (int i = 0; i < possibleViews.size(); i++) {
                View view = possibleViews.get(i);

                int location[] = { 0, 0 };
                view.getLocationOnScreen(location);

                if (((view.getHeight() + location[1] + moveGap >= y)
                        & (view.getWidth() + location[0] + moveGap >= x)
                        & (view.getLeft() - moveGap <= x) & (view.getTop()
                        - moveGap <= y))
                        || view instanceof FrameLayout) {

                    touchedViews.add(view);
                    possibleViews.addAll(getChildViews(view));
                }

            }
        }

        return touchedViews;

    }

    private ArrayList<View> getChildViews(View view) {

        ArrayList<View> views = new ArrayList<View>();
        if (view instanceof ViewGroup) {
            ViewGroup v = ((ViewGroup) view);
            if (v.getChildCount() > 0) {
                for (int i = 0; i < v.getChildCount(); i++) {
                    views.add(v.getChildAt(i));
                }
            }
        }
        return views;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {

        if(motionEvent.getAction() ==  MotionEvent.ACTION_DOWN) {
            playOwnSound();
        }
        return true;
    }

    public void playOwnSound() {


                player = new PianoPlayer(null, Note.A);
                player.play();



    }
}