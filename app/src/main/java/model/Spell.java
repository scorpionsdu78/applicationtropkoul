package model;

import android.util.Log;

import androidx.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import connection.APIconnection;

public class Spell extends APIconnection implements Comparable<Spell>{


    String name;
    String desc;
    String highLevel;
    String page;
    String range;
    List<String> components;
    String material;
    boolean ritual;
    String duration;
    boolean concentration;
    String castingTime;
    int level;
    String school;
    List<String> classes;

    public Spell(String path) throws IOException, JSONException {
        super(path);

        //on récupere le nom
        name = file.getString("name");

        //on récupere la description
        desc = file.getString("desc");

        //on récupère le higher lever (ce champs peut-être null ou empty)
        highLevel = file.optString("higher_level", "None");

        //on récupère la page du player handbook
        page = file.getString("page");

        //on récupère la range
        range = file.getString("range");
        
        //on récupère les components pour le spell, pour cela on initialise une liste puis on parcour un JSON array
        components = new ArrayList<>();
        JSONArray list = file.getJSONArray("components");
        for (int i = 0; i<list.length(); i++) {
            components.add(list.getString(i));
        }

        //on recupere les materiaux du sort
        if(file.optJSONObject("material") != null)
            material = file.getString("material");

        //on recupere si il y a un ritual
        ritual = file.getBoolean("ritual");

        //on recupere la casting time
        duration = file.getString("duration");

        //on recupere si le sort a besoin de concentration
        concentration = file.getBoolean("concentration");

        //on recupere le casting time
        castingTime = file.getString("casting_time");

        //on recupere le level
        level =file.getInt("level");

        //on recupere l'ecole de magie
        school =file.getJSONObject("school").getString("name");

        //on recupere la liste des classe pouvant utiliser ce spell pour cela on va parcourir un json array
        classes = new ArrayList<>();
        list = file.getJSONArray("classes");
        for (int i = 0; i<list.length(); i++) {
            classes.add(list.getJSONObject(i).getString("name"));
        }



    }

    public Spell(String name, String desc, String highLevel, String page, String range, List<String> components, String material, boolean ritual, String duration, boolean concentration, String castingTime, int level, String school, List<String> classes) {


        this.name = name;
        this.desc = desc;
        this.highLevel = highLevel;
        this.page = page;
        this.range = range;
        this.components = components;
        this.material = material;
        this.ritual = ritual;
        this.duration = duration;
        this.concentration = concentration;
        this.castingTime = castingTime;
        this.level = level;
        this.school = school;
        this.classes = classes;
    }

    public boolean isFor(String classe){

        for (String s: classes) {
            Log.i("alerte","entrez dans le for de isfor");

            if(s.toLowerCase().equals(classe.toLowerCase())){
                Log.i("alerte","le sort est pour la classe1");
                return true;
            }
        }

        return false;
    }

    public String getName(){
        return name;
    }

    public String getSchool(){
        return school;
    }
    public int getLevel(){
        return level;
    }

    @NonNull
    @Override
    public String toString() {

        String result = "name: "+name + "\n" +"description: "+ desc + "\n" +"high level:" + highLevel + "\n" +"page: " + page + "\n" + "range: " + range + "\n" + "component: ";

        for (String s: components) {
            result+= " " +s+",";
        }
        result += "\n" + "material: " + material + "\n" + "ritual: " + Boolean.toString(ritual) + "\n" + "duration: " +duration +"\n"+"concentration: " + Boolean.toString(concentration) + "\n" + "casting Time:" + castingTime+"\n" + "level: " +Integer.toString(level) + "\n" + "school: " + school +"\n";

        result += "classes:\n";

        for (String s: classes) {
            result += s + "\n";
        }

        return result;
    }


    @Override
    public int compareTo(Spell spell) {
        return Integer.compare(this.level,spell.getLevel());

    }
}
