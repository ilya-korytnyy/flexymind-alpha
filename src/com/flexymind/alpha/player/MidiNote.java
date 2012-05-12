package com.flexymind.alpha.player;

import com.flexymind.alpha.R;

import java.util.EnumMap;

/**
 * Represents a musical note with it's note.
 * Immutable
 */
public class MidiNote {
    /*
     * no getters because of performance issues
     */
    private final Note note;
    public static EnumMap<Note, Integer> notesWithRaw;


    public MidiNote(Note note) {

        this.note = note;

        notesWithRaw = new EnumMap<Note, Integer>(Note.class);

        initializeMap();
    }

    private void initializeMap() {

        notesWithRaw.put(Note.C, R.raw.c);
        notesWithRaw.put(Note.Cz, R.raw.cdiez);
        notesWithRaw.put(Note.D, R.raw.d);
        notesWithRaw.put(Note.Dz, R.raw.ddiez);
        notesWithRaw.put(Note.E, R.raw.e);
        notesWithRaw.put(Note.F, R.raw.f);
        notesWithRaw.put(Note.Fz, R.raw.fdiez);
        notesWithRaw.put(Note.G, R.raw.g);
        notesWithRaw.put(Note.Gz, R.raw.gdiez);
        notesWithRaw.put(Note.A, R.raw.a);
        notesWithRaw.put(Note.Az, R.raw.adiez);
        notesWithRaw.put(Note.H, R.raw.h);
        notesWithRaw.put(Note.C1, R.raw.c1);
    }

    private Note getNote() {

        return this.note;
    }

    /**
     *
     * @return R.raw.smthng
     */
    public int getRawName() {

        return notesWithRaw.get(getNote());
    }
}
