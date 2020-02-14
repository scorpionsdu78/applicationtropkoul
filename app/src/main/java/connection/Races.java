package connection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.Bonus;
import model.Trait;
import model.TraitList;

public class Races extends APIconnection {

    private List<Bonus>  bonuses;
    private TraitList traitList;
    private TraitList globalTrait;
    private List<String> languages;
    private String name;
    private int speed;
    private String alignement;
    private String age;
    private String size;
    private String size_desc;
    private String langDesc;


    public Races() throws IOException, JSONException {
        super("http://www.dnd5eapi.co/api/races/");
    }

    public Races(String path) throws IOException, JSONException {
        super("http://www.dnd5eapi.co/api/races/"+path);

        //recuperation des bonus de race
        bonuses = new ArrayList<>();
        JSONArray bonusList = file.getJSONArray("ability_bonuses");//on recupere un tableau d'objet JSON contenant les bonus
        for(int i=0; i<bonusList.length(); i++){
            JSONObject tmp = bonusList.getJSONObject(i);

            bonuses.add(new Bonus(tmp.getString("name"),tmp.getInt("bonus")));//on crer un nouveau bonus avec la charac + la valeur
        }

        //on suit la meme logique qu'avec les proficiencies sauf qu'ici on stock le nom et la description
        JSONObject traitOption = file.getJSONObject("trait_options");
        traitList = new TraitList(traitOption.getInt("choose"));

        JSONArray listTraitTmp = traitOption.getJSONArray("from");
        for (int i=0; i<listTraitTmp.length(); i++){
            JSONObject tampon = listTraitTmp.getJSONObject(i);
            traitList.add(new Trait(tampon.getString("url"),tampon.getString("name")));
        }

        //on recomance pour les traits globaux
        JSONArray traitglobaux = file.getJSONArray("traits");
        globalTrait = new TraitList(0);

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

    public TraitList getTraitList() {
        return traitList;
    }

    public TraitList getGlobalTrait() {
        return globalTrait;
    }

    public List<Bonus> getBonuses() {
        return bonuses;
    }

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public String getAlignement() {
        return alignement;
    }

    public String getAge() {
        return age;
    }

    public String getSize() {
        return size;
    }

    public String getSize_desc() {
        return size_desc;
    }

    public String getLangDesc() {
        return langDesc;
    }

    public List<String> getLanguages() {
        return languages;
    }
}
