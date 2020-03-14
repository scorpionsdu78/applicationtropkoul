package com.example.ddprojet.model;

import androidx.annotation.DrawableRes;

import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Character implements Serializable {

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

    protected transient List<String> spells;

    protected List<String> proficiencies;
    protected List<String> languages;
    protected List<String> aptitudes;
    protected List<String> traits;

    protected String personality_traits;
    protected String background;

    private transient boolean alreadySetProf = false;
    private transient boolean allreadySetTrait = false;
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

    public Character(JSONObject file) throws JSONException, IOException {

        JSONObject tmp = file.getJSONObject("alignment");
        this.alignment = new Alignment(tmp.getString("goodEvilAxis"),tmp.getString("lawfulChaoticAxis"));

        JSONArray array = file.getJSONArray("aptitudes");
        this.aptitudes = new ArrayList<>();
        for (int i=0; i<array.length(); i++) {
            this.aptitudes.add(array.getString(i));
        }

        this.avatar = file.getInt("avatar");

        this.background = file.getString("background");

        this.characteristic = new HashMap<>();
        tmp = file.getJSONObject("characteristic");
        this.characteristic.put("CON",new Integer(tmp.getInt("CON")));
        this.characteristic.put("CHA",new Integer(tmp.getInt("CHA")));
        this.characteristic.put("WIS",new Integer(tmp.getInt("WIS")));
        this.characteristic.put("STR",new Integer(tmp.getInt("STR")));
        this.characteristic.put("DEX",new Integer(tmp.getInt("DEX")));
        this.characteristic.put("INT",new Integer(tmp.getInt("INT")));

        this.class_ = file.getString("class");

        array = file.getJSONArray("features");
        this.features = new ArrayList<Feature>();
        for(int i=0; i<array.length(); i++){
            JSONObject obj = array.getJSONObject(i);
            this.features.add(new Feature(obj.getString("name"),obj.getString("Desc")));
        }

        this.hitDice = file.getInt("hitDice");

        this.languages = new ArrayList<>();
        array = file.getJSONArray("languages");
        for(int i=0; i<array.length(); i++){
            languages.add(array.getString(i));
        }

        this.level = file.getInt("level");

        this.life = file.getInt("life");

        this.personality_traits = file.getString("personality_traits");

        this.proficiencies = new ArrayList<>();
        array = file.getJSONArray("proficiencies");
        for (int i=0; i<array.length(); i++)
        {
            proficiencies.add(array.getString(i));
        }

        this.race = file.getString("race");

        this.savingThrows = new ArrayList<>();
        array = file.getJSONArray("savingThrows");
        for(int i =0; i<array.length(); i++){
            savingThrows.add(array.getString(i));
        }

        this.skills = new HashMap<String, Integer>();
        tmp = file.getJSONObject("skills");
        skills.put("Nature",new Integer(tmp.optInt("Nature")));
        skills.put("Medicine", new Integer(tmp.optInt("Medicine")));
        skills.put("Survival", new Integer(tmp.optInt("Survival")));
        skills.put("Perception", new Integer(tmp.optInt("Perception")));
        if(tmp.opt("Stealth") != null){
            skills.put("Stealth", new Integer(tmp.getInt("Stealth")));
        }
        else {
            skills.put("Stealth", new Integer(0));
        }
        skills.put("Insight", new Integer( tmp.optInt("Insight")));
        skills.put("Deception", new Integer(tmp.optInt("Deception")));
        skills.put("Sleight of Hand", new Integer(tmp.optInt("Sleight of Hand")));
        skills.put("Investigation", new Integer(tmp.optInt( "Investigation")));
        skills.put("Performance", new Integer(tmp.optInt("Performance")));
        skills.put("Acrobatics", new Integer(tmp.optInt("Acrobatics")));
        skills.put("Religion", new Integer(tmp.optInt("Religion")));
        skills.put("Arcana", new Integer(tmp.optInt("Arcana")));
        skills.put("Athletics", new Integer(tmp.optInt("Athletics")));
        skills.put("Animal Handling", new Integer(tmp.optInt("Animal Handling")));
        skills.put("Persuasion", new Integer(tmp.optInt("Persuasion")));
        skills.put("Intimidation", new Integer(tmp.optInt( "Intimidation")));
        skills.put("History", new Integer(tmp.optInt("History")));

        this.speed = file.getInt("speed");

        this.traits = new ArrayList<>();
        array = file.getJSONArray("traits");
        for(int i =0; i<array.length(); i++){
            traits.add(array.getString(i));
        }

        this.name = file.getString("name");

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
                this.addProficiencies(new Proficiency("",p));
            }
        }
    }

    public boolean addProficiencies(Proficiency p){
        return this.proficiencies.add(p.getName());
    }

    public boolean removeProficiencies(Proficiency p){
        return this.proficiencies.remove(p.getName());
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

    public boolean addTrait(Trait t){
        return this.traits.add(t.name);
    }

    public boolean removeTrait(Trait t){
        return this.traits.remove(t.name);
    }

    public String getPersonality_traits() {
        return personality_traits;
    }

    public void setPersonality_traits(String personality_traits) {
        this.personality_traits = personality_traits;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }

    public boolean addFeature(Feature feature) {
        return this.features.add(feature);
    }

    public boolean removeFeature(Feature feature) {
        return this.features.remove(feature);
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

    public List<String> skillsHasStrings(){
        List<String> result = new ArrayList<>();
        for(Map.Entry mapEntry : this.skills.entrySet()){
            result.add(mapEntry.getKey() + " " + mapEntry.getValue().toString());
        }
        return result;
    }

    @Override
    public String toString() {
        return "Character{" +
                "name='" + name + '\'' +
                ", race='" + race + '\'' +
                ", class_='" + class_ + '\'' +
                ", level=" + level +
                ", hitDice=" + hitDice +
                '}';
    }
}
