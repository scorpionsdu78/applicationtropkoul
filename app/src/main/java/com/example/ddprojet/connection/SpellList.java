package com.example.ddprojet.connection;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.ddprojet.model.Spell;

public class SpellList extends APIconnection {

    protected static final String spellListPath = "/api/spells/";

    HashMap<String, String> spells;
    List<Spell> spellList;

    public SpellList() throws IOException, JSONException {
        super(SpellList.spellListPath);
        Log.i("alerte","chelou");

        spells = new HashMap<String, String>();
        spellList = new ArrayList<Spell>();

        JSONArray tab = file.getJSONArray("results");
        if(file == null){
            Log.i("alerte","chelou2");
        }else if(file != null){
            Log.i("alerte","chelou3");
        }
        Log.i("alerte","chelou 4");

        for(int i=0; i<tab.length(); i++){
            JSONObject tmp = tab.getJSONObject(i);
            Log.i("alerte",tmp.getString("url"));
            Log.i("alerte",Integer.toString(tab.length()));
            //spells.put(tmp.getString("index"),"https://www.dnd5eapi.co"+tmp.getString("url"));
            spellList.add(new Spell(tmp.getString("url")));
        }
    }

    public List<Spell> getSpellList() {
        return spellList;
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
            Log.i("alerte","entrez le fort de getSepllFor");

            if(s.isFor(classe)){
                Log.i("alerte","le sort est pour la classe2");
                result.add(s);
            }
        }
        return result;
    }

    public List<String> getSpellsName(){
        List<String> ls = new ArrayList<>();
        for(Spell s: spellList){
            ls.add(s.getName());
        }
        return ls;
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
