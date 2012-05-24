package com.flexymind.alpha.player;

import com.flexymind.alpha.StaticResources;
import com.flexymind.alpha.parsers.MIDIParser;
import java.util.List;

public class Melody {

    public List<MidiNote> midiList;

    public Melody(int id) {

        midiList = MIDIParser.parse
                (StaticResources.res.openRawResource(id));
    }

    public int size() {

        return midiList.size();
    }

    public List<MidiNote> SubList(int turn, int upperBorder) {

        return midiList.subList(turn, upperBorder);
    }
}
