package com.flexymind.alpha.Game;

import com.flexymind.alpha.R;
import com.flexymind.alpha.customviews.NoteBoard;
import com.flexymind.alpha.player.Melody;
import com.flexymind.alpha.player.MidiNote;
import com.flexymind.alpha.player.Note;

import java.util.List;

public class Game {

    private List<MidiNote> melodyPartForPlayer;
    private List<Note> melodyPartForNoteboard;
    private int staveCapacity;
    private NoteBoard noteBoard;
    private Melody currentMelody;


    public Game(NoteBoard noteBoard) {

        this.noteBoard = noteBoard;
    }

    public void gameStart() {

        setMelody();
        getStaveCapacity();
        nextPartList(1);
        drawMelodyPart();
    }

    private void setMelody() {

        currentMelody = new Melody(R.raw.song);
    }

    private void getStaveCapacity() {

        staveCapacity = noteBoard.getHowMuchIWant();
    }

    private void drawMelodyPart() {

        noteBoard.drawMelody(melodyPartForNoteboard);
                
    }

    private void nextPartList(int part) {

        int upperBorder = staveCapacity;

        if(part * staveCapacity > currentMelody.size()) {

            upperBorder = currentMelody.size();
        }

        melodyPartForPlayer= currentMelody.midiSubList(part,upperBorder);
        melodyPartForNoteboard = currentMelody.noteSubList(part,upperBorder);
    }



}