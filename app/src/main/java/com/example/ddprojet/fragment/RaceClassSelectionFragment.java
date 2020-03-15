package com.example.ddprojet.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.ddprojet.R;
import com.example.ddprojet.activity.CharacterEditionActivity;
import com.example.ddprojet.util.ClassEnum;
import com.example.ddprojet.util.FragmentEnum;
import com.example.ddprojet.util.RaceEnum;

public class RaceClassSelectionFragment extends Fragment {

    protected CharacterEditionActivity parent_activity;
    protected View view;
    protected View.OnClickListener buttonNextOnClickListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.race_class_selection_layout, container, false);

        this.parent_activity = (CharacterEditionActivity)this.getActivity();

        CardView cardViewRace = this.view.findViewById(R.id.cardViewRace);

        cardViewRace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RaceClassSelectionFragment.this.parent_activity.ChangeFragment(FragmentEnum.Race);
            }
        });


        Button buttonNext = this.view.findViewById(R.id.buttonNext);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RaceClassSelectionFragment.this.parent_activity.ChangeFragment(FragmentEnum.BonusesSelection);
            }
        });


        update();

        return this.view;
    }


    @Override
    public void onResume() {
        super.onResume();

        update();
    }

    protected void update(){

        String race = this.parent_activity.getCharacter().getRace();

        ImageView imageViewRace = this.view.findViewById(R.id.imageViewRace);
        imageViewRace.setImageResource(RaceEnum.getValue(race));


        TextView textViewRace = this.view.findViewById(R.id.textViewRaceName);
        CardView cardViewClass = this.view.findViewById(R.id.cardViewClass);

        if(race != null){
            textViewRace.setText(race);

            cardViewClass.setCardBackgroundColor(0x34FFFFFF);
            cardViewClass.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RaceClassSelectionFragment.this.parent_activity.ChangeFragment(FragmentEnum.Class);
                }
            });
        }
        else{
            textViewRace.setText("Select a race");

            cardViewClass.setCardBackgroundColor(0xD5969696);
            cardViewClass.setOnClickListener(null);
        }


        String class_ = this.parent_activity.getCharacter().getClass_();

        ImageView imageViewClass = this.view.findViewById(R.id.imageViewClass);
        imageViewClass.setImageResource(ClassEnum.getValue(class_));


        TextView textViewClass = this.view.findViewById(R.id.textViewClassName);

        if(class_ != null){
            textViewClass.setText(class_);
        }
        else{
            textViewClass.setText("Select a class");
        }


        Button buttonNext = this.view.findViewById(R.id.buttonNext);

        if(race != null && class_ != null){
            buttonNext.setEnabled(true);
        }
        else{
            buttonNext.setEnabled(false);
        }

    }

}
