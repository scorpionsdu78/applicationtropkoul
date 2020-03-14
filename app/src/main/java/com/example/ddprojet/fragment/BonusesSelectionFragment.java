package com.example.ddprojet.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ddprojet.activity.CharacterEditionActivity;
import com.example.ddprojet.R;
import com.example.ddprojet.model.Feature;
import com.example.ddprojet.model.ProficienciesList;
import com.example.ddprojet.model.Trait;
import com.example.ddprojet.model.TraitsList;
import com.example.ddprojet.util.FragmentEnum;
import com.example.ddprojet.util.adapter.FeaturesListAdapter;
import com.example.ddprojet.util.adapter.ProficienciesListAdapter;
import com.example.ddprojet.util.adapter.TraitsListAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class BonusesSelectionFragment extends Fragment {

    private CharacterEditionActivity parent_activity;
    private View view;

    public List<Feature> featuresList;
    public TraitsList traitsList;
    public List<ProficienciesList> proficienciesList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.bonuses_selection_layout, container, false);

        //Init of the parent activity
        this.parent_activity = (CharacterEditionActivity)this.getActivity();

        Button buttonNext = this.view.findViewById(R.id.buttonNext);

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BonusesSelectionFragment.this.parent_activity.ChangeFragment(FragmentEnum.CharacSkills);
            }
        });

        Button buttonBack = this.view.findViewById(R.id.buttonBack);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BonusesSelectionFragment.this.parent_activity.ChangeFragment(FragmentEnum.RaceClassSelection);
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        this.featuresList = this.parent_activity.getFeatureToChoose();
        this.traitsList = this.parent_activity.getOptionalTraits();
        this.proficienciesList = this.parent_activity.getListToChoseFrom();


        RecyclerView recyclerViewFeaturesList = this.view.findViewById(R.id.layoutFeatures).findViewById(R.id.recyclerViewFeaturesLists);

        RecyclerView.LayoutManager managerFeatures = new LinearLayoutManager(this.getActivity(), LinearLayoutManager.VERTICAL, false);
        final FeaturesListAdapter adapterFeatures = new FeaturesListAdapter(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return BonusesSelectionFragment.this.updateValidation();
            }
        }, this.parent_activity.getCharacter());

        if(this.featuresList != null){
            adapterFeatures.addItems(this.featuresList.toArray(new Feature[0]));

            recyclerViewFeaturesList.setLayoutManager(managerFeatures);
        }
        recyclerViewFeaturesList.setAdapter(adapterFeatures);


        TextView textViewLabelTraits = this.view.findViewById(R.id.layoutTraits).findViewById(R.id.textViewLabel);
        textViewLabelTraits.setText("Traits :");
        RecyclerView recyclerViewTraitsList = this.view.findViewById(R.id.layoutTraits).findViewById(R.id.recyclerViewFeaturesLists);

        RecyclerView.LayoutManager managerTraits = new LinearLayoutManager(this.getActivity(), LinearLayoutManager.VERTICAL, false);
        final TraitsListAdapter adapterTraits = new TraitsListAdapter(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return BonusesSelectionFragment.this.updateValidation();
            }
        }, this.parent_activity.getCharacter());

        if(this.traitsList != null) {
            adapterTraits.setTraitsList(this.traitsList);

            recyclerViewTraitsList.setLayoutManager(managerTraits);
        }
        recyclerViewTraitsList.setAdapter(adapterTraits);



        TextView textViewLabelProficiencies = this.view.findViewById(R.id.layoutProficiencies).findViewById(R.id.textViewLabel);
        textViewLabelProficiencies.setText("Proficiencies :");
        RecyclerView recyclerViewProficienciesList = this.view.findViewById(R.id.layoutProficiencies).findViewById(R.id.recyclerViewFeaturesLists);

        RecyclerView.LayoutManager managerProficiencies = new LinearLayoutManager(this.getActivity(), LinearLayoutManager.VERTICAL, false);
        final ProficienciesListAdapter adapterProficiencies = new ProficienciesListAdapter(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return BonusesSelectionFragment.this.updateValidation();
            }
        }, this.parent_activity);
        if(this.proficienciesList != null)
            adapterProficiencies.addItems(this.proficienciesList.toArray(new ProficienciesList[0]));

        recyclerViewProficienciesList.setLayoutManager(managerProficiencies);
        recyclerViewProficienciesList.setAdapter(adapterProficiencies);


        this.view.findViewById(R.id.buttonNext).setEnabled(false);
    }


    public boolean updateValidation(){
        boolean featuresValidation = ((FeaturesListAdapter)((RecyclerView)this.view.findViewById(R.id.layoutFeatures)
                .findViewById(R.id.recyclerViewFeaturesLists))
                .getAdapter()).getValidation();
        boolean traitsValidation = ((TraitsListAdapter)((RecyclerView)this.view.findViewById(R.id.layoutTraits)
                .findViewById(R.id.recyclerViewFeaturesLists))
                .getAdapter()).getValidation();
        boolean proficienciesValidation = ((ProficienciesListAdapter)((RecyclerView)this.view.findViewById(R.id.layoutProficiencies)
                .findViewById(R.id.recyclerViewFeaturesLists))
                .getAdapter()).getValidation();

        this.view.findViewById(R.id.buttonNext).setEnabled(featuresValidation && traitsValidation && proficienciesValidation);


        return featuresValidation && traitsValidation && proficienciesValidation;
    }


}
