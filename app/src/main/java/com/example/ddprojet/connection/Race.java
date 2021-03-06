package com.example.ddprojet.connection;

import com.example.ddprojet.model.Bonus;
import com.example.ddprojet.model.ProficienciesList;
import com.example.ddprojet.model.Proficiency;
import com.example.ddprojet.model.Trait;
import com.example.ddprojet.model.TraitsList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Race extends APIconnection {

    protected static final java.lang.String racePath = "/api/races/";

    private List<Bonus>  bonuses;
    private TraitsList traitsList;
    private TraitsList globalTrait;
    private List<java.lang.String> languages;
    private java.lang.String name;
    private int speed;
    private java.lang.String alignement;
    private java.lang.String age;
    private java.lang.String size;
    private java.lang.String size_desc;
    private java.lang.String langDesc;
    private ProficienciesList startProf;


    public Race() throws IOException, JSONException {
        super(Race.racePath);
    }

    public Race(java.lang.String race) throws IOException, JSONException {
        super(Race.racePath + race);

        //recuperation des bonus de race
        bonuses = new ArrayList<>();
        JSONArray bonusList = file.getJSONArray("ability_bonuses");//on recupere un tableau d'objet JSON contenant les bonus
        for(int i=0; i<bonusList.length(); i++){
            JSONObject tmp = bonusList.getJSONObject(i);

            bonuses.add(new Bonus(tmp.getString("name"),tmp.getInt("bonus")));//on crer un nouveau bonus avec la charac + la valeur
        }

        //on suit la meme logique qu'avec les proficiencies sauf qu'ici on stock le nom et la description
        if(file.optJSONObject("trait_options") !=null){
            JSONObject traitOption = file.getJSONObject("trait_options");
            traitsList = new TraitsList(traitOption.getInt("choose"));

            JSONArray listTraitTmp = traitOption.getJSONArray("from");
            for (int i=0; i<listTraitTmp.length(); i++){
                JSONObject tampon = listTraitTmp.getJSONObject(i);
                traitsList.add(new Trait(tampon.getString("url"),tampon.getString("name")));
            }
        }


        //on recomance pour les traits globaux
        JSONArray traitglobaux = file.getJSONArray("traits");
        globalTrait = new TraitsList(0);



        for (int i=0; i<traitglobaux.length(); i++){
            JSONObject tampon = traitglobaux.getJSONObject(i);
            globalTrait.add(new Trait(tampon.getString("url"),tampon.getString("name")));
        }

        //on récupère les langues
        languages = new ArrayList<>();
        JSONArray listLangue = file.getJSONArray("languages");

        for(int i=0; i<listLangue.length(); i++){
            JSONObject tampon = listLangue.getJSONObject(i);
            languages.add(tampon.getString("name"));
        }

        //on recupere les eventuel proficiencies de depart

        startProf = new ProficienciesList(0);

        JSONArray array = file.optJSONArray("starting_proficiencies");
        if(array !=null) {
            for (int i = 0; i < array.length(); i++){
                JSONObject tmp = array.getJSONObject(i);
                startProf.add(new Proficiency(tmp.getString("url"),tmp.getString("name")));
            }
        }

        //on récupère le nom
        name = file.getString("name");

        //on récupère la vitesse
        speed = file.getInt("speed");

        //on récupère l'alignement
        alignement = file.getString("alignment");

        //on récupère les infos sur l'age
        age = file.getString("age");

        //on recupere la size
        size = file.getString("size");

        //on récupere les infos sur la size
        size_desc = file.getString("size_description");

        //on récupère la descritpion des langues
        langDesc = file.getString("language_desc");


    }

    public TraitsList getTraitsList() {
        return traitsList;
    }

    public TraitsList getGlobalTrait() {
        return globalTrait;
    }

    public List<Bonus> getBonuses() {
        return bonuses;
    }

    public java.lang.String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public java.lang.String getAlignement() {
        return alignement;
    }

    public java.lang.String getAge() {
        return age;
    }

    public java.lang.String getSize() {
        return size;
    }

    public java.lang.String getSize_desc() {
        return size_desc;
    }

    public java.lang.String getLangDesc() {
        return langDesc;
    }

    public List<java.lang.String> getLanguages() {
        return languages;
    }


    public ProficienciesList getStartProf() {
        return startProf;
    }

    @Override
    public String toString() {
        return "Race{" +
                "name='" + name + '\'' +
                '}';
    }
}
