package com.flexymind.alpha.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import com.flexymind.alpha.player.Note;

public class NoteBoard extends RelativeLayout {


    private static final int ALL_LINES_COUNT  =  9;

    private int staveHeight;
    private int staveWidth;
    private int clefHeight;
    private int clefWidth;
    private int linesGap;
    private int lineHeight;
    private int lineWidth;

    public NoteBoard(Context context) {

        super(context);
    }

    public NoteBoard(Context context, AttributeSet attrs) {

        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        super.onLayout(changed, l, t, r, b);
        setAllNeededSizes();
        drawStave();
        drawClef();
        drawNote(Note.D);
    }

    private void setAllNeededSizes() {

        setStaveSize();
        setLineSize();
        setClefSize();
    }

    /**
     * Manage note parameters based on note given
     * @param note note to represent
     */
    public void drawNote(Note note) {

        NoteView noteView = new NoteView( getContext()
                                        , linesGap
                                        , linesGap
                                        , note);

        LayoutParams params = new LayoutParams
                (LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);


        params.addRule(BELOW, 2);
        params.leftMargin = 100;

        this.addView(noteView, params);
    }

    private void drawStave() {

        for (int i = 2; i < 7; i++) {

            StaveLine staveLine = new StaveLine( getContext()
                                               , lineWidth
                                               , lineHeight);
            LayoutParams layoutParams = new LayoutParams
                   (LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

            layoutParams.topMargin  = linesGap * i;
            layoutParams.leftMargin = linesGap;

            staveLine.setId(i-1);
                    //TODO make unique constants to Id of each component

            this.addView(staveLine, layoutParams);
        }
    }

    private void drawClef() {

        Clef clef = new Clef( getContext()
                            , clefWidth
                            , clefHeight );

        LayoutParams layoutParams = new LayoutParams
               (LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        layoutParams.leftMargin = linesGap;
        clef.setId(0);

        this.addView(clef, layoutParams);
    }

    public void outputMelody() {
    }


    public void removeAllNotes() {

    }


    public void highlightCorrectNote(int position) {

    }

    public void highlightErrorNote(Note note, int position) {

    }

    private void setClefSize() {

        clefHeight = staveHeight;
        clefWidth  = staveHeight  / 3;
    }

    private void setStaveSize() {

        staveHeight = this.getHeight();
        linesGap = staveHeight / (ALL_LINES_COUNT - 1);
        staveWidth = this.getWidth() - linesGap * 2;
    }

    private void setLineSize() {

        lineHeight = linesGap / 5;
        lineWidth = staveWidth;
    }
}