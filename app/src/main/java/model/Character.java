package model;

import androidx.annotation.DrawableRes;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class Character {

    protected String name;
    protected String race;
    protected String class_;
    protected Alignment alignment;
    protected int level;
    protected @DrawableRes int avatar;

    protected Map<String, Integer> characteristic;
    protected Map<String, Integer> skills;

    protected List<String> spells;

    protected List<String> proficiencies;
    protected List<String> languages;
    protected List<String> aptitudes;
    protected List<String> traits;

    protected String personality_traits;
    protected String ideals;
    protected String bonds;
    protected String flaws;

    public Character(){

        this.characteristic = new HashMap<>();
        this.skills = new HashMap<>();

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

        this.spells = new ArrayList<>();
        this.proficiencies = new ArrayList<>();
        this.languages = new ArrayList<>();
        this.aptitudes = new ArrayList<>();
        this.traits = new ArrayList<>();

    }

    public Character(String name, String race, String class_, Alignment alignment, int level, @DrawableRes int avatar,
                     Map<String, Integer> characteristic, Map<String, Integer> skills, Map<String, Integer> savingThrows,
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
        this.proficiencies = proficiencies;
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
        this.traits = traits;
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
}
