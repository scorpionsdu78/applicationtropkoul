package util;

import androidx.annotation.DrawableRes;

import com.example.ddprojet.R;


public enum ClassEnum {

    Barbarian(R.drawable.class_barbarian),
    Bard(R.drawable.class_bard),
    Cleric(R.drawable.class_cleric),
    Druid(R.drawable.class_druid),
    Fighter(R.drawable.class_fighter),
    Monk(R.drawable.class_monk),
    Paladin(R.drawable.class_paladin),
    Ranger(R.drawable.class_ranger),
    Rogue(R.drawable.class_rogue),
    Sorcerer(R.drawable.class_sorcerer),
    Warlock(R.drawable.class_warlock),
    Wizard(R.drawable.class_wizard),
    Default(R.drawable.class_default);



    private @DrawableRes int value;

    ClassEnum(@DrawableRes int _value){
        value = _value;
    }

    public @DrawableRes int getValue() {
        return value;
    }

    public static @DrawableRes int getValue(String raceName){
        switch (raceName.toLowerCase()){

            case "barbarian" :

                return Barbarian.getValue();


            case "bard" :

                return Bard.getValue();


            case "cleric" :

                return Cleric.getValue();


            case "druid" :

                return Druid.getValue();


            case "fighter" :

                return Fighter.getValue();


            case "monk" :

                return Monk.getValue();


            case "paladin" :

                return Paladin.getValue();


            case "ranger" :

                return Ranger.getValue();


            case "rogue" :

                return Rogue.getValue();


            case "sorcerer" :

                return Sorcerer.getValue();


            case "warlock" :

                return Warlock.getValue();


            case "wizard" :

                return Wizard.getValue();


            default:

                return Default.getValue();
        }
    }
}
