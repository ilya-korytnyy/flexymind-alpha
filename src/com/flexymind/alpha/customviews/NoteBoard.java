package com.flexymind.alpha.customviews;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.View;
import com.flexymind.alpha.R;

import java.util.ArrayList;
import java.util.List;

/**
 * All coordinates are in density pixels
 *
 * @author Ilya Koritniy
 */
public class NoteBoard extends View {
    /** Number of lines of the board. The value is {@value}. */
    private static final int LINE_COUNT = 5;
    /** The maximum number of notes that can be drawn on the notes board. The value is {@value}. */
    private static final int MAX_NOTES = 7;
    /** A margin from each side of the display. The value is {@value}. */
    private static final int MARGIN = 30;
    /** The value is {@value}. */
    private static final int LINE_THICKNESS = 2;

    private Bitmap clef;
    private ArrayList<Note> notes = new ArrayList<Note>(MAX_NOTES);

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
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // set up for drawing
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(LINE_THICKNESS);

        // draw 5 lines
        int currentY = MARGIN;
        int lineMargin = (this.getHeight() - MARGIN * 2) / (LINE_COUNT - 1);
        for (int i = 0; i < LINE_COUNT; i++) {
            canvas.drawLine(MARGIN, currentY, this.getWidth() - MARGIN, currentY, paint);
            currentY += lineMargin;
        }

        // draw treble clef
        float proportion = (float) clef.getHeight() / clef.getWidth();

        // if the bitmap' height is <= then the lines' height, just draw as it is
        if (clef.getHeight() <= lineMargin * 4) {
            canvas.drawBitmap(clef, null, new Rect(MARGIN, MARGIN, clef.getWidth(), clef.getHeight()), paint);
        } else {   // otherwise, scale the bitmap
            int clefHeight = lineMargin * 4;
            int clefWidth = (int) (lineMargin * 4 * proportion);
            canvas.drawBitmap(clef, null, new Rect(MARGIN, MARGIN - LINE_THICKNESS, clefHeight, clefWidth), paint);
        }
    }

    /**
     * Puts the specified {@code note} on the specified position of the note board
     *
     * @param note
     */
    public void outputNote(Note note) {

    }

    /**
     * Puts specified {@code notes} on the note board
     *
     * @param notes
     */
    public void outputNotes(List<Note> notes) {

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
     * @param note
     * @param position Position of a note on the note board, starting from 0.
     */
    public void highlightCorrectNote(Note note, int position, Color color) {
        // check range
        if (position < 0
                || position >= notes.size()) {
            return;
        }

        notes.get(position).setColor(color);
    }

    /**
     * Changes the color of a note.
     *
     * @param note
     * @param position Position of a note on the note board, starting from 0.
     */
    public void highlightErrorNote(Note note, int position, Color color) {
        // check range
        if (position < 0
                || position >= notes.size()) {
            return;
        }

        notes.get(position).setColor(color);
    }

}
