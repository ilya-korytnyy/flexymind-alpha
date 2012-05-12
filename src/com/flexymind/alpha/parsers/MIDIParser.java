package com.flexymind.alpha.parsers;

import com.flexymind.alpha.player.Note;
import com.flexymind.alpha.player.Tone;
import com.leff.midi.MidiFile;
import com.leff.midi.event.MidiEvent;
import com.leff.midi.event.NoteOn;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;

/*
 * [review] mandrigin; it is bad to throw exceptions from the constructors. And it's to complicated.
 * It's simpler to make static methods:
 * Instead
 * try {
 *    parser = new MIDIParser(file);
 *    List<Notes> notes = parser.getNotes();
 * } ...
 *
 * Use
 *
 * try {
 *    List<Note> notes = MIDIParser.parse(file);
 * }
 *
 * Simplier, huh?
 *
*/
//[review] mandrigin: javadocs!
public class MIDIParser {
    /*
     * [review] mandrigin: DO NOT LEAVE THE COMMENTED-OUT CODE! We have VCS to bring changes back!
     */
//    protected final static int DEFAULT_DURATION = 480;

    protected List<Note> listNotes;


    public MIDIParser(File file) throws IOException{
        listNotes = new ArrayList<Note>();
        parseFile(new MidiFile(file));
    }

    public MIDIParser(InputStream file) throws IOException{
        listNotes = new ArrayList<Note>();
        parseFile(new MidiFile(file));
    }

    public List<Note> getNotes(){
        return listNotes;
    }

    private void parseFile(MidiFile mFile){
        SortedSet<MidiEvent> eventTreeSet = mFile.getTracks().get(1).getEvents();
        for (MidiEvent event : eventTreeSet) {
            //[review] too complicated. maybe it is better to have 'isSupported' method in the Tone class and
            // so the code will look like:
            // if((event instanceof NoteOn) && Tone.isSupported(event))
            // {
            //    Note note = new Note((Tone)event);
            // }
            if ((event instanceof NoteOn) && (Tone.getToneById(((NoteOn) event).getNoteValue()) != Tone.UNKNOW)) {
                Note note = new Note(Tone.getToneById(((NoteOn) event).getNoteValue()));
                listNotes.add(note);
            }
        }

    }

}
