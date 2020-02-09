package connection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Classes extends APIconnection {

    private List<ProficienciesList> ProficienciesChoice;
    private ProficienciesList BasicProficiencies;
    private String name;
    private int hitDice;
    private List<String> JetDeSauv;

    public Classes() throws IOException, JSONException{
        super("http://www.dnd5eapi.co/api/classes/");
    }

    public Classes(String classe) throws IOException, JSONException {
        super( "http://www.dnd5eapi.co/api/classes/" + classe);

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


    }

    public List<ProficienciesList> getChoice() {
        return ProficienciesChoice;
    }
}
