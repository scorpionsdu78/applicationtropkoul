package model;

import java.util.ArrayList;
import java.util.List;

import model.Proficiencies;

public class ProficienciesList {
    List<Proficiencies> list;
    int choice;

    public ProficienciesList(int choice) {
        this.choice = choice;
        list = new ArrayList<Proficiencies>();
    }

    public void add(Proficiencies p){
        list.add(p);
    }

    public List<Proficiencies> getList() {
        return list;
    }

    public int getChoice() {
        return choice;
    }

    public List<String> getNames(){
        List<String> names = new ArrayList<>();
        for (Proficiencies p: list) {
            names.add(p.getName());
        }
        return names;
    }

    public List<String> hasSkills(){
        List<String> retour = new ArrayList<>();
        for (Proficiencies p: list) {
            if(p.name.contains("Skill:")){
                retour.add(p.name.split(" ")[1]);
                list.remove(p);
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
            s = s +"\n"+list.get(i).name+ " " +  "\n";
        }
        
        return s;
    }
}
