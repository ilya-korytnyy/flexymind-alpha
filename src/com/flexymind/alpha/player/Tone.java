package com.flexymind.alpha.player;

public enum Tone {
    C,   // до
    D,   // ре
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

    public static Tone getToneById(int id){
        switch (id){
            case 57:
                return Tone.A;
            case 58:
                return Tone.Az;
            case 48:
                return Tone.C;
            case 49:
                return Tone.Cz;
            case 60:
                return Tone.C1;
            case 50:
                return Tone.D;
            case 51:
                return Tone.Dz;
            case 52:
                return Tone.E;
            case 53:
                return Tone.F;
            case 54:
                return Tone.Fz;
            case 55:
                return Tone.G;
            case 56:
                return Tone.Gz;
            case 59:
                return Tone.H;
            default:
                return Tone.UNKNOW;
        }
    }
}
