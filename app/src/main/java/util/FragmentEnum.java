package util;

import androidx.fragment.app.Fragment;

public enum FragmentEnum {

    RaceClassSelection(0),
    Race(1),
    Class(2),
    CaracComp(3),
    CapaDons(4),
    EquipSpell(5),
    Personnalite(6);



    private int value;

    FragmentEnum(int _value){
        value = _value;
    }

    public int getValue() {
        return value;
    }
}
