package com.flexymind.alpha.customviews;

import android.content.Context;
import android.graphics.Picture;
import com.flexymind.alpha.player.Note;

import static com.flexymind.alpha.customviews.PictureStorage.*;


public class NoteView extends ParentSelfDrawingView {

    public  static final int       UP         = 0;
    public  static final int       DOWN       = 1;
    public  static final int       SHARP_UP   = 2;
    public  static final int       SHARP_DOWN = 3;


    private        final Note      note;

    private              Picture[] currentColorArrayWithPictures;
    private              int       noteForm;

    private              int       topMarginCorrection ;


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

        super(context, (int) (noteWidth * 2.7), (int) (noteHeight * 3.2));

        this.note       = note;
        currentColorArrayWithPictures = blackPictures;

        setNoteFormAndCorrectionToTopMargin();
        setNotePicture();
    }




    private void setNoteFormAndCorrectionToTopMargin() {

        if (!isInverted() && !isSharp()) {

            topMarginCorrection = - height / 2 - height / 12;
            noteForm = UP;
        }else if (!isInverted() && isSharp()) {

            topMarginCorrection =  - height / 2 - height / 10;
            noteForm = SHARP_UP;
        }else  if (isInverted() && !isSharp()) {

            topMarginCorrection = - height / 5;
            noteForm = DOWN;
        }else if (isInverted() && isSharp()) {

            topMarginCorrection = - height / 5;
            noteForm = SHARP_DOWN;
        }
    }

    private void setNotePicture() {

        picture = currentColorArrayWithPictures[noteForm];
    }

    private boolean isInverted() {

        if (note.H.compareTo(note) < 0){

            return true ;
        }
        return false;
    }


    private boolean isSharp() {

        for (Note note : Note.getNotesForBlackKeys()) {

           if (this.note == note) {
               return true;
           }
        }

        return false;
    }

    /**
     * Makes it green
     */
    public void highlightGreen() {

        currentColorArrayWithPictures = greenPictures;
        setNotePicture();
    }

    /**
     * Makes it red
     */
    public void highlightRed() {

        currentColorArrayWithPictures = redPictures;
        setNotePicture();
    }

    /**
     * Makes it black again
     */
    public void highlightBlack() {

        currentColorArrayWithPictures = blackPictures;
        setNotePicture();
    }

    public int getTopMarginCorrection() {

        return topMarginCorrection;
    }

    private void delete() {

    }

}