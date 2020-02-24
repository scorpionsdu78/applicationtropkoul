package connection;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import model.Spell;

public class SpellList extends APIconnection {

    protected static final String spellListPath = "spells/";

    HashMap<String, String> spells;
    List<Spell> spellList;

    public SpellList() throws IOException, JSONException {
        super(SpellList.spellListPath);

        spells = new HashMap<String, String>();

        JSONArray tab = file.getJSONArray("results");
        for(int i=0; i<tab.length(); i++){
            JSONObject tmp = tab.getJSONObject(i);
            spells.put(tmp.getString("index"),"https://www.dnd5eapi.co"+tmp.getString("url"));
            spellList.add(new Spell("https://www.dnd5eapi.co"+tmp.getString("url")));
        }
    }

    public Spell getSpell(String name) throws IOException, JSONException {

        if(spells.get(name) != null){
            Log.d("debug",spells.get(name));
            return new Spell(spells.get(name));
        }

        return null;
    }

    public List<Spell> getSpellFor(String classe){
        List<Spell> result = new ArrayList<>();
        for (Spell s: spellList) {
            if(s.isFor(classe)){
                result.add(s);
            }
        }
        return result;
    }


    @Override
    public String toString() {

        String s ="SpellList{";

        for (String i : spells.keySet()) {
            s= s +i+"\n";
        }

        return  s;
    }
}
