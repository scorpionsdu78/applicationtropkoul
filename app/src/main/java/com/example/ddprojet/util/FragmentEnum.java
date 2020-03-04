package com.example.ddprojet.util;

import androidx.annotation.IdRes;

import com.example.ddprojet.R;

public enum FragmentEnum {

    RaceClassSelection(0, 0, R.id.nav_raceClass),
    Race(1, 0, R.id.nav_raceClass),
    Class(2, 0, R.id.nav_raceClass),
    BonusesSelection(3, 1, R.id.nav_bonusesSelection),
    CharacSkills(4, 2, R.id.nav_characSkills),
    Spells(5, 3, R.id.nav_Spells),
    Description(6, 4, R.id.nav_description);



    private int value;
    private int navItemPos;
    private @IdRes int navItemId;

    FragmentEnum(int value, int navItemPos, @IdRes int navItemId){
        this.value = value;
        this.navItemPos = navItemPos;
        this.navItemId = navItemId;
    }

    public int getValue() {
        return value;
    }

    public @IdRes int getNavItemPos(){
        return this.navItemPos;
    }

    public @IdRes int getNavItemId(){
        return this.navItemId;
    }
}
