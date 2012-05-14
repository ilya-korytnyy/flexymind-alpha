package com.flexymind.alpha.customviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import com.flexymind.alpha.R;
import com.flexymind.alpha.player.Note;
import com.larvalabs.svgandroid.SVG;
import android.graphics.Picture;
import com.larvalabs.svgandroid.SVGParser;

/**
 * This class was created to solve the problem of itself
 * note rendering.
 *
 * Brief tutorial:
 *   1. create an object of this class in parent ViewGroup
 *      and send to constructor just a note, note width and note height
 *   2. now you should find a correct position in your ViewGroup
 *      and create an object of class RelativeLayout.LayoutParams
 *   3. to set position of note in relation to left corner of your
 *      View Group set the leftMargin and topMargin of your
 *      LayoutParamsMargin.
 *      Or if you want set position in relation
 *      to another view in your ViewGroup
 *      use the LayoutParams method addRule (for this feature, each
 *      component, should have an unique Id)
 *
 * The left top corner of whole note (include tail) is a left top corner
 * of note herself (ellipse of note). that's why you don't need to worry
 * about (not)inversion of note tail
 */

public class NoteView extends RelativeLayout {

    private static SVG      noteSVG;
    private static SVG      noteInvertedTail;
    private static Picture  notePicture;
    private static Picture  noteInvertedTailPicture;

    private final  Note     note;
    private final  boolean  isSharp;
    private final  boolean  isInverted;

    private final  int      noteWidth;
    private final  int      noteHeight;

    /**
     * @param context
     * @param noteWidth a width just of the ellipse
     *                  of note (exclude tail and sharp)
     * @param noteHeight a height just of the ellipse
     *                  of note (exclude tail and sharp)
     * @param note
     */
    public NoteView(Context context, int noteWidth,
                            int noteHeight, Note note) {

        super(context);
        noteSVG     = SVGParser.getSVGFromResource( getResources()
                                                  , R.raw.notebody);
        notePicture = noteSVG.getPicture();


        noteInvertedTail    = SVGParser.getSVGFromResource( getResources()
                                                  , R.raw.notetailinverted);
        noteInvertedTailPicture = noteInvertedTail.getPicture();

        this.note       = note;
        this.noteWidth  = noteWidth;
        this.noteHeight = noteHeight;
        isSharp         = isSharp();
        isInverted      = isInverted();
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


    private boolean isSharp() {

        for (Note note : Note.getNotesForBlackKeys()) {

           if (this.note == note) {
               return true;
           }
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
