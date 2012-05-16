package com.flexymind.alpha.player;

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
