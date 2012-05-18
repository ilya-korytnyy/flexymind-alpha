package com.flexymind.alpha;

import com.flexymind.alpha.player.Melody;
import com.flexymind.alpha.player.MidiNote;
import java.util.LinkedList;
import java.util.List;

public class Gamer {

    private List<MidiNote> notesList;
    private List<MidiNote> melodyPart;
    private int staveCapacity;

    public void gameStart(){

        setMelody();
        getStaveCapacity();//notes stave dimension
        drawMelody();
    }

    private void setMelody(){

         Melody currentMelody = new Melody(melodyFile);      //parse file from resources
         notesList = currentMelody.melodyNotes;
    }

    private void getStaveCapacity(){
         //
    }

    private void drawMelody(){
        //
    }

    private List<MidiNote> nextTurnList(int turn){

        int upperBorder = staveCapacity;
        if(turn*staveCapacity>notesList.size()){
            upperBorder = notesList.size();
        }
        melodyPart = notesList.subList(turn,upperBorder);

        return melodyPart;
    }

}
