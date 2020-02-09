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

        for (int i =0; i<list.size(); i++) {
            s = s +"\n"+list.get(i).name+ " " + list.get(i).url + "\n";
        }
        
        return s;
    }
}
