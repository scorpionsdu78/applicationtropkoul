package com.example.ddprojet.fonction.asyncFonc;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
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

import connection.Race;
import model.Bonus;
import model.TraitList;
import util.StringAdapter;
import util.Utility;

public class RaceInfoGet extends AsyncTask<String, List<String>, Race> {

    private WeakReference<View> view;
    private WeakReference<TextView> name;
    private WeakReference<TextView> speed;
    private WeakReference<TextView> alignement;
    private WeakReference<TextView> age;
    private WeakReference<TextView> size;
    private WeakReference<TextView> sizeDesc;
    private WeakReference<TextView> langDesc;
    private WeakReference<ListView> langues;
    private WeakReference<ListView> trait;
    private WeakReference<ListView> Bonus;
    Map<String, Integer> images;


    public RaceInfoGet(View v,TextView _name, TextView _speed, TextView _alignement, TextView _age, TextView _size, TextView _sizeDesc, TextView _langDesc, ListView _langues, ListView _trait, ListView _bonus) {
        this.view = new WeakReference<>(v);
        this.name = new WeakReference<TextView>(_name);
        this.speed = new WeakReference<>(_speed);
        this.alignement = new WeakReference<>(_alignement);
        this.age = new WeakReference<>(_age);
        this.size = new WeakReference<>(_size);
        this.sizeDesc = new WeakReference<>(_sizeDesc);
        this.langDesc = new WeakReference<>(_langDesc);
        this.langues = new WeakReference<>(_langues);
        this.trait = new WeakReference<>(_trait);
        this.Bonus = new WeakReference<>(_bonus);

        images = new HashMap<>();
        images.put("Dragonborn", new Integer(R.drawable.race_dragonborn));
        images.put("Dwarf", new Integer(R.drawable.race_dwarf));
        images.put("Elf", new Integer(R.drawable.race_elf));
        images.put("Gnome", new Integer(R.drawable.race_gnome));
        images.put("Half-Elf", new Integer(R.drawable.race_half_elf));
        images.put("Halfling", new Integer(R.drawable.race_halfling));
        images.put("Half-Orc", new Integer(R.drawable.race_half_orc));
        images.put("Human", new Integer(R.drawable.race_human));
        images.put("Tiefling", new Integer(R.drawable.race_tiefling));

    }

    @Override
    protected Race doInBackground(String... strings) {

        for (String s:strings) {
            try {
                Race race = new Race(s);
                return race;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    protected void onPostExecute(Race race) {

        name.get().setText(race.getName());

        ImageView image = view.get().findViewById(R.id.RaceimageView3);
        image.setImageResource(images.get(race.getName()).intValue());

        this.speed.get().setText("speed: " + Integer.toString(race.getSpeed()));

        this.alignement.get().setText("alignement"+race.getAlignement());

        this.age.get().setText("age: " + race.getAge());

        this.size.get().setText("size "+race.getSize());

        this.sizeDesc.get().setText("size description: " + race.getSize_desc());

        this.langDesc.get().setText("languages description: " + race.getLangDesc());

        //récuperation de la liste des langues
        ListView list = this.langues.get();

        List<String> lang = new ArrayList<>();
        lang.add("Langue");

        for (String s: race.getLanguages()) {
            lang.add(s);
        }

        StringAdapter adapter = new StringAdapter(view.get().getContext(), R.layout.util_list, lang);

        list.setAdapter(adapter);

        Utility.setListViewHeightBasedOnChildren(list);

        //on get les traits
        ListView list2 = this.trait.get();
        String traistSrtring = "";
        if(race.getTraitList() !=null){
            traistSrtring += "trait to choose (" + Integer.toString(race.getTraitList().getChoice()) + ")\n" + race.getTraitList().toString();
        }

        traistSrtring+="\n" + race.getGlobalTrait().toString();
        List<String> traitList = new ArrayList<>();
        traitList.add("Trait List");
        traitList.add(traistSrtring);

        Log.d("faischier", String.valueOf(traitList.size()));

        StringAdapter adaptert = new StringAdapter(view.get().getContext(), R.layout.util_list, traitList);
        list2.setAdapter(adaptert);

        Utility.setListViewHeightBasedOnChildren(list2);

        //on get les bonus
        ListView list3 = this.Bonus.get();

        List<String> bonusList = new ArrayList<>();


        List<model.Bonus> bonuses = race.getBonuses();

        bonusList.add("BONUS");

        for (Bonus b: bonuses) {
            bonusList.add(b.getCharacteristic() + ":" + Integer.toString(b.getValue()));
        }

        StringAdapter adapterb = new StringAdapter(view.get().getContext(), R.layout.util_list, bonusList);
        list3.setAdapter(adapterb);

        Utility.setListViewHeightBasedOnChildren(list3);


    }


}


