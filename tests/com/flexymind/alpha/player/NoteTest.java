package com.flexymind.alpha.player;

import com.flexymind.alpha.R;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class NoteTest extends TestCase {

    private MidiNote noteC;
    private MidiNote noteC1;
    private MidiNote noteFz;

    @org.junit.Before
    public void setUp() throws Exception {

        super.setUp();

        noteC = new MidiNote(Note.C);
        noteC1 = new MidiNote(Note.C1);
        noteFz = new MidiNote(Note.Fz);
    }

    @org.junit.After
    public void tearDown() throws Exception {

        super.tearDown();

        noteC = null;
        noteC1 = null;
        noteFz = null;
    }

    public void testGetRawName() throws Exception {

        assertEquals(noteC.getMidiFileId(), R.raw.c);
        assertEquals(noteC1.getMidiFileId(), R.raw.c1);
        assertEquals(noteFz.getMidiFileId(), R.raw.fdiez);
        // test 4 fail
        //assertEquals(noteFz.getRawName(), R.raw.gdiez);
    }

    public NoteTest(String name) {

        super(name);
    }

    public static Test suite() {

        TestSuite suite = new TestSuite();
        suite.addTest(new NoteTest("testGetMidiFileId"));
        return suite;
    }
}
