package com.flexymind.alpha.player;

import com.flexymind.alpha.R;

import java.util.EnumMap;

/**
 * Represents a musical note with it's tone and duration.
 * Immutable
 */
public class Note {
    /*
     * no getters because of performance issues
     */
    private final Tone tone;
    private final int duration;
    private final boolean sharp;
    private final boolean flat;
    public static EnumMap<Tone, Integer> notesWithRaw;


    public Note(Tone tone, int duration, boolean sharp, boolean  flat) {

        this.tone = tone;
        this.duration = duration;
        this.sharp = sharp;
        this.flat = flat;

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

    private int isSharp() {

        return sharp ? 1 : 0;
    }

    private int isFlat() {

        return flat ? 0 : 1;
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

    public void drawNote() {
        //do smth stub
    }


}
