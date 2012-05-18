package com.flexymind.alpha.Game;

import com.flexymind.alpha.R;
import com.flexymind.alpha.customviews.NoteBoard;
import com.flexymind.alpha.player.Melody;
import com.flexymind.alpha.player.MidiNote;
import java.util.List;

public class Game {

    private List<MidiNote> melodyPart;
    private int staveCapacity;
    private NoteBoard noteBoard;
    private Melody currentMelody;


    public Game(NoteBoard noteBoard) {

        this.noteBoard = noteBoard;
    }

    public void gameStart() {

        setMelody();
        getStaveCapacity();
        drawMelody();
    }

    private void setMelody() {

        currentMelody = new Melody(R.raw.song);
    }

    private void getStaveCapacity() {

        staveCapacity = noteBoard.getHowMuchIWant();
    }

    private void drawMelody() {


    }

    private List<MidiNote> nextTurnList(int turn) {

        int upperBorder = staveCapacity;

        if(turn * staveCapacity > currentMelody.size()) {

            upperBorder = currentMelody.size();
        }

        melodyPart = currentMelody.subList(turn, upperBorder);

        return melodyPart;
    }

}