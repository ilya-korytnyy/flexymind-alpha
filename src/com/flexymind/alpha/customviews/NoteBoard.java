package com.flexymind.alpha.customviews;

import android.content.Context;
import android.util.AttributeSet;
import com.flexymind.alpha.player.Note;

import java.util.ArrayList;
import java.util.List;

public class NoteBoard extends Board {

    private static final int ALL_LINES_COUNT = 9;

    private int staveHeight;
    private int staveWidth;
    private int clefHeight;
    private int clefWidth;
    private int linesGap;
    private int lineHeight;
    private int lineWidth;
    private int topMarginCorrection = 0;

    private List<Note> notes = new ArrayList<Note>();
    private List<NoteView> noteViews = new ArrayList<NoteView>();   // stores all NoteViews that are displayed

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
        drawNote(Note.C1);
    }

    private void setAllNeededSizes() {

        setStaveSize();
        setLineSize();
        setClefSize();
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

            staveLine.setId(i - 1);
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
        clef.setId(1);

        this.addView(clef, layoutParams);
    }

    /**
     * Manage note parameters based on note given. Draw another note (some notes may be already drawn)
     * @param note note to represent
     */
    public void drawNote(Note note) {
        notes.add(note);

        NoteView noteView = new NoteView( getContext()
                , linesGap
                , linesGap
                , note);

        LayoutParams layoutParams = new LayoutParams
                (LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        layoutParams.addRule(BELOW, 2);
        layoutParams.topMargin    = this.topMarginCorrection
                + noteView.getTopMarginCorrection();
        layoutParams.leftMargin   = 100;
        noteView.setId(notes.size());

        this.addView(noteView, layoutParams);
    }

    /**
     * Draw each Note of the melody. Previously drawn Notes are removed.
     * @param melody
     */
    public void drawMelody(List<Note> melody) {
        // TODO: remove NoteViews
        notes = new ArrayList<Note>();
        for (Note note : melody) {
            drawNote(note);
        }
    }

    public void removeAllNotes() {
        notes.clear();
        // TODO: redraw (remove NoteViews)
    }

    public void highlightCorrectNote(int position) {
        if (position < 0 || position >= notes.size()) {
            return;
        }

        noteViews.get(position).highlight();
    }

    public void unHighlightNote(int position) {
        if (position < 0 || position >= notes.size()) {
            return;
        }

        noteViews.get(position).unHighlight();
    }

    public void highlightErrorNote(Note note, int position) {
        // TODO: create new View at specified position and make it red
    }

    private void initializeMap() {

    }

    /*
     * The stave takes all height, width without gaps and sets line gap depending on positions count
     */
    private void setStaveSize() {

        staveHeight = this.height;
        linesGap = staveHeight / (ALL_LINES_COUNT - 1);
        staveWidth = this.width - linesGap * 2;
    }

    private void setLineSize() {

        lineHeight = linesGap / 5;      // the line is 5 times thinner then the distance between lines
        lineWidth = staveWidth;
    }

    private void setClefSize() {

        clefHeight = staveHeight;
        clefWidth  = clefHeight / 3;
    }
}