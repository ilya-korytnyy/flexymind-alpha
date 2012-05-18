package com.flexymind.alpha.player;

import com.flexymind.alpha.parsers.MIDIParser;
import java.io.File;
import java.util.List;


public class Melody {

    public List<MidiNote> melodyNotes;

    public Melody(File melodyFile){

        melodyNotes = MIDIParser.parse(melodyFile);
    }
}
