package connection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Classes extends APIconnection {

    private List<ProficienciesList> choice;

    public Classes() throws IOException, JSONException{
        super("http://www.dnd5eapi.co/api/classes/");
    }

    public Classes(String classe) throws IOException, JSONException {
        super( "http://www.dnd5eapi.co/api/classes/" + classe);

        choice = new ArrayList<>();

        JSONArray proficienciesChoice = file.getJSONArray("proficiency_choices");

        for (int i=0; i<proficienciesChoice.length(); i++){
            JSONObject Proficiancy = proficienciesChoice.getJSONObject(i);

            int size = Proficiancy.getInt("choose");
            JSONArray ListOfChoice = Proficiancy.getJSONArray("from");

            ProficienciesList result = new ProficienciesList(size);

            for (int j = 0; j<ListOfChoice.length(); j++){
                JSONObject choice = ListOfChoice.getJSONObject(j);
                Proficiencies p = new Proficiencies(choice.getString("url"),choice.getString("name"));
                result.add(p);
            }

            choice.add(result);
        }
    }

    public List<ProficienciesList> getChoice() {
        return choice;
    }
}
