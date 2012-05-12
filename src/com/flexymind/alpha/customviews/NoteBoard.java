package com.flexymind.alpha.customviews;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import com.flexymind.alpha.R;
import com.flexymind.alpha.player.Tone;
import com.larvalabs.svgandroid.SVG;
import com.larvalabs.svgandroid.SVGParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * All coordinates are in density pixels
 *
 */
public class NoteBoard extends View {
    /** Number of lines of the board. The value is {@value}. */
    private static final int LINE_COUNT = 5;
    /** The maximum number of notes that can be drawn on the notes board. The value is {@value}. */
    private static final int MAX_NOTES = 7;

    /** A margin from each side of the display. */
    private int margin;
    /** The horizontal distance between two (@code Notes} */
    private int hStep;

    private Bitmap clef;
    private ArrayList<Note> notes = new ArrayList<Note>(MAX_NOTES);
    private HashMap<Tone, Integer> noteYMap = new HashMap<Tone, Integer>(MAX_NOTES);

    public NoteBoard(Context context) {
        super(context);
        init();
    }

    public NoteBoard(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public NoteBoard(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        clef = BitmapFactory.decodeResource(getResources(), R.drawable.treble_clef);

        // init {@code notes} with default values
        notes.add(new Note(getContext(), Tone.C));
        notes.add(new Note(getContext(), Tone.D));
        notes.add(new Note(getContext(), Tone.E));
        notes.add(new Note(getContext(), Tone.F));
        notes.add(new Note(getContext(), Tone.G));
        notes.add(new Note(getContext(), Tone.A));
        notes.add(new Note(getContext(), Tone.H));
        notes.add(new Note(getContext(), Tone.C1));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // set parameters for drawing
        margin = this.getHeight() / 5;
        int linesHeight = (this.getHeight() - (int) (margin * 2.5));
        int linesWidth = (this.getWidth() - margin * 2);
        int lineHeight = linesHeight / (LINE_COUNT - 1);

        // set coordinates for each tone
        int lowestPoint = linesHeight + margin + lineHeight;
        int step = lineHeight / 2;
        noteYMap.put(Tone.C, lowestPoint);
        noteYMap.put(Tone.Cz, lowestPoint);
        noteYMap.put(Tone.C1, lowestPoint);
        noteYMap.put(Tone.D, lowestPoint - step);
        noteYMap.put(Tone.Dz, lowestPoint - step);
        noteYMap.put(Tone.E, lowestPoint - step * 2);
        noteYMap.put(Tone.F, lowestPoint - step * 3);
        noteYMap.put(Tone.Fz, lowestPoint - step * 3);
        noteYMap.put(Tone.G, lowestPoint - step * 4);
        noteYMap.put(Tone.Gz, lowestPoint - step * 4);
        noteYMap.put(Tone.A, lowestPoint - step * 5);
        noteYMap.put(Tone.Az, lowestPoint - step * 5);
        noteYMap.put(Tone.H, lowestPoint - step * 6);

        // draw 5 lines
        SVG linesSvg = SVGParser.getSVGFromResource(getResources(), R.raw.linescut);
        Picture linesPicture = linesSvg.getPicture();
        canvas.drawPicture(linesPicture, new Rect(margin, margin, linesWidth + margin, linesHeight + margin));

        // draw the clef
        SVG clefSvg = SVGParser.getSVGFromResource(getResources(), R.raw.goodclef);
        Picture clefPicture = clefSvg.getPicture();
        RectF clefLimits = clefSvg.getLimits();
        float proportion = clefLimits.bottom / clefLimits.right;
        int clefHeight = linesHeight + margin;
        int clefLeftIndent = (int) (margin * 1.5);
        int clefTopIndent = margin / 2;
        int clefWidth = (int) (clefHeight / proportion);
        canvas.drawPicture(clefPicture, new RectF(clefLeftIndent, clefTopIndent,
                                                  clefWidth + clefLeftIndent, clefHeight + clefTopIndent + margin / 2));

        // let's try to add note
        Note note = new Note(getContext(), Tone.C);

        // set note on required position
        int nY = noteYMap.get(Tone.C);
        hStep = linesWidth / MAX_NOTES;
        int nX = clefLeftIndent + clefWidth + clefLeftIndent // space for clef
                 + notes.indexOf(Tone.C) * hStep;

        // here we add the note
    }

    /**
     * Puts the specified {@code tone} on the specified position of the note board
     *
     * @param tone
     */
    public void outputTone(Tone tone) {

    }

    /**
     * Puts specified {@code tones} on the note board
     *
     * @param tones
     */
    public void outputNotes(List<Tone> tones) {

    }

    /**
     * Removes all notes
     */
    public void clear() {
        notes.clear();
    }

    /**
     * Changes the color of a note.
     *
     * @param position Position of a note on the note board, starting from 0.
     */
    public void highlightCorrectNote(int position) {
        // check range
        if (position < 0
                || position >= notes.size()) {
            return;
        }

        notes.get(position).highlight();
    }

    /**
     * Changes the color of a note.
     *
     * @param tone
     * @param position Position of a note on the note board, starting from 0.
     */
    public void highlightErrorNote(Tone tone, int position) {
        // check range
        if (position < 0
                || position >= notes.size()) {
            return;
        }

        notes.get(position).highlight();
    }

}
