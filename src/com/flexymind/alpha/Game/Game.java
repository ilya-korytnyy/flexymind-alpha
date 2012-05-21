package com.flexymind.alpha.Game;

import android.view.View;
import com.flexymind.alpha.GameScreen;
import com.flexymind.alpha.R;
import com.flexymind.alpha.customviews.NoteBoard;
import com.flexymind.alpha.player.Melody;
import com.flexymind.alpha.player.MidiNote;
import com.flexymind.alpha.player.Note;
import com.flexymind.alpha.player.PianoPlayer;

import java.util.LinkedList;
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

        currentMelody = new Melody(R.raw.gooses);
    }

    private void getStaveCapacity() {

        staveCapacity = noteBoard.getHowMuchIWant();
    }

    private void drawMelodyPart(int part) {

        nextPartList(part);
        noteBoard.drawMelody(melodyPart);
    }

    private void playMelodyPart(int part){
        player = PianoPlayer.getInstance(noteBoard.getContext(), Note.C);
        //for (MidiNote midiNote : melodyPart){
        //    PianoPlayer pianoPlayer = PianoPlayer.getInstance(noteBoard.getContext(), midiNote.getNote());
        //    pianoPlayer.play();
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