package com.flexymind.alpha.customviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import com.flexymind.alpha.R;
import com.flexymind.alpha.player.Tone;
import com.larvalabs.svgandroid.SVG;
import android.graphics.Picture;
import com.larvalabs.svgandroid.SVGParser;

import java.util.EnumMap;

public class Note extends View {

    Tone tone;
    boolean sharp;
    private static EnumMap<Tone, Integer> noteOrder;

    /**
     *   Parameters of rectangle picture fits in
     */
    private class rectEdges{

        int left;
        int top;
        int right;
        int bottom;
    }

    public Note(Context context) {

        super(context);
        init();
    }

    public Note(Context context, AttributeSet attrs) {

        super(context, attrs);
        init();
    }

    public void setColor(Color color) {

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
    private void drawSVG(Canvas canvas, int source, rectEdges edges){

        SVG noteSvg = SVGParser.getSVGFromResource(getResources(), source);
        Picture notePicture = noteSvg.getPicture();
        Rect noteRect = new Rect(edges.left, edges.top, edges.right, edges.bottom);
        canvas.drawPicture(notePicture, noteRect);
    }

    /**
     * Compute note & sharp position in relation
     * to the stave. Draw note & sharp if necessary.
     * @param canvas canvas to draw on
     * @param note note to draw
     * @param margin left margin from display edge to the stave
     * @param lineMargin margin between lines
     * @param order number of note in current NoteBoard view
     */
    protected void onDraw(Canvas canvas, Note note, int margin, int lineMargin, int order) {

        //HARDCODE FOR TEST
        note.tone = Tone.A;     //+++++
        note.sharp = true;      //+++++
        //++++++++++++++++++

        int linesX = lineMargin*6+lineMargin/2;
        int linesY = margin+100+order*100;
        int noteWidth = lineMargin*2;
        int noteHeight = lineMargin*3;
        int sharpWidth = lineMargin;
        int sharpHeight = lineMargin*2;

        rectEdges noteRect = new rectEdges();
        rectEdges sharpRect = new rectEdges();

        //Notes till H would be drawn straight, rest of them inverted
        if ((order = noteOrder.get(note.tone))<6){
            noteRect.left = linesY;
            noteRect.bottom = linesX-lineMargin/2*order;
            noteRect.top = noteRect.bottom-noteHeight;
            noteRect.right = noteRect.left+noteWidth;
            if(note.sharp == true){
                sharpRect.right = noteRect.left;
                sharpRect.left = sharpRect.right-sharpWidth;
                sharpRect.top = noteRect.bottom+lineMargin/2;
                sharpRect.bottom = sharpRect.top-sharpHeight;
            }
        }
        else{                            //inverted position
            noteRect.left = linesY;
            noteRect.bottom = linesX-lineMargin/2*(order+2);
            noteRect.right = noteRect.left+noteWidth;
            noteRect.top = noteRect.bottom+noteHeight;
            if(note.sharp == true){
                sharpRect.right = noteRect.left;
                sharpRect.left = sharpRect.right-sharpWidth;
                sharpRect.top = noteRect.bottom-lineMargin/2;
                sharpRect.bottom = sharpRect.top+sharpHeight;
            }
        }

        //Draw note & sharp if necessary
        drawSVG(canvas, R.raw.note, noteRect);
        if(note.sharp == true){
            drawSVG(canvas, R.raw.sharp, sharpRect);
        }
    }
}
