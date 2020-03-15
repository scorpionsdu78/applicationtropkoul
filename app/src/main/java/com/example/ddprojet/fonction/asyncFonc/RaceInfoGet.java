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
import com.example.ddprojet.connection.Race;
import com.example.ddprojet.model.Bonus;
import com.example.ddprojet.model.Proficiency;
import com.example.ddprojet.model.Trait;
import com.example.ddprojet.service.LoadingService;
import com.example.ddprojet.util.RaceEnum;
import com.example.ddprojet.util.adapter.StringAdapter;

import org.json.JSONException;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.List;

public class RaceInfoGet extends AsyncTask<String, List<String>, Race> {

    private WeakReference<View> view;
    private WeakReference<TextView> name;
    private WeakReference<TextView> speed;
    private WeakReference<TextView> alignement;
    private WeakReference<TextView> age;
    private WeakReference<TextView> size;
    private WeakReference<TextView> sizeDesc;
    private WeakReference<TextView> langDesc;
    private WeakReference<RecyclerView> langues;
    private WeakReference<RecyclerView> trait;
    private WeakReference<RecyclerView> Bonus;

    private WeakReference<Activity> activity;


    public RaceInfoGet(View v, TextView _name, TextView _speed, TextView _alignement, TextView _age, TextView _size, TextView _sizeDesc, TextView _langDesc, RecyclerView _langues,
                       RecyclerView _trait, RecyclerView _bonus, Activity activity) {
        this.view = new WeakReference<>(v);
        this.name = new WeakReference<>(_name);
        this.speed = new WeakReference<>(_speed);
        this.alignement = new WeakReference<>(_alignement);
        this.age = new WeakReference<>(_age);
        this.size = new WeakReference<>(_size);
        this.sizeDesc = new WeakReference<>(_sizeDesc);
        this.langDesc = new WeakReference<>(_langDesc);
        this.langues = new WeakReference<>(_langues);
        this.trait = new WeakReference<>(_trait);
        this.Bonus = new WeakReference<>(_bonus);
        this.activity = new WeakReference<>(activity);

        this.activity.get().startService(new Intent(this.activity.get(), LoadingService.class));
    }

    @Override
    protected Race doInBackground(String... strings) {

        try {
            Race race = new Race(strings[0]);
            return race;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    protected void onPostExecute(Race race) {

        if(race == null){
            this.activity.get().stopService(new Intent(this.activity.get(), LoadingService.class));
            return;
        }

        name.get().setText(race.getName());

        ImageView image = view.get().findViewById(R.id.RaceimageView3);
        image.setImageResource(RaceEnum.getValue(race.getName()));

        this.speed.get().setText(Integer.toString(race.getSpeed()));

        this.alignement.get().setText(race.getAlignement());

        this.age.get().setText(race.getAge());

        this.size.get().setText(race.getSize());

        this.sizeDesc.get().setText(race.getSize_desc());

        this.langDesc.get().setText(race.getLangDesc());

        //récuperation de la liste des langues
        RecyclerView recyclerViewLangues = this.langues.get();
        StringAdapter adapterLangues = new StringAdapter();

        for (String language : race.getLanguages()) {
            adapterLangues.addItem(language);
        }

        recyclerViewLangues.setLayoutManager(new LinearLayoutManager(view.get().getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerViewLangues.setAdapter(adapterLangues);

        //on get les traits
        RecyclerView recyclerViewTraits = this.trait.get();
        StringAdapter adapterTraits = new StringAdapter();


        if(race.getTraitsList() != null){
            String item = "";
            if(race.getTraitsList().getTraits().get(0).getSubName() != null){
                item += "Choose " + race.getTraitsList().getChoice() + " " + race.getTraitsList().getTraits().get(0).getName() + " :";

                for(Trait trait : race.getTraitsList().getTraits()){
                    item += "\n" + trait.getSubName();
                }
            } else {
                item += "Choose " + race.getTraitsList().getChoice() + " :";

                for(Trait trait : race.getTraitsList().getTraits())
                    item += "\n" + trait.getName();
            }
            adapterTraits.addItem(item);
        }

        for(Trait trait : race.getGlobalTrait().getTraits()){
            adapterTraits.addItem(trait.getName());
        }

        recyclerViewTraits.setLayoutManager(new LinearLayoutManager(view.get().getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerViewTraits.setAdapter(adapterTraits);

        //on get les bonus
        RecyclerView recyclerViewBonus = this.Bonus.get();
        StringAdapter adapterBonus = new StringAdapter();

        List<com.example.ddprojet.model.Bonus> bonuses = race.getBonuses();

        for (Bonus b: bonuses) {
            adapterBonus.addItem(b.getCharacteristic() + ":" + Integer.toString(b.getValue()));
        }

        recyclerViewBonus.setLayoutManager(new LinearLayoutManager(view.get().getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerViewBonus.setAdapter(adapterBonus);

        //on get les proficiencies de la race

        if(!race.getStartProf().getList().isEmpty()) {

            RecyclerView recyclerViewProficiencies = this.view.get().findViewById(R.id.prof);
            StringAdapter adapterProficiencies = new StringAdapter();

            for (Proficiency proficiency : race.getStartProf().getList()) {
                if(proficiency.isSkill())
                    adapterProficiencies.addItem(proficiency.getSubName());
                else
                    adapterProficiencies.addItem(proficiency.getName());
            }

            recyclerViewProficiencies.setLayoutManager(new LinearLayoutManager(view.get().getContext(), LinearLayoutManager.VERTICAL, false));
            recyclerViewProficiencies.setAdapter(adapterProficiencies);

        }else{
            ConstraintLayout constraintLayoutProficiencies = this.view.get().findViewById(R.id.constraintLayoutProficiencies);
            constraintLayoutProficiencies.setVisibility(View.INVISIBLE);
            constraintLayoutProficiencies.setMaxHeight(0);
        }

        this.activity.get().stopService(new Intent(this.activity.get(), LoadingService.class));

    }

}


