package com.example.ddprojet.model;

import java.util.ArrayList;
import java.util.List;

public class ProficienciesList {
    List<Proficiency> list;
    int choice;

    public ProficienciesList(int choice) {
        this.choice = choice;
        list = new ArrayList<Proficiency>();
    }

    public List<Proficiency> getProficiencies(){
        return list;
    }

    public void add(Proficiency p){
        list.add(p);
    }

    public List<Proficiency> getList() {
        return list;
    }

    public int getChoice() {
        return choice;
    }

    public List<String> getNames(){
        List<String> names = new ArrayList<>();
        for (Proficiency p: list) {
            names.add(p.getName());
        }
        return names;
    }

    public boolean hasSkills(){
        if(this.getList().size() == 0)
            return false;

        boolean retour = true;

        for (Proficiency p : this.list) {
            if(!p.isSkill()){
                retour = false;
                break;
            }
        }

        return retour;
    }

    @Override
    public String toString() {
        String s = "";

        if(choice >0){
            s += "choice: " + Integer.toString(choice);
        }

        for (int i =0; i<list.size(); i++) {
            s += "\n" + list.get(i).name;
        }
        
        return s;
    }
}
