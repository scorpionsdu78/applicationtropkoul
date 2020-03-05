package com.example.ddprojet.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class TraitsList {

    protected List<Trait> traits;
    protected int choice;
    protected String name;

    public TraitsList(int choice) {
        this.choice = choice;
        traits = new ArrayList<Trait>();
    }

    public void add(Trait trait){
        if(trait.getSubName() != null){
            name = trait.getName();
        }
        traits.add(trait);
    }

    public int getChoice() {
        return choice;
    }

    public List<String> getName(){
        List<String> retour = new ArrayList<>();
        for (Trait t: traits) {
            retour.add(t.name);
        }
        return retour;
    }

    public List<Trait> getTraits(){
        return this.traits;
    }

    @Override
    public String toString() {
        String s ="";

        if(name == null){
            for (Trait t: traits) {
                Log.d("JTL", t.getName() + t.getDescription());
                s += t.getName();

                if(t != traits.get(traits.size()-1))
                    s += "\n";
            }
        }else {
            s += name + ": \n";
            for (Trait t: traits) {
                Log.d("JTL", t.getName() + t.getDescription());
                s += t.getSubName();

                if(t != traits.get(traits.size()-1))
                    s += "\n";
            }
        }


        return s;
    }
}
