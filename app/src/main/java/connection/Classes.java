package connection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.Feature;
import model.Proficiencies;
import model.ProficienciesList;

public class Classes extends APIconnection {

    protected static final String classPath = "/api/classes/";

    private List<ProficienciesList> ProficienciesChoice;
    private ProficienciesList BasicProficiencies;
    private String name;
    private int hitDice;
    private List<String> JetDeSauv;
    private List<Feature> features;
    private List<Feature> featureChoose;
    private boolean hasSpellCasting;



    public Classes() throws IOException, JSONException{
        super(Classes.classPath);
    }

    public Classes(String class_) throws IOException, JSONException {
        super( Classes.classPath + class_);

        //creations de la proficiencies l
        ProficienciesChoice = new ArrayList<>();

        //recuperation des tableau de proficiencies choice
        JSONArray proficienciesChoiceTmp = file.getJSONArray("proficiency_choices");

        //on parcour la liste des different tableau de proficiencies choice
        for (int i=0; i<proficienciesChoiceTmp.length(); i++){
            //on récupere la liste
            JSONObject ProficianciTmp = proficienciesChoiceTmp.getJSONObject(i);

            //on récupère les attribut choose et la liste de proficiencies
            int size = ProficianciTmp.getInt("choose");
            JSONArray ListOfChoice = ProficianciTmp.getJSONArray("from");
            //on crer un objet proficiencies list
            ProficienciesList result = new ProficienciesList(size);
            //on parcour la list des proficiencies  et on ajoute les element a l'objet
            for (int j = 0; j<ListOfChoice.length(); j++){
                JSONObject choice = ListOfChoice.getJSONObject(j);
                Proficiencies p = new Proficiencies(choice.getString("url"),choice.getString("name"));
                result.add(p);
            }

            //on ajouté le nouvel objet a la liste
            ProficienciesChoice.add(result);
        }

        //recuperation des proficiencies de base
        BasicProficiencies = new ProficienciesList(0);
        JSONArray BasicProficienciesTmp = file.getJSONArray("proficiencies");

        for(int i = 0; i<BasicProficienciesTmp.length(); i++){
            JSONObject tmp = BasicProficienciesTmp.getJSONObject(i);
            BasicProficiencies.add(new Proficiencies(tmp.getString("url"),tmp.getString("name")));
        }

        //recuêration du nom
        name = file.getString("name");

        //recuperation des hit die
        hitDice = file.getInt("hit_die");

        //Recuperation des jet de sauvgarde
        JetDeSauv = new ArrayList<>();
        JSONArray JetSauvTmp = file.getJSONArray("saving_throws");
        for(int i=0; i<JetSauvTmp.length(); i++){
            JSONObject sauvegardeTmp = JetSauvTmp.getJSONObject(i);
            JetDeSauv.add(sauvegardeTmp.getString("name"));
        }

        APIconnection co = new APIconnection(file.getJSONObject("class_levels").getString("url") + "/1");
        JSONObject fichier = co.getFile();
        if(fichier.optJSONObject("feature_choices")!=null){
            if(name.toLowerCase() == "fighter"){
                featureChoose = new ArrayList<>();
                featureChoose.add(new Feature("/api/features/fighting-style-archery"));
                featureChoose.add(new Feature("/api/features/fighting-style-defense"));
                featureChoose.add(new Feature("/api/features/fighting-style-dueling"));
                featureChoose.add(new Feature("/api/features/fighting-style-great-weapon-fighting"));
                featureChoose.add(new Feature("/api/features/fighting-style-protection"));
                featureChoose.add(new Feature("/api/features/fighting-style-two-weapon-fighting"));
            }
        }

        JSONArray tmp = fichier.getJSONArray("features");
        features = new ArrayList<>();

        for (int i=0; i < tmp.length(); i++){
            switch (tmp.getJSONObject(i).getString("name")){
                case "Divine Domain":
                    break;

                case "Sorcerous Origin":
                    break;

                case "Otherworldly Patron":
                    break;

                default:
                    features.add(new Feature(tmp.getJSONObject(i).getString("url")));
            }
        }

        if(file.optJSONObject("spellcasting") != null){
            hasSpellCasting = true;
        }else {
            hasSpellCasting = false;
        }

    }


    public List<ProficienciesList> getProficienciesChoice() {
        return ProficienciesChoice;
    }

    public ProficienciesList getBasicProficiencies() {
        return BasicProficiencies;
    }

    public String getName() {
        return name;
    }

    public int getHitDice() {
        return hitDice;
    }

    public List<String> getJetDeSauv() {
        return JetDeSauv;
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public List<Feature> getFeatureChoose() {
        return featureChoose;
    }

    public boolean getHasSpellCastin(){
        return hasSpellCasting;
    }
}
