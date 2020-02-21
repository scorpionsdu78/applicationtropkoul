package com.example.ddprojet.fonction.asyncFonc;

import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ddprojet.R;

import org.json.JSONException;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import connection.Classes;
import model.ProficienciesList;
import util.StringAdapter;
import util.Utility;

public class ClassesGetInfo extends AsyncTask<String, String, Classes> {

    WeakReference<View> vue;
    Map<String, Integer> images;



    public ClassesGetInfo(View _vue) {
        this.vue = new WeakReference<>(_vue);
        images = new HashMap<String, Integer>();
        images.put("Barbarian", new Integer(R.drawable.class_barbarian));
        images.put("Bard", new Integer(R.drawable.class_bard));
        images.put("Cleric", new Integer(R.drawable.class_cleric));
        images.put("Druid", new Integer(R.drawable.class_druid));
        images.put("Fighter", new Integer(R.drawable.class_fighter));
        images.put("Monk", new Integer(R.drawable.class_monk));
        images.put("Paladin",new Integer(R.drawable.class_paladin));
        images.put("Ranger", new Integer(R.drawable.class_ranger));
        images.put("Rogue", new Integer(R.drawable.class_rogue));
        images.put("Sorcerer", new Integer(R.drawable.class_sorcerer));
        images.put("Warlock", new Integer(R.drawable.class_warlock));
        images.put("Wizard", new Integer(R.drawable.class_wizard));
    }

    @Override
    protected Classes doInBackground(String... strings) {
        for (String s:strings) {
            try {
                Classes classe = new Classes(s);
                return classe;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }


        return null;
    }

    @Override
    protected void onPostExecute(Classes classes) {

        TextView txt = vue.get().findViewById(R.id.name);
        txt.setText(classes.getName());

        ImageView image = vue.get().findViewById(R.id.RaceimageView3);
        image.setImageResource(images.get(classes.getName()).intValue());

        txt = vue.get().findViewById(R.id.hitDice);
        txt.setText("hit dice: "+ Integer.toString(classes.getHitDice()));

        txt = vue.get().findViewById(R.id.proficiency);
        txt.setText(classes.getBasicProficiencies().toString());

        List<String> ls = new ArrayList<>();
        ls.add("Proficiencies choice");

        for (ProficienciesList pl: classes.getProficienciesChoice()) {
            ls.add(pl.toString() + "\n");

        }

        ListView list = vue.get().findViewById(R.id.proficienciesChoice);
        StringAdapter adapter = new StringAdapter(vue.get().getContext(), R.layout.util_list, ls);

        list.setAdapter(adapter);

        Utility.setListViewHeightBasedOnChildren(list);

        List<String> listBonus = classes.getJetDeSauv();
        listBonus.add(0, "SAVING THROW:");

        ListView list2 = vue.get().findViewById(R.id.Saving);

        StringAdapter adapter2 = new StringAdapter(vue.get().getContext(), R.layout.util_list, listBonus);
        list2.setAdapter(adapter2);

        Utility.setListViewHeightBasedOnChildren(list2);


    }
}
