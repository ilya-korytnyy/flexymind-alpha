package com.flexymind.alpha.customviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import com.flexymind.alpha.R;
import com.flexymind.alpha.player.Note;
import com.larvalabs.svgandroid.SVG;
import android.graphics.Picture;
import com.larvalabs.svgandroid.SVGParser;

public class NoteView extends View {

    private static SVG      noteSVG;
    private static SVG      noteLine;
    private static Picture  notePicture;
    private static Picture  noteLinePicture;

    private final  Note     note;
    private final  boolean  isSharp;
    private final  boolean  isInverted;

    private final  int      noteWidth;
    private final  int      noteHeight;

    public NoteView(Context context, int noteWidth,
                            int noteHeight, Note note) {

        super(context);
        noteSVG     = SVGParser.getSVGFromResource( getResources()
                                                  , R.raw.notebody);
        notePicture = noteSVG.getPicture();


        noteLine    = SVGParser.getSVGFromResource( getResources()
                                                  , R.raw.note);
        noteLinePicture = noteLine.getPicture();

        isSharp         = isSharp();
        isInverted      = isInverted();
        this.noteWidth  = noteWidth;
        this.noteHeight = noteHeight;
        this.note       = note;
    }

    private boolean isInverted() {

        return false;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        setMeasuredDimension(noteWidth, noteHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
        canvas.drawPicture(notePicture, new Rect( 0
                                                , 0
                                                , noteWidth
                                                , noteHeight) );
    }

    /**
     * Compute note & sharp position in relation
     * to the stave. Draw note & sharp if necessary.
     * @param canvas canvas to draw on
     * @param x x coordinate of the note
     * @param y y coordinate of the note
     * @param scale gap betweem lines for note scaling
     * @param isStraight straight or inverted position
     * @param isSharp has sharp or not
     */
    protected void onDraw(Canvas canvas, int x, int y, int scale,
                          boolean isStraight, boolean isSharp) {

        int noteWidth = scale * 2;
        int noteHeight = scale * 3;
        int sharpWidth = noteWidth / 2;
        int sharpHeight = noteWidth;

        Rect noteRect = new Rect();
        Rect sharpRect = new Rect();

        // All notes till H would be drawn straight, rest is inverted
        if (isStraight){
            noteRect.left = x;
            noteRect.right = noteRect.left + noteWidth;
            noteRect.bottom = y;
            noteRect.top = noteRect.bottom - noteHeight;
            if (isSharp) {
                sharpRect.right = noteRect.left;
                sharpRect.left = sharpRect.right - sharpWidth;
                sharpRect.top = noteRect.bottom + scale/2;
                sharpRect.bottom = sharpRect.top - sharpHeight;
            }
        }else {                            //inverted position
            noteRect.right = x;
            noteRect.left = noteRect.right + noteWidth;
            noteRect.bottom = y;
            noteRect.top = noteRect.bottom + noteHeight;
            /*  if(isSharp){                           //XXX: don't need for one octave
                sharpRect.left = 0;
                sharpRect.right = noteRect.left;
                sharpRect.top = noteHeight / 3;
                sharpRect.bottom = sharpRect.top + sharpHeight;
            }*/
        }

        //Draw note & sharp if necessary
      //  drawSVG(canvas, R.raw.note, noteRect);
        if(isSharp){
         //   drawSVG(canvas, R.raw.invertedsharp, sharpRect);
        }
    }

    private boolean isSharp() {

        if (note == Note.Cz || note == Note.Dz
                || note == Note.Fz || note == Note.Cz
                || note == Note.Gz || note == Note.Az) {
            return  true;
        }
        return false;
    }



    // SHOW ERROR/CORRECT NOTE

    /**
     * Makes it green
     */
    public void highlight() {

    }

    /**
     * Makes it red
     */
    public void highlightError() {

    }

    /**
     * Makes it black again
     */
    public void unHighlight() {

    }
}
