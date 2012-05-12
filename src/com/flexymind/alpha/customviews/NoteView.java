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

public class NoteView extends View {
    private static EnumMap<Tone, Integer> noteOrder;
    private Tone tone;

    public NoteView(Context context, Tone tone) {
        super(context);
        this.tone = tone;
        init();
    }

    public NoteView(Context context, AttributeSet attrs, Tone tone) {
        super(context, attrs);
        this.tone = tone;
        init();
    }

    private void init(){
        noteOrder = new EnumMap<Tone, Integer>(Tone.class);
        initializeMap();
    }

    /**
     * Map tones in ascending order
     */
    private void initializeMap() {
        noteOrder.put(Tone.C, 0);
        noteOrder.put(Tone.Cz, 0);
        noteOrder.put(Tone.D, 1);
        noteOrder.put(Tone.Dz, 1);
        noteOrder.put(Tone.E, 2);
        noteOrder.put(Tone.F, 3);
        noteOrder.put(Tone.Fz, 3);
        noteOrder.put(Tone.G, 4);
        noteOrder.put(Tone.Gz, 4);
        noteOrder.put(Tone.A, 5);
        noteOrder.put(Tone.Az, 5);
        noteOrder.put(Tone.H, 6);
        noteOrder.put(Tone.C1, 7);
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
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // determine if we've to add #
        boolean isDiez = false;
        if (tone == Tone.Cz || tone == Tone.Dz
                || tone == Tone.Fz || tone == Tone.Cz
                || tone == Tone.Gz || tone == Tone.Az) {
            isDiez = true;
        }

        /*
         * noteWidth - width of the note without '#'
         * '#' takes 2/3 of the note's height anf 1/3 of it's width
         */
        int noteWidth = this.getWidth() / 3 * 2;
        int sharpWidth = noteWidth / 2;          // width of the '#'
        int noteHeight = this.getHeight();
        int sharpHeight = noteHeight / 3;        // height of the '#'

        Rect noteRect = new Rect();
        Rect sharpRect = new Rect();

        // All notes except H would be drawn straight, H is inverted
        if (noteOrder.get(tone) != 6){
            noteRect.left = sharpWidth;
            noteRect.right = noteRect.left + noteWidth;
            noteRect.top = noteHeight / 3;              // if its 'straight', drawing starts lower
            noteRect.bottom = noteHeight;
            if (isDiez) {
                sharpRect.left = 0;
                sharpRect.right = noteRect.left;
                sharpRect.top = noteHeight / 3;
                sharpRect.bottom = sharpRect.top + sharpHeight;
            }
        } else {                            //inverted position
            noteRect.left = sharpWidth;
            noteRect.bottom = noteHeight * 2/ 3;
            noteRect.right = noteRect.left + noteWidth;
            noteRect.top = 0;
            if(isDiez){
                sharpRect.left = 0;
                sharpRect.right = noteRect.left;
                sharpRect.top = noteHeight / 3;
                sharpRect.bottom = sharpRect.top + sharpHeight;
            }
        }

        //Draw note & sharp if necessary
        drawSVG(canvas, R.raw.note, noteRect);
        if(isDiez){
            drawSVG(canvas, R.raw.sharp, sharpRect);
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
