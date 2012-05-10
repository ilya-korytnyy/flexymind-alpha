package com.flexymind.alpha.player;

import com.flexymind.alpha.R;

import java.util.EnumMap;

/**
 * Represents a musical note with it's tone.
 * Immutable
 */
public class Note {
    /*
     * no getters because of performance issues
     */
    private final Tone tone;
    public static EnumMap<Tone, Integer> notesWithRaw;


    public Note(Tone tone) {

        this.tone = tone;

        notesWithRaw = new EnumMap<Tone, Integer>(Tone.class);

        initializeMap();
    }

    private void initializeMap() {

        notesWithRaw.put(Tone.C, R.raw.c);
        notesWithRaw.put(Tone.Cz, R.raw.cdiez);
        notesWithRaw.put(Tone.D, R.raw.d);
        notesWithRaw.put(Tone.Dz, R.raw.ddiez);
        notesWithRaw.put(Tone.E, R.raw.e);
        notesWithRaw.put(Tone.F, R.raw.f);
        notesWithRaw.put(Tone.Fz, R.raw.fdiez);
        notesWithRaw.put(Tone.G, R.raw.g);
        notesWithRaw.put(Tone.Gz, R.raw.gdiez);
        notesWithRaw.put(Tone.A, R.raw.a);
        notesWithRaw.put(Tone.Az, R.raw.adiez);
        notesWithRaw.put(Tone.H, R.raw.h);
        notesWithRaw.put(Tone.C1, R.raw.c1);
    }

    private Tone getTone() {

        return this.tone;
    }

    /**
     *
     * @return R.raw.smthng
     */
    public int getRawName() {

        return notesWithRaw.get(getTone());
    }
}
