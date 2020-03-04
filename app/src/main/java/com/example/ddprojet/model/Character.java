package com.example.ddprojet.model;

import android.util.Log;

import androidx.annotation.DrawableRes;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Character {

    protected String name;
    protected String race;
    @SerializedName("class")
    protected String class_;
    protected Alignment alignment;
    protected int level;
    protected @DrawableRes int avatar;

    protected int life;
    protected int hitDice;
    protected int speed;

    protected Map<String, Integer> characteristic;
    protected Map<String, Integer> skills;

    protected List<String> savingThrows;

    protected List<String> spells;

    protected List<String> proficiencies;
    protected List<String> languages;
    protected List<String> aptitudes;
    protected List<String> traits;

    protected String personality_traits;
    protected String ideals;
    protected String bonds;
    protected String flaws;

    private boolean alreadySetProf = false;
    private boolean allreadySetTrait = false;
    private boolean hasSpellCasting = false;

    private List<Feature> features;
    private List<Feature> featureChoose;

    public Character(){

        this.characteristic = new HashMap<>();
        this.skills = new HashMap<>();
        this.savingThrows = new ArrayList<>();

        this.spells = new ArrayList<>();
        this.proficiencies = new ArrayList<>();
        this.languages = new ArrayList<>();
        this.aptitudes = new ArrayList<>();
        this.traits = new ArrayList<>();

    }

    public Character(String name, String race, String class_, Alignment alignment, int level, @DrawableRes int avatar){

        this.name = name;
        this.race = race;
        this.class_ = class_;
        this.alignment = alignment;
        this.level = level;
        this.avatar = avatar;

        this.characteristic = new HashMap<>();
        this.skills = new HashMap<>();
        this.savingThrows = new ArrayList<>();

        this.spells = new ArrayList<>();
        this.proficiencies = new ArrayList<>();
        this.languages = new ArrayList<>();
        this.aptitudes = new ArrayList<>();
        this.traits = new ArrayList<>();

    }

    public Character(String name, String race, String class_, Alignment alignment, int level, @DrawableRes int avatar,
                     Map<String, Integer> characteristic, Map<String, Integer> skills, List<String> savingThrows,
                     List<String> spells, List<String> proficiencies, List<String> languages, List<String> aptitudes, List<String> traits,
                     String personality_traits, String ideals, String bonds, String flaws) {

        this.name = name;
        this.race = race;
        this.class_ = class_;
        this.alignment = alignment;
        this.level = level;
        this.avatar = avatar;

        this.characteristic = characteristic;
        this.skills = skills;
        this.savingThrows = savingThrows;

        this.spells = spells;
        this.proficiencies = proficiencies;
        this.languages = languages;
        this.aptitudes = aptitudes;
        this.traits = traits;

        this.personality_traits = personality_traits;
        this.ideals = ideals;
        this.bonds = bonds;
        this.flaws = flaws;

    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getHitDice() {
        return hitDice;
    }

    public void setHitDice(int hitDice) {
        this.hitDice = hitDice;
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
        Log.d("why","entrez dans le set race");
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

    public Map<String, Integer> getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(Map<String, Integer> characteristic) {
        this.characteristic = characteristic;
    }

    public Map<String, Integer> getSkills() {
        return skills;
    }

    public void setSkills(Map<String, Integer> skills) {
        this.skills = skills;
    }

    public List<String> getSavingThrows() {
        return savingThrows;
    }

    public void setSavingThrows(List<String> savingThrows) {
        this.savingThrows = savingThrows;
    }

    public boolean ContainsSavingThrows(String savingThrow) {
        return this.savingThrows.contains(savingThrow);
    }

    public List<String> getSpells() {
        return spells;
    }

    public void setSpells(List<String> spells) {
        this.spells = spells;
    }

    public List<String> getProficiencies() {
        return proficiencies;
    }

    public void setProficiencies(List<String> proficiencies) {
        if(!alreadySetProf) {
            this.proficiencies = proficiencies;
            alreadySetProf = true;
        }
        else {
            for (String p: proficiencies) {
                this.addProficiencies(new Proficiencies("",p));
            }
        }
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public List<String> getAptitudes() {
        return aptitudes;
    }

    public void setAptitudes(List<String> aptitudes) {
        this.aptitudes = aptitudes;
    }

    public List<String> getTraits() {
        return traits;
    }

    public void setTraits(List<String> traits) {
        if(!this.allreadySetTrait) {
            this.traits = traits;
            allreadySetTrait = true;
        }else{
            for (String s: traits) {
                this.traits.add(s);
            }
        }
    }

    public String getPersonality_traits() {
        return personality_traits;
    }

    public void setPersonality_traits(String personality_traits) {
        this.personality_traits = personality_traits;
    }

    public String getIdeals() {
        return ideals;
    }

    public void setIdeals(String ideals) {
        this.ideals = ideals;
    }

    public String getBonds() {
        return bonds;
    }

    public void setBonds(String bonds) {
        this.bonds = bonds;
    }

    public String getFlaws() {
        return flaws;
    }

    public void setFlaws(String flaws) {
        this.flaws = flaws;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void addProficiencies(Proficiencies p){
        this.proficiencies.add(p.getName());
    }

    public void addTrait(Trait t){
        this.traits.add(t.name);
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }

    public List<Feature> getFeatureChoose() {
        return featureChoose;
    }

    public void setFeatureChoose(List<Feature> featureChoose) {
        this.featureChoose = featureChoose;
    }

    public boolean isHasSpellCasting() {
        return hasSpellCasting;
    }

    public void setHasSpellCasting(boolean hasSpellCasting) {
        this.hasSpellCasting = hasSpellCasting;
    }
}