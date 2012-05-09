package com.flexymind.alpha.player;

/**
 * Represents a musical note with it's tone, octave and duration.
 * Immutable
 *
 * @author Ilya Koritniy
 */
public class Note {
    /*
     * no getters because of performance issues
     */
    public final Tone tone;
    public final Octave octave;
    public final int duration;

    public Note(Tone tone, Octave octave, int duration) {
        this.tone = tone;
        this.octave = octave;
        this.duration = duration;
    }

    /** Get mid file of tone
     *  @return mid file (R.raw."mid file")
     * */
    public int getTone() {
        return 0;
    }
}
