package com.flexymind.alpha.player;

import com.flexymind.alpha.R;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class NoteTest extends TestCase {

    private Note noteC;
    private Note noteC1;
    private Note noteFz;

    @org.junit.Before
    public void setUp() throws Exception {

        super.setUp();

        noteC = new Note(Tone.C);
        noteC1 = new Note(Tone.C1);
        noteFz = new Note(Tone.Fz);
    }

    @org.junit.After
    public void tearDown() throws Exception {

        super.tearDown();

        noteC = null;
        noteC1 = null;
        noteFz = null;
    }

    public void testGetRawName() throws Exception {

        assertEquals(noteC.getRawName(), R.raw.c);
        assertEquals(noteC1.getRawName(), R.raw.c1);
        assertEquals(noteFz.getRawName(), R.raw.fdiez);
        // test 4 fail
        //assertEquals(noteFz.getRawName(), R.raw.gdiez);
    }

    public NoteTest(String name) {

        super(name);
    }

    public static Test suite() {

        TestSuite suite = new TestSuite();
        suite.addTest(new NoteTest("testGetRawName"));
        return suite;
    }
}
