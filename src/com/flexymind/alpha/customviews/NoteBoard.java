package com.flexymind.alpha.customviews;

import android.content.Context;
import android.util.AttributeSet;
import com.flexymind.alpha.player.Note;
import org.w3c.dom.ProcessingInstruction;

import java.util.EnumMap;
import java.util.Map;

public class NoteBoard extends Board {

    private class NoteMargeParams {

        public int  line;
        public int  isOnLine;

        public NoteMargeParams(int line, int isOnLine) {

            this.line = line;
            this.isOnLine = isOnLine;
        }
    }


    private static final int ALL_LINES_COUNT  =  9;

    private int staveHeight;
    private int staveWidth;
    private int clefHeight;
    private int clefWidth;
    private int linesGap;
    private int lineHeight;
    private int lineWidth;
    private int topMarginCorrection = 0;

    private Map<Note, NoteMargeParams> notesParams;

    public NoteBoard(Context context) {

        super(context);
        initializeNotesMarginParams();
    }

    public NoteBoard(Context context, AttributeSet attrs) {

        super(context, attrs);
        initializeNotesMarginParams();
     }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        super.onLayout(changed, l, t, r, b);
        setAllNeededSizes();
        drawStave();
        drawClef();
        drawNote(Note.C);
    }

    private void initializeNotesMarginParams() {

        notesParams = new EnumMap<Note, NoteMargeParams>(Note.class);
        notesParams.put(Note.C,   new NoteMargeParams(6,  1));
        notesParams.put(Note.D,   new NoteMargeParams(5,  0));
        notesParams.put(Note.Dz,  new NoteMargeParams(5,  0));
        notesParams.put(Note.E,   new NoteMargeParams(5,  1));
        notesParams.put(Note.F,   new NoteMargeParams(4,  0));
        notesParams.put(Note.Fz,  new NoteMargeParams(4,  0));
        notesParams.put(Note.G,   new NoteMargeParams(4,  1));
        notesParams.put(Note.Gz,  new NoteMargeParams(4,  1));
        notesParams.put(Note.A,   new NoteMargeParams(3,  0));
        notesParams.put(Note.Az,  new NoteMargeParams(3,  0));
        notesParams.put(Note.H,   new NoteMargeParams(3,  1));
        notesParams.put(Note.C1,  new NoteMargeParams(3,  1));
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


        int margeOnLineCorrection;

        if (notesParams.get(note).line > 5) {

            params.addRule(BELOW, 5);
            margeOnLineCorrection = (notesParams.get(note).line - 5) *
                linesGap - notesParams.get(note).isOnLine * linesGap / 2;
        }else {

            params.addRule(BELOW, notesParams.get(note).line);
            margeOnLineCorrection = notesParams.get(note).isOnLine *
                    linesGap / 2;
        }



        params.topMargin    = margeOnLineCorrection +
                              noteView.getTopMarginCorrection();
        params.leftMargin   = 100;

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
        clef.setId(300);

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

    private void initializeMap() {


    }

    private void setClefSize() {

        clefHeight = staveHeight;
        clefWidth  = staveHeight  / 3;
    }

    private void setStaveSize() {

        staveHeight = this.height;
        linesGap = staveHeight / (ALL_LINES_COUNT - 1);
        staveWidth = this.width - linesGap * 2;
    }

    private void setLineSize() {

        lineHeight = linesGap / 5;
        lineWidth = staveWidth;
    }
}