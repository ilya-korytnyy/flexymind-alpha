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
    ////[review] mandrigin: bad name... midiByToneMap is better.
    public static EnumMap<Tone, Integer> notesWithRaw;


    public Note(Tone tone) {

        this.tone = tone;

        notesWithRaw = new EnumMap<Tone, Integer>(Tone.class);

        initializeMap();
    }

    //[review] mandrigin: is it called on every note creation? if you need to initialize static fields,
    // I think it's better to use static constrictors:
    // http://www.snippetit.com/2009/05/java-static-variables-static-methods-and-static-constructor/
    // And it is better to make it unmodifiable: http://stackoverflow.com/a/507621/1084240
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

    //[review] mandrigin: do you really need this method?
    private Tone getTone() {

        return this.tone;
    }

    //[review] mandrgin: strange name... it points to the MIDI file.
    //so  it better be called as 'getMidiFileId()'
    ////[review] mandrigin: javadocs for public methods!
    /**
     *
     * @return R.raw.smthng
     */
    public int getRawName() {

        return notesWithRaw.get(getTone());
    }
}
