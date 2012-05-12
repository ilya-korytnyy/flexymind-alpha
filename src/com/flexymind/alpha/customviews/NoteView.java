package com.flexymind.alpha.customviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import com.flexymind.alpha.R;
import com.flexymind.alpha.player.Tone;
import com.larvalabs.svgandroid.SVG;
import android.graphics.Picture;
import com.larvalabs.svgandroid.SVGParser;

import java.util.EnumMap;

//[review] mandrigin: Maybe it's the ToneView class?
public class NoteView extends View {

    public NoteView(Context context) {
        super(context);
    }

    public NoteView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Parse .svg picture and fit it in rectangle
     * @param canvas canvas to draw on
     * @param source path to picture source
     * @param edges rectangle parametres
     */
    private void drawSVG(Canvas canvas, int source, Rect edges){

        SVG noteSvg = SVGParser.getSVGFromResource(getResources(), source);
        Picture notePicture = noteSvg.getPicture();
        Rect noteRect = new Rect(edges.left, edges.top, edges.right, edges.bottom);
        canvas.drawPicture(notePicture, noteRect);
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
        drawSVG(canvas, R.raw.note, noteRect);
        if(isSharp){
            drawSVG(canvas, R.raw.invertedsharp, sharpRect);
        }
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
