package com.example.ddprojet.util;

public enum Requirement {



    Barbare("STR"),
    Barde("CHA"),
    Clerc("WIS"),
    Druide("WIS"),
    Ensorceleur("CHA"),
    Guerrier("STR","DEX"),
    Magicien("INT"),
    Moine("DEX","WIS"),
    Paladin("STR", "CHA"),
    Rodeur("DEX","WIS"),
    Roublard("DEX"),
    Sorcier("CHA");


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
