package com.example.ddprojet.fonction.asyncFonc;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ddprojet.R;

import org.json.JSONException;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

import connection.Classes;
import model.Feature;
import model.ProficienciesList;
import util.StringAdapter;

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
        txt.setText(Integer.toString(classes.getHitDice()));

        txt = vue.get().findViewById(R.id.proficiency);
        txt.setText(classes.getBasicProficiencies().toString());

        RecyclerView recyclerViewProficiencies = vue.get().findViewById(R.id.proficienciesChoice);
        StringAdapter adapterProficiencies = new StringAdapter();

        for (ProficienciesList pl: classes.getProficienciesChoice()) {
            adapterProficiencies.addItem(pl.toString());
        }

        recyclerViewProficiencies.setLayoutManager(new LinearLayoutManager(vue.get().getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerViewProficiencies.setAdapter(adapterProficiencies);


        RecyclerView recyclerViewSavingThrows = vue.get().findViewById(R.id.Saving);
        StringAdapter adapterSavingThrows = new StringAdapter();

        for(String bonus : classes.getJetDeSauv()){
            adapterSavingThrows.addItem(bonus);
        }

        recyclerViewSavingThrows.setLayoutManager(new LinearLayoutManager(vue.get().getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerViewSavingThrows.setAdapter(adapterSavingThrows);


        RecyclerView recyclerViewFeatures = vue.get().findViewById(R.id.features);
        StringAdapter adapterFeatures = new StringAdapter();

        for(Feature feature : classes.getFeatures()){
            adapterFeatures.addItem(feature.toString());
        }

        recyclerViewFeatures.setLayoutManager(new LinearLayoutManager(vue.get().getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerViewFeatures.setAdapter(adapterFeatures);

        Log.i("DulcheE", "before");
        if(classes.getFeatureChoose() != null) {
            Log.i("DulcheE", "not null");

            RecyclerView recyclerViewChooseFeatures = vue.get().findViewById(R.id.chooseFeatures);
            StringAdapter adapterChooseFeatures = new StringAdapter();

            for(Feature feature : classes.getFeatureChoose()){
                Log.i("DulcheE", feature.toString());
                adapterChooseFeatures.addItem("Choose 1 :\n" + feature.toString());
            }

            recyclerViewChooseFeatures.setLayoutManager(new LinearLayoutManager(vue.get().getContext(), LinearLayoutManager.VERTICAL, false));
            recyclerViewChooseFeatures.setAdapter(adapterChooseFeatures);

            ConstraintLayout constraintLayoutChooseFeatures= this.vue.get().findViewById(R.id.constraintLayoutChooseFeatures);
            constraintLayoutChooseFeatures.setVisibility(View.VISIBLE);
        }else{
            Log.i("DulcheE", "null");
            ConstraintLayout constraintLayoutChooseFeatures= this.vue.get().findViewById(R.id.constraintLayoutChooseFeatures);
            constraintLayoutChooseFeatures.setVisibility(View.INVISIBLE);
            constraintLayoutChooseFeatures.setMaxHeight(0);
        }

    }
}
