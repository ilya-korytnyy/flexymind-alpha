package com.flexymind.alpha.customviews;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import com.flexymind.alpha.R;
import com.flexymind.alpha.player.Note;
import com.larvalabs.svgandroid.SVG;
import com.larvalabs.svgandroid.SVGParser;

import java.util.EnumMap;


public class NoteBoard extends RelativeLayout {


    private static final int ALL_LINES_COUNT  =  9;
    private static final int MAX_NOTES        =  7;

    private static EnumMap<Note, Integer> noteYCoord;

    private int staveHeight;
    private int staveWidth;
    private int clefHeight;
    private int clefWidth;
    private int linesGap;
    private int lineHeight;
    private int notesGap;

    private int lineWidth;

    public NoteBoard(Context context) {
        super(context);
    }

    public NoteBoard(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NoteBoard(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * Map tones and y coordinate of the related notes
     * @param linesGap gap between two lines of the stave
     */
    private void initializeMap(int linesGap) {
        int step = linesGap / 2;

        noteYCoord.put(Note.C,  step * 15);
        noteYCoord.put(Note.Cz, step * 15);
        noteYCoord.put(Note.D,  step * 14);
        noteYCoord.put(Note.Dz, step * 14);
        noteYCoord.put(Note.E,  step * 13);
        noteYCoord.put(Note.F,  step * 12);
        noteYCoord.put(Note.Fz, step * 12);
        noteYCoord.put(Note.G,  step * 11);
        noteYCoord.put(Note.Gz, step * 11);
        noteYCoord.put(Note.A,  step * 10);
        noteYCoord.put(Note.Az, step * 10);
        noteYCoord.put(Note.H,  step *  7);
        noteYCoord.put(Note.C1, step *  6);
    }

    /**
     * Draw stave, clef, manage notes drawing
     * @param canvas canvas to draw on
     */

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);

        drawClef(canvas);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        super.onLayout(changed, l, t, r, b);
        setAllNeededSizes();
        drawStave();
        drawNote(Note.Dz);
    }

    private void setAllNeededSizes() {

        setStaveSize();
        setLineSize();
        setCliefSize();
        prepareNotesCoord();
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

        int x = linesGap + clefWidth + notesGap;
        int y = noteYCoord.get(note) + lineHeight / 2 - 100;

        params.leftMargin = x;
        params.topMargin  = y;

        this.addView(noteView, params);
    }

    private void drawStave() {

        for (int i = 2; i < 7; i++) {

            StaveLine staveLine = new StaveLine( getContext()
                                               , lineWidth
                                               , lineHeight);
            LayoutParams layoutParams = new LayoutParams
                    (LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

            layoutParams.topMargin = linesGap * i;
            layoutParams.leftMargin = linesGap;

            this.addView(staveLine, layoutParams);
        }
    }

    private void drawClef(Canvas canvas) {

        SVG clefSvg = SVGParser.getSVGFromResource(getResources(),
                R.raw.scaledclef);
        Picture clefPicture = clefSvg.getPicture();

        canvas.drawPicture( clefPicture
                          , new Rect( linesGap
                                    , 0
                                    , linesGap + clefWidth
                                    , clefHeight) );
    }

    public void outputMelody() {
    }


    public void removeAllNotes() {
        //
    }


    public void highlightCorrectNote(int position) {
       //
    }

    public void highlightErrorNote(Note note, int position) {
        //
    }

    private void prepareNotesCoord() {

        notesGap = (staveWidth - clefWidth) / (MAX_NOTES + 1);
        noteYCoord = new EnumMap<Note, Integer>(Note.class);
        initializeMap(linesGap);
    }

    private void setCliefSize() {

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