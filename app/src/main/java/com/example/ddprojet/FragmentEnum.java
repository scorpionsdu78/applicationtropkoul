package com.example.ddprojet;

import androidx.fragment.app.Fragment;

public enum FragmentEnum {

    Race(0),
    Classe(1),
    CaracComp(2),
    CapaDons(3),
    Equip(4),
    Personnaliter(5);



    private int value;

    FragmentEnum(int _value){
        value = _value;
    }

    public int getValue() {
        return value;
    }
}
