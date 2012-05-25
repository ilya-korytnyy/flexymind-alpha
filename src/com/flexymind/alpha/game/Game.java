package com.flexymind.alpha.game;

import android.os.Handler;
import android.os.Message;
import com.flexymind.alpha.R;
import com.flexymind.alpha.customviews.NoteBoard;
import com.flexymind.alpha.player.Melody;
import com.flexymind.alpha.player.MidiNote;
import com.flexymind.alpha.player.PianoPlayer;

import java.util.List;

public class Game {

    static boolean played = false;

    private                 List<MidiNote>  melodyPart;
    private                 int             staveCapacity;
    private static NoteBoard       noteBoard;
    private                 Melody          currentMelody;
    private                 PianoPlayer     player;
    private static final    int             PART = 1;
    public static Handler colorHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);    //To change body of overridden methods use File | Settings | File Templates.

            noteBoard.highlightNote(3);

        }
    };

    public Game(NoteBoard noteBoard) {
        this.noteBoard = noteBoard;
    }

    public void gameStart() {
        setMelody();
        getStaveCapacity();
        drawMelodyPart(PART);
        playMelodyPart(PART);
    }


    private void setMelody() {
        currentMelody = new Melody(R.raw.goomidi);
    }

    private void getStaveCapacity() {

        staveCapacity = noteBoard.getHowMuchIWant();
    }

    private void drawMelodyPart(int part) {
        nextPartList(part);
        noteBoard.setShownNotes(melodyPart);
    }

    private void playMelodyPart(int part){
        player = new PianoPlayer(noteBoard.getContext());
        player.playJetMelody();
    }





    private void nextPartList(int part) {
        int upperBorder = staveCapacity;
        if(part * staveCapacity > currentMelody.size()) {

            upperBorder = currentMelody.size();
        }
        melodyPart = currentMelody.SubList(part - 1, upperBorder);
    }
}