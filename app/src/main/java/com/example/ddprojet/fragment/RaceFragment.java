package com.example.ddprojet.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.icu.text.AlphabeticIndex;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ddprojet.R;
import com.example.ddprojet.fonction.utile.RacesGet;

import java.io.Console;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class RaceFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         View v = inflater.inflate(R.layout.race_layout, container, false);

        RecyclerView rv = (v).findViewById(R.id.RaceRecyclerView);
        RaceDescriptionAdaptator adaptator = new RaceDescriptionAdaptator();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),3,LinearLayoutManager.VERTICAL,false);

        rv.setLayoutManager(gridLayoutManager);
        rv.setAdapter(adaptator);

        RacesGet getter = new RacesGet(adaptator);
        getter.execute("test");

         return v;
    }

    public class RaceHolder extends RecyclerView.ViewHolder{

        protected TextView name;
        protected ImageView photo;

        public RaceHolder(@NonNull View itemView) {
            super(itemView);
            photo = (ImageView) itemView.findViewById(R.id.imageViewRace);
            name = (TextView) itemView.findViewById(R.id.textViewRace);
            FrameLayout fr = (FrameLayout) itemView.findViewById(R.id.frameLayoutRace);
            fr.setPadding(5,5,5,5);
            name.setTextSize(16);
        }

        public void setName(String _name) {
            this.name.setText(_name);
        }

        public void setPhoto(@DrawableRes int _photo){
            photo.setImageResource(_photo);
        }

    }

    public class RaceDescriptionAdaptator extends RecyclerView.Adapter<RaceHolder>{

        Vector<String> races;
        Map<String, Integer> images;

        RaceDescriptionAdaptator(){
            races = new Vector<>();
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

        @NonNull
        @Override
        public RaceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater lf = LayoutInflater.from(parent.getContext());
            View v = lf.inflate(R.layout.character_race_view, parent, false);

            RaceHolder rc = new RaceHolder(v);

            return rc;
        }

        @Override
        public void onBindViewHolder(@NonNull RaceHolder holder, int position) {
            String race = races.elementAt(position);

            holder.setName(race);
            Log.d("error",race);
            holder.setPhoto(images.get(race).intValue());


        }

        @Override
        public int getItemCount() {
            return races.size();
        }

        public void add(String element){
            races.add(element);
            this.notifyItemChanged(this.races.size()-1);
        }
    }




}