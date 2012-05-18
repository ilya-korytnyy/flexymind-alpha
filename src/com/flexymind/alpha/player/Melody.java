package com.flexymind.alpha.player;

import com.flexymind.alpha.R;
import com.flexymind.alpha.StaticResources;
import com.flexymind.alpha.parsers.MIDIParser;
import java.io.File;
import java.util.List;

public class Melody {

    public List<MidiNote> melodyNotes;

    public Melody(int id) {


        melodyNotes = MIDIParser.parse
                (StaticResources.res.openRawResource(id));
    }

    public int size() {

        return melodyNotes.size();
    }


    public List<MidiNote> subList(int turn, int upperBorder) {

        return melodyNotes.subList(turn, upperBorder);
    }
}
