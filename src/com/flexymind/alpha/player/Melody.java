package com.flexymind.alpha.player;

import com.flexymind.alpha.StaticResources;
import com.flexymind.alpha.parsers.MIDIParser;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class Melody {

    public List<MidiNote> midiList;
    public List<Note> noteList;

    public Melody(int id) {

        midiList = MIDIParser.parse
                (StaticResources.res.openRawResource(id));
        noteList = new LinkedList<Note>();
        getNoteListFromMidiList();
    }

    private void getNoteListFromMidiList() {

        for(MidiNote midiNote : midiList) {
            noteList.add(midiNote.getNote());
        }
    }

    public int size() {

        return midiList.size();
    }


    public List<MidiNote> midiSubList(int turn, int upperBorder) {

        return midiList.subList(turn, upperBorder);
    }

    public List<Note> noteSubList(int turn, int upperBorder) {

        return noteList.subList(turn, upperBorder);
    }
}
