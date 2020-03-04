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
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ddprojet.activity.CharacterEditionActivity;
import com.example.ddprojet.R;
import com.example.ddprojet.fonction.asyncFonc.RaceInfoGet;
import com.example.ddprojet.fonction.asyncFonc.RacesGet;

import java.util.HashMap;
import java.util.Vector;
import java.util.concurrent.ExecutionException;

import com.example.ddprojet.connection.Race;
import com.example.ddprojet.model.Bonus;
import com.example.ddprojet.model.Character;
import com.example.ddprojet.util.FragmentEnum;
import com.example.ddprojet.util.RaceEnum;

public class RaceFragment extends Fragment {

    private Race choosed;
    private PopupWindow popupWindow;

    //TODO saved states !

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

        ((Button)v.findViewById(R.id.backButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((CharacterEditionActivity)RaceFragment.this.getActivity()).ChangeFragment(FragmentEnum.RaceClassSelection);
            }
        });

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

        RaceDescriptionAdaptator(){
            races = new Vector<>();
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
            holder.setPhoto(RaceEnum.getValue(race));
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
        View popupView = inflater.inflate(R.layout.race_info, null);



        RaceInfoGet getter = new RaceInfoGet(popupView, (TextView)popupView.findViewById(R.id.name), (TextView)popupView.findViewById(R.id.speed), (TextView)popupView.findViewById(R.id.alignement),
                (TextView)popupView.findViewById(R.id.age), (TextView)popupView.findViewById(R.id.size), (TextView)popupView.findViewById(R.id.sizeDesc),
                (TextView)popupView.findViewById(R.id.langDesc) ,(RecyclerView)popupView.findViewById(R.id.languages), (RecyclerView)popupView.findViewById(R.id.trait),
                (RecyclerView) popupView.findViewById(R.id.Bonus));
        getter.execute(name);

        try {
            choosed = getter.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        if(this.popupWindow != null)
            this.popupWindow.dismiss();
        this.popupWindow = new PopupWindow(popupView, width, height, focusable);

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        this.popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        Button bt1 = (Button) popupView.findViewById(R.id.selectRace);
        bt1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                RaceFragment.this.popupWindow.dismiss();
                onValidation(view);
            }
        });

        Button bt2 = (Button) popupView.findViewById(R.id.backButton);
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

        activity.ChangeFragment(FragmentEnum.RaceClassSelection);
    }




}
