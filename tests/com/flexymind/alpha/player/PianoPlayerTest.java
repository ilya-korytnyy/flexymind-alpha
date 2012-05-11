package com.flexymind.alpha.player;



import android.media.SoundPool;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.After;
import org.junit.Before;


public class PianoPlayerTest extends TestCase {
    public SoundPool testSoundPool;
    public  int      testToneID;
    private Note     testNote;

    @Before
    public void setUp() throws Exception {
        super.setUp();

    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    public void testPlay() throws Exception {

    }

    public PianoPlayerTest(String name) {
        super(name);
    }

    public static Test suite() {
        TestSuite suite = new TestSuite();
        suite.addTest(new PianoPlayerTest("testPlay"));
        return suite;
    }
}
