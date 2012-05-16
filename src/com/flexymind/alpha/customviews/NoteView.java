package com.flexymind.alpha.customviews;

import android.content.Context;
import android.graphics.*;
import com.flexymind.alpha.player.Note;

import static com.flexymind.alpha.customviews.
              PictureStorage.blackPictures;
import static com.flexymind.alpha.customviews.
              PictureStorage.greenPictures;
import static com.flexymind.alpha.customviews.
              PictureStorage.redPictures;


public class NoteView extends ParentSelfDrawingView {

    public  final static int       UP         = 0;
    public  final static int       DOWN       = 1;
    public  final static int       SHARP_UP   = 2;
    public  final static int       SHARP_DOWN = 3;

    private final        Note      note;

    private              Picture[] currentColorArrayWithPictures;
    private              int       noteForm;


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

        super(context, noteWidth * 2, noteHeight * 3);

        this.note       = note;
        currentColorArrayWithPictures = blackPictures;

        setNoteForm();
        setNotePicture();
    }


    private void setNoteForm() {

        if (!isInverted() && !isSharp()) {

            noteForm = UP;
        }else if (!isInverted() && isSharp()) {

            noteForm = SHARP_UP;
        }else  if (isInverted() && !isSharp()) {

            noteForm = DOWN;
        }else if (isInverted() && isSharp()) {

            noteForm = SHARP_DOWN;
        }
    }

    private void setNotePicture() {

        picture = currentColorArrayWithPictures[noteForm];
    }

    private boolean isInverted() {

        return true;
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
    public void highlight() {

        currentColorArrayWithPictures = greenPictures;
        setNotePicture();
    }

    /**
     * Makes it red
     */
    public void highlightError() {

        currentColorArrayWithPictures = redPictures;
        setNotePicture();
    }

    /**
     * Makes it black again
     */
    public void unHighlight() {

        currentColorArrayWithPictures = blackPictures;
        setNotePicture();
    }
}