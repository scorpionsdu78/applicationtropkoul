package com.example.ddprojet.fonction.asyncFonc;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
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

import com.example.ddprojet.connection.Class;
import com.example.ddprojet.model.Feature;
import com.example.ddprojet.model.ProficienciesList;
import com.example.ddprojet.model.Proficiency;
import com.example.ddprojet.service.LoadingService;
import com.example.ddprojet.util.ClassEnum;
import com.example.ddprojet.util.adapter.StringAdapter;

public class ClassesInfoGet extends AsyncTask<String, String, Class> {

    WeakReference<View> vue;
    WeakReference<Activity> activity;


    public ClassesInfoGet(View _vue, Activity activity) {
        this.vue = new WeakReference<>(_vue);
        this.activity = new WeakReference<>(activity);

        this.activity.get().startService(new Intent(this.activity.get(), LoadingService.class));
    }

    @Override
    protected Class doInBackground(String... strings) {
        for (String s:strings) {
            try {
                Class classe = new Class(s);
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
    protected void onPostExecute(Class _class) {

        if(_class == null){
            this.activity.get().stopService(new Intent(this.activity.get(), LoadingService.class));
            return;
        }

        TextView txt = vue.get().findViewById(R.id.name);
        txt.setText(_class.getName());

        ImageView image = vue.get().findViewById(R.id.RaceimageView3);
        image.setImageResource(ClassEnum.valueOf(_class.getName()).getValue());

        txt = vue.get().findViewById(R.id.hitDice);
        txt.setText(Integer.toString(_class.getHitDice()));

        txt = vue.get().findViewById(R.id.proficiency);
        txt.setText(_class.getBasicProficiencies().toString());

        RecyclerView recyclerViewProficiencies = vue.get().findViewById(R.id.proficienciesChoice);
        StringAdapter adapterProficiencies = new StringAdapter();

        for (ProficienciesList pl : _class.getProficienciesChoice()) {
            String item = "";
            if(pl.hasSkills()) {
                item += "Choose " + pl.getChoice() + " " + pl.getList().get(0).getName();
                for(Proficiency proficiency : pl.getList())
                    item += "\n" + proficiency.getSubName();
            } else {
                item += "Choose " + pl.getChoice();
                for(Proficiency proficiency : pl.getList())
                    item += "\n" + proficiency.getName();
            }
            if(!item.isEmpty())
                adapterProficiencies.addItem(item);
        }

        recyclerViewProficiencies.setLayoutManager(new LinearLayoutManager(vue.get().getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerViewProficiencies.setAdapter(adapterProficiencies);


        RecyclerView recyclerViewSavingThrows = vue.get().findViewById(R.id.Saving);
        StringAdapter adapterSavingThrows = new StringAdapter();

        for(String bonus : _class.getJetDeSauv()){
            adapterSavingThrows.addItem(bonus);
        }

        recyclerViewSavingThrows.setLayoutManager(new LinearLayoutManager(vue.get().getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerViewSavingThrows.setAdapter(adapterSavingThrows);


        RecyclerView recyclerViewFeatures = vue.get().findViewById(R.id.features);
        StringAdapter adapterFeatures = new StringAdapter();

        for(Feature feature : _class.getFeatures()){
            adapterFeatures.addItem(feature.getName());
        }

        recyclerViewFeatures.setLayoutManager(new LinearLayoutManager(vue.get().getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerViewFeatures.setAdapter(adapterFeatures);

        if(_class.getFeatureChoose() != null) {

            RecyclerView recyclerViewChooseFeatures = vue.get().findViewById(R.id.chooseFeatures);
            StringAdapter adapterChooseFeatures = new StringAdapter();

            String item = "Choose " + ((_class.getFeatureChoose().get(0).getSubName() != null) ? _class.getFeatureChoose().get(0).getName() : "1") + " :";
            for(Feature feature : _class.getFeatureChoose()){
                item += ("\n" + ((feature.getSubName() != null) ? feature.getSubName() : feature.getName()));
            }

            adapterChooseFeatures.addItem(item);

            recyclerViewChooseFeatures.setLayoutManager(new LinearLayoutManager(vue.get().getContext(), LinearLayoutManager.VERTICAL, false));
            recyclerViewChooseFeatures.setAdapter(adapterChooseFeatures);

            ConstraintLayout constraintLayoutChooseFeatures= this.vue.get().findViewById(R.id.constraintLayoutChooseFeatures);
            constraintLayoutChooseFeatures.setVisibility(View.VISIBLE);
        }else{
            ConstraintLayout constraintLayoutChooseFeatures= this.vue.get().findViewById(R.id.constraintLayoutChooseFeatures);
            constraintLayoutChooseFeatures.setVisibility(View.INVISIBLE);
            constraintLayoutChooseFeatures.setMaxHeight(0);
        }

        this.activity.get().stopService(new Intent(this.activity.get(), LoadingService.class));

    }
}
