package model;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class TraitList {

    List<Trait> traits;
    int choice;

    public TraitList(int choice) {
        this.choice = choice;
        traits = new ArrayList<Trait>();
    }

    public void add(Trait trait){
        traits.add(trait);
    }

    public int getChoice() {
        return choice;
    }

    @Override
    public String toString() {
        String s ="";

        for (Trait t: traits) {
            Log.d("JTL", t.getName() + t.getDescription());
            s = s + t.getName()  + "\n";
        }

        return s;
    }
}
