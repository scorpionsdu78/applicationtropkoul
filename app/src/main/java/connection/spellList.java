package connection;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Set;

import model.Spell;

public class spellList extends APIconnection {

    HashMap<String, String> spells;

    public spellList() throws IOException, JSONException {
        super("http://www.dnd5eapi.co/api/spells/");

        spells = new HashMap<String, String>();

        JSONArray tab = file.getJSONArray("results");
        for(int i=0; i<tab.length(); i++){
            JSONObject tmp = tab.getJSONObject(i);
            spells.put(tmp.getString("index"),"http://www.dnd5eapi.co"+tmp.getString("url"));
        }
    }

    public Spell getSpell(String name) throws IOException, JSONException {

        if(spells.get(name) != null){
            Log.d("debug",spells.get(name));
            return new Spell(spells.get(name));
        }

        return null;
    }


    @Override
    public String toString() {

        String s ="spellList{";

        for (String i : spells.keySet()) {
            s= s +i+"\n";
        }

        return  s;
    }
}
