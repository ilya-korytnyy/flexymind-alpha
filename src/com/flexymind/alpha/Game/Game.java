package com.flexymind.alpha.Game;

import android.content.Context;
import com.flexymind.alpha.R;
import com.flexymind.alpha.customviews.NoteBoard;
import com.flexymind.alpha.player.Melody;
import com.flexymind.alpha.player.MidiNote;
import com.flexymind.alpha.player.PianoPlayer;

import java.util.LinkedList;
import java.util.List;


public class Game {

    private List<MidiNote>          melodyPart;
    private int                     staveCapacity;
    private NoteBoard               noteBoard;
    private Melody                  currentMelody;
    private PianoPlayer             player;
    private LinkedList<PianoPlayer> playerList;
    private Context                 context;

    public Game(NoteBoard noteBoard, Context context) {

        this.noteBoard = noteBoard;
        this.context   = context;
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


        playerList = new LinkedList<PianoPlayer>();

        for(int i = 0; i < melodyPart.size(); i++) {
            player = new PianoPlayer( context
                                    , melodyPart.get(i).getMidiFileId());
            playerList.add(i, player);
        }

        for(int i = 0; i < melodyPart.size();) {
            playOwnSound(i);
        }
    }


    private void playOwnSound(final int i) {
        Thread soundThread = new Thread(new Runnable() {
            @Override
            public void run() {
                playerList.get(i).play();
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