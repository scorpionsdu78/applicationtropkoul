package util;

import androidx.annotation.DrawableRes;

import com.example.ddprojet.R;


public enum RaceEnum {

    Dragonborn(R.drawable.race_dragonborn),
    Dwarf(R.drawable.race_dwarf),
    Elf(R.drawable.race_elf),
    Gnome(R.drawable.race_gnome),
    Half_Elf(R.drawable.race_half_elf),
    Half_Orc(R.drawable.race_half_orc),
    Halfling(R.drawable.race_halfling),
    Human(R.drawable.race_human),
    Tiefling(R.drawable.race_tiefling),
    Default(R.drawable.race_default);


    private @DrawableRes int value;

    RaceEnum(@DrawableRes int _value){
        value = _value;
    }

    public @DrawableRes int getValue() {
        return value;
    }

    public static @DrawableRes int getValue(String raceName){

        if(raceName == null)
            return Default.getValue();


        switch (raceName.toLowerCase()){

            case "dragonborn" :

                return Dragonborn.getValue();


            case "dwarf" :

                return Dwarf.getValue();


            case "elf" :

                return Elf.getValue();


            case "gnome" :

                return Gnome.getValue();


            case "half-elf" :

                return Half_Elf.getValue();


            case "half-orc" :

                return Half_Orc.getValue();


            case "halfling" :

                return Halfling.getValue();


            case "human" :

                return Human.getValue();


            case "tiefling" :

                return Tiefling.getValue();


            default:

                return Default.getValue();
        }
    }
}
