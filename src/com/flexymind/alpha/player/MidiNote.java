package com.flexymind.alpha.player;

import com.flexymind.alpha.R;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

/**
 * Represents a musical note with it's sound sample.
 * Immutable
 */
public class MidiNote {
    /*
     * no getters because of performance issues
     */
    private final Note note;
    public static final Map<Note, Integer> midiByToneMap;

    static {

        Map<Note, Integer> tempMap = new EnumMap<Note, Integer>(Note.class);
        initializeMap(tempMap);
        midiByToneMap = Collections.unmodifiableMap(tempMap);
    }

    private static void initializeMap(Map<Note, Integer> tempMap) {

        tempMap.put(Note.C,  R.raw.c);
        tempMap.put(Note.Cz, R.raw.cdiez);
        tempMap.put(Note.D,  R.raw.d);
        tempMap.put(Note.Dz, R.raw.ddiez);
        tempMap.put(Note.E,  R.raw.e);
        tempMap.put(Note.F,  R.raw.f);
        tempMap.put(Note.Fz, R.raw.fdiez);
        tempMap.put(Note.G,  R.raw.g);
        tempMap.put(Note .Gz, R.raw.gdiez);
        tempMap.put(Note.A,  R.raw.a);
        tempMap.put(Note.Az, R.raw.adiez);
        tempMap.put(Note.H,  R.raw.h);
        tempMap.put(Note.C1, R.raw.c1);
    }

    public MidiNote(Note note) {

        this.note = note;
    }

    /**
     * Method gets MidiFileID
     * @return resource identifier (R.raw.smthng)
     */
    public int getMidiFileId() {

        return midiByToneMap.get(note);
    }

    public Note getNote() {

        return note;
    }
}
