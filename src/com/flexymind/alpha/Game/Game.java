package com.flexymind.alpha.Game;

import com.flexymind.alpha.R;
import com.flexymind.alpha.customviews.NoteBoard;
import com.flexymind.alpha.player.Melody;
import com.flexymind.alpha.player.MidiNote;
import com.flexymind.alpha.player.PianoPlayer;

import java.util.List;

public class Game {

    private List<MidiNote> melodyPart;
    private int staveCapacity;
    private NoteBoard noteBoard;
    private Melody currentMelody;
    private PianoPlayer player;


    public Game(NoteBoard noteBoard) {

        this.noteBoard = noteBoard;
    }

    public void gameStart() {

        setMelody();
        getStaveCapacity();
        drawMelodyPart(1);
        playMelodyPart(1);
    }

    private void setMelody() {

        currentMelody = new Melody(R.raw.song);
    }

    private void getStaveCapacity() {

        staveCapacity = noteBoard.getHowMuchIWant();
    }

    private void drawMelodyPart(int part) {

        nextPartList(part);
        noteBoard.setShownNotes(melodyPart);
    }

    private void playMelodyPart(int part){
        player = new PianoPlayer(noteBoard.getContext(), R.raw.song);
        //for (MidiNote midiNote : melodyPart){
        //    PianoPlayer pianoPlayer = new PianoPlayer(noteBoard.getContext(), R.raw.gooses);
          //  pianoPlayer.play();
        //}
        playOwnSound();

    }

    public void playOwnSound() {

        Thread soundThread = new Thread(new Runnable() {
            @Override
            public void run() {
                player.play();
            }
        });
        soundThread.start();
    }

    private void nextPartList(int part) {

        int upperBorder = staveCapacity;

        if(part * staveCapacity > currentMelody.size()) {

            upperBorder = currentMelody.size();
        }

        melodyPart = currentMelody.SubList(part - 1, upperBorder);
    }
}