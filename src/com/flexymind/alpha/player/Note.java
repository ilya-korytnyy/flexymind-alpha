package com.flexymind.alpha.player;

public enum Note {
    C,   // до
    D,   // ре                       // TODO: refactor (D=1)
    E,   // ми
    F,   // фа
    G,   // соль
    A,   // ля
    H,   // си
    Az,  // ля диез
    Cz,  // до диез
    Dz,  // ре диез
    Fz,  // фа диез
    Gz,  // соль диез
    C1,   // до
    UNKNOW;

    /**
     * For MIDI labrary
     * @param id
     * @return
     */
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
}
