package com.flexymind.alpha.customviews;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.View;
import com.flexymind.alpha.R;
import com.flexymind.alpha.player.Tone;
import com.larvalabs.svgandroid.SVG;
import com.larvalabs.svgandroid.SVGParser;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;

/**
 * All coordinates are in density pixels
 */
public class NoteBoard extends View {

    private static final int ALL_LINES_COUNT = 9;
    private static final int MAX_NOTES = 7;
    private static final int widthMargin = 10;

    //[review] mandrigin: don't think is should be static.
    private static EnumMap<Tone, Integer> noteYCoord;
    private int staveHeight;
    private int staveWidth;
    private int linesGap;
    private int lineHeight;
    private int clefHeight;
    private int clefWidth;
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

        noteYCoord.put(Tone.C, step*15);
        noteYCoord.put(Tone.Cz, step*15);
        noteYCoord.put(Tone.D, step*14);
        noteYCoord.put(Tone.Dz, step*14);
        noteYCoord.put(Tone.E, step*13);
        noteYCoord.put(Tone.F, step*12);
        noteYCoord.put(Tone.Fz, step*12);
        noteYCoord.put(Tone.G, step*11);
        noteYCoord.put(Tone.Gz, step*11);
        noteYCoord.put(Tone.A, step*10);
        noteYCoord.put(Tone.Az, step*10);
        noteYCoord.put(Tone.H, step*7);
        noteYCoord.put(Tone.C1, step*6);
    }

    /**
     * Draw stave, clef, manage notes drawing
     * @param canvas canvas to draw on
     */
    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);

        //stave size
        staveHeight = this.getHeight();
        staveWidth = this.getWidth() - widthMargin*2;
        //lines gap
        linesGap = staveHeight / (ALL_LINES_COUNT-1);
        lineHeight = linesGap / 5;
        //draw stave (5 lines visible, gap for 2+2 auxiliary lines above&below stave)
        SVG lineSvg = SVGParser.getSVGFromResource(getResources(), R.raw.line);
        Picture linesPicture = lineSvg.getPicture();
        for (int i = 2; i < 7; i++) {
            canvas.drawPicture(linesPicture, new Rect(widthMargin, i*linesGap,
                               this.staveWidth, i*linesGap+lineHeight));
        }

        //clef size
        clefHeight = staveHeight;
        clefWidth = clefHeight / 3;
        //draw clef
        SVG clefSvg = SVGParser.getSVGFromResource(getResources(), R.raw.scaledclef);
        Picture clefPicture = clefSvg.getPicture();
        canvas.drawPicture(clefPicture, new Rect(widthMargin, 0,
                           widthMargin+clefWidth, clefHeight));

        //prepare notes coordinates
        notesGap = (staveWidth-clefWidth) / (MAX_NOTES+1);
        noteYCoord = new EnumMap<Tone, Integer>(Tone.class);
        initializeMap(linesGap);

        //HARDCODE try to output
        outputNote(canvas, Tone.Fz);

    }

    public void outputMelody()
    {
        //outputNote();
    }

    /**
     * Manage note parameters based on tone given
     * @param canvas canvas to draw on
     * @param tone tone to represent
     */
    public void outputNote(Canvas canvas, Tone tone) {

        //[review] mandrigin: it's better to make additional params ot the NoteView -- tone.
        // Then all the code will be:
        // ToneView note = new ToneView(getContext(), tone);
        // int x = ...
        // int y = ...
        // int scale = ...
        // note.onDraw(...)
        NoteView note = new NoteView(getContext());

        int x = widthMargin + clefWidth + notesGap;
        int y = noteYCoord.get(tone) + lineHeight/2;
        int scale = linesGap;

        //straight or inverted position
        //[review] mandrigin: I think the NoteView should know is it inverted. It should know it's note then.
        // Move to the NoteView class.
        boolean isStraight = true;
        if(noteYCoord.get(tone) < noteYCoord.get(Tone.Az)){
            isStraight = false;
        }

        //has sharp or not
        //[review] mandrigin: I think the NoteView should know is it sharp. It should know it's note then.
        // Move to the NoteView class.
        boolean isSharp = false;
        if (tone== Tone.Cz || tone== Tone.Dz
            || tone== Tone.Fz || tone== Tone.Cz
            || tone== Tone.Gz || tone == Tone.Az) {
            isSharp = true;
        }

        note.onDraw(canvas, x, y, scale, isStraight, isSharp);
    }

    /**
     * Removes all notes
     */
    public void clear() {
        //
    }

    /**
     * Changes the color of a note.
     */
    public void highlightCorrectNote(int position) {
       //
    }

    /**
     * Changes the color of a note.
     */
    public void highlightErrorNote(Tone tone, int position) {
        //
    }

}
