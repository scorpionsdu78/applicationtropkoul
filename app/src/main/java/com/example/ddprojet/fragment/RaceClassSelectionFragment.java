package com.example.ddprojet.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.ddprojet.CharacterEditionActivity;
import com.example.ddprojet.R;

import util.ClassEnum;
import util.CustomViewPager;
import util.FragmentEnum;
import util.RaceEnum;

public class RaceClassSelectionFragment extends Fragment {

    protected CharacterEditionActivity parent_activity;
    protected View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.race_class_selection_layout, container, false);

        this.parent_activity = (CharacterEditionActivity)this.getActivity();

        CardView cardViewRace = this.view.findViewById(R.id.cardViewRace);
        CardView cardViewClass = this.view.findViewById(R.id.cardViewClass);

        cardViewRace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RaceClassSelectionFragment.this.parent_activity.ChangeFragment(FragmentEnum.Race);
            }
        });
        cardViewClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RaceClassSelectionFragment.this.parent_activity.ChangeFragment(FragmentEnum.Class);
            }
        });

        Button buttonNext = this.view.findViewById(R.id.buttonNext);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RaceClassSelectionFragment.this.parent_activity.ChangeFragment(FragmentEnum.CaracComp);
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


        String class_ = this.parent_activity.getCharacter().getClass_();

        ImageView imageViewClass = this.view.findViewById(R.id.imageViewClass);
        imageViewClass.setImageResource(ClassEnum.getValue(class_));

    }
}
