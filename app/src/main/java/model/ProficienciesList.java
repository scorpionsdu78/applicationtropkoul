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
