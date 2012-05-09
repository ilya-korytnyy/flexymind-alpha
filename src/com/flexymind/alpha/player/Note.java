package com.flexymind.alpha.player;

/**
 * Represents a musical note with it's tone, octave and duration.
 * Immutable
 */
public class Note {
    /*
     * no getters because of performance issues
     */
    public final Tone tone;
    public final int duration;

    public Note(Tone tone, int duration) {
        this.tone = tone;
        this.duration = duration;
    }

    /** Get mid file of tone
     *  @return mid file (R.raw."mid file")
     * */
    public int getTone() {
        return 0;
    }
}
