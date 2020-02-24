package com.example.ddprojet.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ddprojet.CharacterEditionActivity;
import com.example.ddprojet.R;
import com.example.ddprojet.fonction.asyncFonc.RaceInfoGet;
import com.example.ddprojet.fonction.asyncFonc.RacesGet;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ExecutionException;

import connection.Race;
import model.Bonus;
import model.Character;
import util.FragmentEnum;

public class RaceFragment extends Fragment {

    private Race choosed;

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
        protected FrameLayout fr;

        public RaceHolder(@NonNull View itemView) {
            super(itemView);
            photo = (ImageView) itemView.findViewById(R.id.imageViewClass);
            name = (TextView) itemView.findViewById(R.id.textViewClass);
            fr = (FrameLayout) itemView.findViewById(R.id.frameLayoutClass);
            fr.setPadding(5,5,5,5);
        }

        public void setName(String _name) {
            this.name.setText(_name);
        }

        public void setPhoto(@DrawableRes int _photo){
            photo.setImageResource(_photo);
        }

        public void setOnclick(){
            fr.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onButtonShowPopupWindowClick(view, name.getText().toString().toLowerCase());
                }
            });
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
            holder.setOnclick();


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

    public void onButtonShowPopupWindowClick(View view, String name)  {

        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(getActivity().LAYOUT_INFLATER_SERVICE);
        View PopupView = inflater.inflate(R.layout.race_info, null);



        RaceInfoGet getter = new RaceInfoGet(PopupView,(TextView)PopupView.findViewById(R.id.name),(TextView)PopupView.findViewById(R.id.speed),(TextView)PopupView.findViewById(R.id.alignment),(TextView)PopupView.findViewById(R.id.age),
                (TextView)PopupView.findViewById(R.id.size), (TextView)PopupView.findViewById(R.id.sizeDesc),(TextView)PopupView.findViewById(R.id.langDesc) ,(ListView)PopupView.findViewById(R.id.langauges),
                (ListView)PopupView.findViewById(R.id.trait),(ListView)PopupView.findViewById(R.id.Bonus));
        getter.execute(name);

        try {
            choosed = getter.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(PopupView, width, height, focusable);

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        Button bt1 = (Button) PopupView.findViewById(R.id.selectRace);
        bt1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
                onValidation(view);
            }
        });

        Button bt2 = (Button) PopupView.findViewById(R.id.backButton);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });

    }

    public void onValidation(View v){

        CharacterEditionActivity activity = (CharacterEditionActivity)getActivity();

        Character userCharacter = activity.getCharacter();



        userCharacter.setRace(choosed.getName());
        userCharacter.setSpeed(choosed.getSpeed());
        userCharacter.setLanguages(choosed.getLanguages());
        userCharacter.setTraits(choosed.getGlobalTrait().getName());
        HashMap<String, Integer> bonusCharac = new HashMap<>();
        for (Bonus b: choosed.getBonuses()) {
            bonusCharac.put(b.getCharacteristic(), new Integer(b.getValue()));
        }
        activity.setBonusCharac(bonusCharac);
        activity.setTraits(choosed.getGlobalTrait(),choosed.getTraitList());
        if(choosed.getStartProf().hasSkills() !=null){
            userCharacter.setProficiencies(choosed.getStartProf().getNames());
            for (String skill: choosed.getStartProf().hasSkills()) {
                activity.addSkill(skill);
            }
        }

        activity.ChangeFragment(FragmentEnum.Classe.getValue());
    }




}
