package com.flexymind.alpha.player;

import com.flexymind.alpha.R;

public enum Note {
    C   (R.raw.c, R.raw.noteup),   // до
    Cz  (R.raw.cdiez, R.raw.sharpnoteup),   // до диез
    D   (R.raw.d, R.raw.noteup),   // ре
    Dz  (R.raw.ddiez, R.raw.sharpnoteup),   // ре диез
    E   (R.raw.e, R.raw.noteup),   // ми
    F   (R.raw.f, R.raw.noteup),   // фа
    Fz  (R.raw.fdiez, R.raw.sharpnoteup),   // фа диез
    G   (R.raw.g, R.raw.noteup),   // соль
    Gz  (R.raw.gdiez, R.raw.sharpnoteup),   // соль диез
    A   (R.raw.a, R.raw.noteup),   // ля
    Az  (R.raw.adiez, R.raw.sharpnoteup),   // ля диез
    H   (R.raw.h, R.raw.notedown),   // си
    C1  (R.raw.c1, R.raw.notedown),   // до
    UNKNOW(-1, -1);

    public final int soundResource;
    public final int blackSvgResource;

    private Note(int soundResource, int blackSvgResource) {
        this.soundResource = soundResource;
        this.blackSvgResource = blackSvgResource;
    }

    //[review] mandrgin: strange name... it points to the MIDI file.
    //so  it better be called as 'getMidiFileId()'
    ////[review] mandrigin: javadocs for public methods!
    public static Note getToneById(int id){
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

    private static Note[] blackTone = null;
    private static Note[] whiteTone = null;
    private static Note[] allNotes  = null;

    public static Note[] getNotesForBlackKeys() {
        if(blackTone == null){
            blackTone =  new Note[] {Cz, Dz, Fz, Gz, Az };
        }

        return blackTone;
    }

    public static Note[] getNotesForWhiteKeys() {
        if(whiteTone == null){
            whiteTone = new Note[] {C, D, E, F, G, A, H, C1};
        }
        return whiteTone;
    }
}
