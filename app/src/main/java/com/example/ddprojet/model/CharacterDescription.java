package com.example.ddprojet.model;

import androidx.annotation.DrawableRes;

public class CharacterDescription {

    protected String name;
    protected String race;
    protected String class_;
    protected Alignment alignment;
    protected int level;
    protected @DrawableRes int avatar;


    public CharacterDescription() {}

    public CharacterDescription(String name, String race, String class_, Alignment alignment, int level, @DrawableRes int avatar) {
        this.name = name;
        this.race = race;
        this.class_ = class_;
        this.alignment = alignment;
        this.level = level;
        this.avatar = avatar;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getClass_() {
        return class_;
    }

    public void setClass_(String class_) {
        this.class_ = class_;
    }

    public Alignment getAlignment() {
        return alignment;
    }

    public void setAlignment(Alignment alignment) {
        this.alignment = alignment;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(@DrawableRes int avatar) {
        this.avatar = avatar;
    }
}
