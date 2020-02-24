package com.example.ddprojet.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.ddprojet.CharacterEditionActivity;
import com.example.ddprojet.R;

import util.CustomViewPager;
import util.FragmentEnum;

public class RaceClassSelectionFragment extends Fragment {

    CharacterEditionActivity parent_activity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.race_class_selection_layout, container, false);

        this.parent_activity = (CharacterEditionActivity)this.getActivity();

        CardView cardViewRace = v.findViewById(R.id.cardViewRace);
        CardView cardViewClass = v.findViewById(R.id.cardViewClass);

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

        Button buttonNext = v.findViewById(R.id.buttonNext);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RaceClassSelectionFragment.this.parent_activity.ChangeFragment(FragmentEnum.CaracComp);
            }
        });

        return v;
    }
}
