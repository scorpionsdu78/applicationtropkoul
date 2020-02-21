package util;

import android.util.Pair;

public enum Requirement {



    Barbare("Strength"),
    Barde("Charisma"),
    Clerc("Wisdom"),
    Druide("Wisdom"),
    Ensorceleur("Charisma"),
    Guerrier("Strength","Dexterity"),
    Magicien("Intelligence"),
    Moine("Dexterity","Wisdom"),
    Paladin("Strength", "Charisma"),
    Rodeur("Dexterity","Wisdom"),
    Roublard("Dexterity"),
    Sorcier("Charisma");


    private String stat1;
    private String stat2;

    Requirement(String s) {
        stat1 = s;
    }

    Requirement(String s1, String s2){
        stat1 = s1;
        stat2 = s2;
    }

    public String getStat1() {
        return stat1;
    }

    public String getStat2() {
        return stat2;
    }
}
