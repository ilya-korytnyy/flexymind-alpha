package com.flexymind.alpha.customviews;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import com.flexymind.alpha.player.MidiNote;
import com.flexymind.alpha.player.Note;

import java.util.*;

public class NoteBoard extends Board {

    private static final int ALL_LINES_COUNT  =  9;

    private int staveHeight;
    private int staveWidth;
    private int clefHeight;
    private int clefWidth;
    private int linesGap;
    private int lineHeight;
    private int lineWidth;
    private int noteLeftMarge;
    private int notesGap;
    public static boolean isCreated = false;

    private Map<Note, NoteMargeParams> notesParams;

    private List<Note> notes = new LinkedList<Note>();
    public  List<NoteView> noteViews =
                            new ArrayList<NoteView>(); // stores all NoteViews that are displayed

    private class NoteMargeParams {

        public int  line;
        public int  isOnLine;
        public NoteMargeParams(int line, int isOnLine) {

            this.line = line;
            this.isOnLine = isOnLine;
        }

    }

    public NoteBoard(Context context) {

        super(context);
        initializeNotesMarginParams();
    }

    public NoteBoard(Context context, AttributeSet attrs) {

        super(context, attrs);
        initializeNotesMarginParams();
    }


    public void showAllWhatINeed() {

        setAllNeededSizes();
        drawStave();
        drawClef();
        drawMelodyOnStave();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
        isCreated = true;
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
        notesParams.put(Note.C1,  new NoteMargeParams(2,  0));
    }

    private void setAllNeededSizes() {

        setStaveSize();
        setLineSize();
        setClefSize();
        setNotesMarginParams();
    }

    public int getHowMuchIWant() {

        return 8;
    }

    /**
     * Manage note parameters based on note given
     * @param note note to represent
     */
    public NoteView drawNote(Note note) {

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
            margeOnLineCorrection = - notesParams.get(note).isOnLine *
                    linesGap / 2;
        }



        params.topMargin    = margeOnLineCorrection +
                              noteView.getTopMarginCorrection();
        params.leftMargin   = this.noteLeftMarge;

        this.addView(noteView, params);
        this.noteLeftMarge += notesGap;

        return noteView;
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

    /**
     * Draw each Note of the melody. Previously drawn Notes are removed.
     * @param melody
     */
    public void setShownNotes(List<MidiNote> melody) {

        removeNotesFromStave();

        for (MidiNote midiNote : melody){
            notes.add(midiNote.getNote());
        }
    }

    private void drawMelodyOnStave() {

        for (Note note : notes) {
            noteViews.add(drawNote(note));
        }
    }


    public void highlightNote(int id) {

        noteViews.get(id).highlightGreen();
    }

    public void highlightCorrectNote(int position) {

        if (position < 0 || position >= notes.size()) {
            return;
        }
        noteViews.get(position).highlightGreen();
    }


    public void removeNotesFromStave() {

        for (NoteView note : noteViews) {
            this.removeView(note);
        }
        noteViews.clear();
        notes.clear();
    }

    public void unHighlightNote(int position) {

        if (position < 0 || position >= notes.size()) {
            return;
        }
        noteViews.get(position).highlightBlack();
    }

    public void highlightErrorNote(Note note, int position) {
        // TODO: create new View at specified position and make it red
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

    private void setNotesMarginParams() {

        this.noteLeftMarge = clefWidth * 2;
        this.notesGap      = (staveWidth - noteLeftMarge
        - getHowMuchIWant() * 20) /  (getHowMuchIWant() - 1);
    }
}

/*
=======
package com.flexymind.alpha.customviews;

import android.content.Context;
import android.util.AttributeSet;
import com.flexymind.alpha.Game.Game;
import com.flexymind.alpha.player.MidiNote;
import com.flexymind.alpha.player.Note;

import java.util.*;

public class NoteBoard extends Board {

    private static final int ALL_LINES_COUNT  =  9;

    private int staveHeight;
    private int staveWidth;
    private int clefHeight;
    private int clefWidth;
    private int linesGap;
    private int lineHeight;
    private int lineWidth;
    private int noteLeftMarge;
    private int notesGap;

    private Map<Note, NoteMargeParams> notesParams;

    private List<Note> notes = new LinkedList<Note>();
    private List<NoteView> noteViews =
                            new LinkedList<NoteView>();   // stores all NoteViews that are displayed


    private class NoteMargeParams {

        public int  line;
        public int  isOnLine;
        public NoteMargeParams(int line, int isOnLine) {

            this.line = line;
            this.isOnLine = isOnLine;
        }

    }

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
        drawMelodyOnStave();
        highlightNote(3);
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
        notesParams.put(Note.C1,  new NoteMargeParams(2,  0));
    }

    private void setAllNeededSizes() {

        setStaveSize();
        setLineSize();
        setClefSize();
        setNotesMarginParams();
    }

    public int getHowMuchIWant() {

        return 6;
    }

    /**
     * Manage note parameters based on note given
     * @param note note to represent
     */
 /*   public void drawNote(Note note) {

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
            margeOnLineCorrection = - notesParams.get(note).isOnLine *
                    linesGap / 2;
        }



        params.topMargin    = margeOnLineCorrection +
                              noteView.getTopMarginCorrection();
        params.leftMargin   = this.noteLeftMarge;

        this.addView(noteView, params);
        this.noteLeftMarge += notesGap;
        noteViews.add(noteView);

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

    /**
     * Draw each Note of the melody. Previously drawn Notes are removed.
     * @param melody
     */
 /*   public void setShownNotes(List<MidiNote> melody) {

        notes.clear();

        for (MidiNote midiNote : melody){

            notes.add(midiNote.getNote());
        }
    }

    private void drawMelodyOnStave() {

        noteViews.clear();

        for (Note note1 : notes) {

            drawNote(note1);
        }

    }


    public void highlightNote(int id) {
        noteViews.get(id).highlightGreen();
    }

    public void highlightCorrectNote(int position) {
        if (position < 0 || position >= notes.size()) {
            return;
        }

        noteViews.get(position).highlightGreen();
    }

    public void unHighlightNote(int position) {
        if (position < 0 || position >= notes.size()) {
            return;
        }

        noteViews.get(position).highlightBlack();
    }

    public void highlightErrorNote(Note note, int position) {
        // TODO: create new View at specified position and make it red
    }


    /*
     * The stave takes all height, width without gaps and sets line gap depending on positions count
     */
 /*   private void setStaveSize() {

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

    private void setNotesMarginParams() {

        this.noteLeftMarge = clefWidth * 2;
        this.notesGap      = (staveWidth - noteLeftMarge
        - getHowMuchIWant() * 20) /  (getHowMuchIWant() - 1);
    }



>>>>>>> 4a68c6db025fe019279ec8e9bd7762e1145d7bcf
}  */