package com.flexymind.alpha.parsers;

import com.flexymind.alpha.player.MidiNote;
import com.flexymind.alpha.player.Note;
import com.leff.midi.MidiFile;
import com.leff.midi.event.MidiEvent;
import com.leff.midi.event.NoteOn;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;

/**
 * Class for parsing a .midi file to {@code List<MidiNote>}
 */
public class MIDIParser {

    private static List<MidiNote> listNotes;

    /**
     * Parses midi file from specified File
     *
     * @param file
     * @return List of MidiNotes, or {@code null} if there was an error.
     */
    public static List<MidiNote> parse(File file) {

        listNotes = null;
        try {
            listNotes = parseFile(new MidiFile(file));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return listNotes;
        }
    }

    /**
     * Parses midi file from specified InputStream
     *
     * @param stream
     * @return List of MidiNotes, or {@code null} if there was an error.
     */
    public static List<MidiNote> parse(InputStream stream) {

        listNotes = null;
        try {
            listNotes = parseFile(new MidiFile(stream));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return listNotes;
        }
    }

    private static List<MidiNote> parseFile(MidiFile mFile) {

        ArrayList<MidiNote> tempList = new ArrayList<MidiNote>();

        SortedSet<MidiEvent> eventTreeSet = mFile.getTracks().get(1).getEvents();
        for (MidiEvent event : eventTreeSet) {
            if (isCorrectMidiNote(event)) {
                MidiNote note = new MidiNote(Note.getToneByMidiFileId(((NoteOn) event).getNoteValue()));
                tempList.add(note);
            }
        }

        return tempList;
    }

    private static boolean isCorrectMidiNote(MidiEvent event) {

        if (!(event instanceof NoteOn)) {
            return false;
        }

        Note note = Note.getToneByMidiFileId(((NoteOn) event).getNoteValue());
        return note != Note.UNKNOW;
    }
}
