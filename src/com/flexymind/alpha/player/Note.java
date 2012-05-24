package com.flexymind.alpha.player;

import java.util.Arrays;

public enum Note {

    C,      // до
    Cz,     // до диез
    D,      // ре
    Dz,     // ре диез
    E,      // ми
    F,      // фа
    Fz,     // фа диез
    G,      // соль
    Gz,     // соль диез
    A,      // ля
    Az,     // ля диез
    H,      // си
    C1,     // до
    UNKNOW;

    private static Note[] blackNotes = new Note[] { Cz, Dz, Fz, Gz, Az };
    private static Note[] whiteNotes = new Note[] { C, D, E, F, G, A, H, C1 };
    private static Note[] allNotes  = new Note[] { C, Cz, D, Dz, E, F, Fz, G, Gz, A, Az, H, C1 };

    /**
     * Returns the tone associated with the specified {@code id}. ID is set up in Midi parsing library.
     *
     * @param id ID of the Note
     * @return The Note
     */
    public static Note getToneByMidiFileId(int id){

        switch (id){
            case 57:
                return Note.A;
            case 58:
                return Note.Az;
            case 48:
                return Note.C;
            case 49:
                return Note.Cz;
            case 60:
                return Note.C1;
            case 50:
                return Note.D;
            case 51:
                return Note.Dz;
            case 52:
                return Note.E;
            case 53:
                return Note.F;
            case 54:
                return Note.Fz;
            case 55:
                return Note.G;
            case 56:
                return Note.Gz;
            case 59:
                return Note.H;
            default:
                return Note.UNKNOW;
        }
    }

    public static Note[] getNotesForBlackKeys() {
        return blackNotes;
    }

    public static Note[] getNotesForWhiteKeys() {
        return whiteNotes;
    }

    public static Note[] getNotesForAllKeys() {
        return allNotes;
    }
}
