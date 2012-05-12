package com.flexymind.alpha.customviews;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.View;
import com.flexymind.alpha.R;
import com.flexymind.alpha.player.Tone;
import com.larvalabs.svgandroid.SVG;
import com.larvalabs.svgandroid.SVGParser;

import java.util.EnumMap;

/**
 * All coordinates are in density pixels
 */
public class NoteBoard extends View {

    private static final int ALL_LINES_COUNT  =  9;
    private static final int MAX_NOTES        =  7;
    private static final int WIDTH_MARGIN      = 10;

    private static EnumMap<Tone, Integer> noteYCoord;
    private int staveHeight;
    private int staveWidth;
    private int cliefHeight;
    private int cliefWidth;
    private int linesGap;
    private int lineHeight;
    private int notesGap;

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

        noteYCoord.put(Tone.C,  step * 15);
        noteYCoord.put(Tone.Cz, step * 15);
        noteYCoord.put(Tone.D,  step * 14);
        noteYCoord.put(Tone.Dz, step * 14);
        noteYCoord.put(Tone.E,  step * 13);
        noteYCoord.put(Tone.F,  step * 12);
        noteYCoord.put(Tone.Fz, step * 12);
        noteYCoord.put(Tone.G,  step * 11);
        noteYCoord.put(Tone.Gz, step * 11);
        noteYCoord.put(Tone.A,  step * 10);
        noteYCoord.put(Tone.Az, step * 10);
        noteYCoord.put(Tone.H,  step *  7);
        noteYCoord.put(Tone.C1, step *  6);
    }

    /**
     * Draw stave, clef, manage notes drawing
     * @param canvas canvas to draw on
     */
    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);

        setAllNeededSizes();

        drawStave(canvas);
        drawClief(canvas);

        //HARDCODE try to output
        outputNote(canvas, Tone.Fz);
    }

    private void setAllNeededSizes() {
        setStaveSize();
        setLinesGap();
        setCliefSize();
        prepareNotesCoord();
    }


    private void drawStave(Canvas canvas) {
        SVG lineSvg = SVGParser.getSVGFromResource(getResources(),
                                                   R.raw.line);
        Picture linesPicture = lineSvg.getPicture();
        for (int i = 2; i < 7; i++) {
            canvas.drawPicture(linesPicture,
                               new Rect(WIDTH_MARGIN,
                                       i * linesGap,
                                       this.staveWidth,
                                       i * linesGap + lineHeight) );
        }
    }

    private void drawClief(Canvas canvas) {
        SVG clefSvg = SVGParser.getSVGFromResource(getResources(),
                R.raw.scaledclef);
        Picture clefPicture = clefSvg.getPicture();

        canvas.drawPicture(clefPicture,
                           new Rect(WIDTH_MARGIN,
                           0,
                           WIDTH_MARGIN + cliefWidth,
                           cliefHeight) );
    }

    public void outputMelody() {
    }

    /**
     * Manage note parameters based on tone given
     * @param canvas canvas to draw on
     * @param tone tone to represent
     */
    public void outputNote(Canvas canvas, Tone tone) {

        NoteView note = new NoteView(getContext());

        int x = WIDTH_MARGIN + cliefWidth + notesGap;
        int y = noteYCoord.get(tone) + lineHeight / 2;
        int scale = linesGap;

        note.onDraw(canvas, x, y, scale, isStraight(tone), isSharp(tone));
    }

    private boolean isStraight(Tone tone) {
        if(noteYCoord.get(tone) < noteYCoord.get(Tone.Az)){
            return false;
        }
        return true;
    }

    private boolean isSharp(Tone tone) {
        if (tone== Tone.Cz || tone== Tone.Dz
            || tone== Tone.Fz || tone== Tone.Cz
            || tone== Tone.Gz || tone == Tone.Az) {
                return  true;
        }
        return false;
    }

    public void removeAllNotes() {
        //
    }


    public void highlightCorrectNote(int position) {
       //
    }

    public void highlightErrorNote(Tone tone, int position) {
        //
    }

    private void prepareNotesCoord() {
        notesGap = (staveWidth - cliefWidth) / (MAX_NOTES + 1);
        noteYCoord = new EnumMap<Tone, Integer>(Tone.class);
        initializeMap(linesGap);
    }

    private void setCliefSize() {
        cliefHeight = staveHeight;
        cliefWidth  = staveHeight  / 3;
    }

    private void setLinesGap() {
        linesGap = staveHeight / (ALL_LINES_COUNT - 1);
        lineHeight = linesGap / 5;
    }

    private void setStaveSize() {
        staveHeight = this.getHeight();
        staveWidth = this.getWidth() - WIDTH_MARGIN * 2;
    }

}
