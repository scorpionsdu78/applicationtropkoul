package com.example.ddprojet.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ddprojet.CharacterEditionActivity;
import com.example.ddprojet.R;

import java.util.HashMap;
import java.util.Map;

import util.FragmentEnum;

public class CaracCompFragment extends Fragment {

    protected View view;

    protected CharacterEditionActivity parent_activity;

    protected LinearLayout strength_layout;
    protected LinearLayout dexterity_layout;
    protected LinearLayout constitution_layout;
    protected LinearLayout intelligence_layout;
    protected LinearLayout wisdom_layout;
    protected LinearLayout charisma_layout;

    protected int characPoints;
    protected Map<Integer, Integer> characPointsCosts;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.carac_comp_layout, container, false);

        Button buttonBack = this.view.findViewById(R.id.buttonBack);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CaracCompFragment.this.parent_activity.ChangeFragment(FragmentEnum.RaceClassSelection);
            }
        });

        //Init of the parent activity
        this.parent_activity = (CharacterEditionActivity)this.getActivity();

        //Init the number of point to attribute to the characs
        this.characPoints = 27 + this.parent_activity.levelBonusCharac();

        this.characPointsCosts = new HashMap<>();
        this.characPointsCosts.put(8, 0);
        this.characPointsCosts.put(9, 1);
        this.characPointsCosts.put(10, 2);
        this.characPointsCosts.put(11, 3);
        this.characPointsCosts.put(12, 4);
        this.characPointsCosts.put(13, 5);
        this.characPointsCosts.put(14, 7);
        this.characPointsCosts.put(15, 9);

        updateCharacPoints();


        //Init of the characs layout and values
        this.strength_layout = (LinearLayout)this.view.findViewById(R.id.layoutStrength);
        init(this.strength_layout);

        /*this.dexterity_layout = (LinearLayout)this.view.findViewById(R.id.layoutDexterity);
        init(this.dexterity_layout);

        this.constitution_layout = (LinearLayout)this.view.findViewById(R.id.layoutConstitution);
        init(this.constitution_layout);

        this.intelligence_layout = (LinearLayout)this.view.findViewById(R.id.layoutIntelligence);
        init(this.intelligence_layout);

        this.wisdom_layout = (LinearLayout)this.view.findViewById(R.id.layoutWisdom);
        init(this.wisdom_layout);

        this.charisma_layout = (LinearLayout)this.view.findViewById(R.id.layoutCharisma);
        init(this.charisma_layout);


        //Init of the spells checkbox
        if(this.parent_activity.containBonusSkill("Athletics"))
            ((CheckBox)this.view.findViewById(R.id.checkBoxAthletics)).setChecked(true);



        if(this.parent_activity.containBonusSkill("Acrobatics"))
            ((CheckBox)this.view.findViewById(R.id.checkBoxAcrobatics)).setChecked(true);

        if(this.parent_activity.containBonusSkill("Sleight of Hand"))
            ((CheckBox)this.view.findViewById(R.id.checkBoxSleight_of_Hand)).setChecked(true);

        if(this.parent_activity.containBonusSkill("Stealth"))
            ((CheckBox)this.view.findViewById(R.id.checkBoxStealth)).setChecked(true);



        if(this.parent_activity.containBonusSkill("Arcana"))
            ((CheckBox)this.view.findViewById(R.id.checkBoxArcana)).setChecked(true);

        if(this.parent_activity.containBonusSkill("History"))
            ((CheckBox)this.view.findViewById(R.id.checkBoxHistory)).setChecked(true);

        if(this.parent_activity.containBonusSkill("Investigation"))
            ((CheckBox)this.view.findViewById(R.id.checkBoxInvestigation)).setChecked(true);

        if(this.parent_activity.containBonusSkill("Nature"))
            ((CheckBox)this.view.findViewById(R.id.checkBoxNature)).setChecked(true);

        if(this.parent_activity.containBonusSkill("Religion"))
            ((CheckBox)this.view.findViewById(R.id.checkBoxReligion)).setChecked(true);



        if(this.parent_activity.containBonusSkill("Animal Handling"))
            ((CheckBox)this.view.findViewById(R.id.checkBoxAnimalHandling)).setChecked(true);

        if(this.parent_activity.containBonusSkill("Insigth"))
            ((CheckBox)this.view.findViewById(R.id.checkBoxInsight)).setChecked(true);

        if(this.parent_activity.containBonusSkill("Medicine"))
            ((CheckBox)this.view.findViewById(R.id.checkBoxMedicine)).setChecked(true);

        if(this.parent_activity.containBonusSkill("Perception"))
            ((CheckBox)this.view.findViewById(R.id.checkBoxPerception)).setChecked(true);

        if(this.parent_activity.containBonusSkill("Survival"))
            ((CheckBox)this.view.findViewById(R.id.checkBoxSurvival)).setChecked(true);



        if(this.parent_activity.containBonusSkill("Deception"))
            ((CheckBox)this.view.findViewById(R.id.checkBoxDeception)).setChecked(true);

        if(this.parent_activity.containBonusSkill("Intimidation"))
            ((CheckBox)this.view.findViewById(R.id.checkBoxIntimidation)).setChecked(true);

        if(this.parent_activity.containBonusSkill("Performance"))
            ((CheckBox)this.view.findViewById(R.id.checkBoxPerformance)).setChecked(true);

        if(this.parent_activity.containBonusSkill("Persuasion"))
            ((CheckBox)this.view.findViewById(R.id.checkBoxPersuasion)).setChecked(true);



        CheckBox checkBoxSavingThrows = this.view.findViewById(R.id.checkBoxSavingThrows);
        checkBoxSavingThrows.setChecked(true);*/

        return this.view;
    }




    protected void init(final LinearLayout parent){
        /*onClickPlus(parent);
        onClickMinus(parent);*/

        String characName = ((TextView)parent.findViewById(R.id.textViewLabel)).getContentDescription().toString();
        TextView textViewValue = (TextView)parent.findViewById(R.id.textViewValue);
/*
        int initValue = 8;
        initValue += this.parent_activity.getBonusCharac(characName);
         */

        if(this.parent_activity.getCharacter().getSavingThrows().contains(characName)) {
            Log.i("DulcheE", "Saving trows " + characName);
            CheckBox checkBoxSavingThrows = parent.findViewById(R.id.checkBoxSavingThrows);
            checkBoxSavingThrows.setChecked(true);
            checkBoxSavingThrows.setText("I'm checked");
            checkBoxSavingThrows.setText("I'm checked");
        }/*

        int requierement = this.parent_activity.getClassRequierement(characName);
        while(initValue < requierement){
            initValue++;
            this.characPoints--;
            this.updateCharacPoints();
        }

        textViewValue.setText(String.valueOf(initValue));
        this.parent_activity.getCharacter().getCharacteristic().put(characName, initValue);

        updateSkillsValue(parent);*/
    }


    protected void onClickPlus(final LinearLayout parent){

        Button button = (Button)parent.findViewById(R.id.button2);


        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                addValueCharacTextView(parent);
            }

        });

    }

    protected void onClickMinus(final LinearLayout parent){

        Button button = (Button)parent.findViewById(R.id.button);


        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                soustractValueCharacTextView(parent);
            }

        });

    }


    protected void addValueCharacTextView(LinearLayout parent){

        if(this.characPoints > 0) {

            TextView characTextView = (TextView)parent.findViewById(R.id.textViewValue);
            String number = characTextView.getText().toString();

            int number_parsed = 0;
            try{
                number_parsed = Integer.parseInt(number);

            } catch (NumberFormatException e) {
                return;
            }

            number_parsed++;

            if(number_parsed > 15)
                return;

            int cost = this.characPointsCosts.get(number_parsed);
            if(this.characPoints < cost)
                return;

            characTextView.setText(String.valueOf(number_parsed));

            String characName = ((TextView) parent.findViewById(R.id.textViewLabel)).getContentDescription().toString();
            this.parent_activity.getCharacter().getCharacteristic().put(characName, number_parsed);

            this.characPoints -= cost;
            this.updateCharacPoints();

            this.updateSkillsValue(parent);

        }

    }

    protected void soustractValueCharacTextView(LinearLayout parent){

        TextView characTextView = (TextView)parent.findViewById(R.id.textViewValue);
        String characName = ((TextView)parent.findViewById(R.id.textViewLabel)).getContentDescription().toString();
        String number = characTextView.getText().toString();

        int number_parsed = 0;
        try{
            number_parsed = Integer.parseInt(number);
        } catch (NumberFormatException e) {
            return;
        }

        int cost = this.characPointsCosts.get(number_parsed);
        number_parsed--;

        int min_limit = 8;

        min_limit += this.parent_activity.getBonusCharac(characName);

        if(number_parsed >= min_limit && number_parsed >= this.parent_activity.getClassRequierement(characName)) {
            characTextView.setText(String.valueOf(number_parsed));

            this.parent_activity.getCharacter().getCharacteristic().put(characName, number_parsed);

            this.characPoints += cost;
            this.updateCharacPoints();

            this.updateSkillsValue(parent);
        }
    }


    protected void updateSkillsValue(LinearLayout parent){

        String characName = ((TextView)parent.findViewById(R.id.textViewLabel)).getContentDescription().toString();
        TextView savingThrows = (TextView)parent.findViewById(R.id.textViewSavingThrows);
        updateSavingThrowsValue(savingThrows, parent);

        switch (characName){

            case "STR" :

                TextView textViewAthletics = (TextView)parent.findViewById(R.id.textViewAthletics);
                updateSkillValue(textViewAthletics, "Athletics", parent);

                break;


            case "DEX" :

                TextView textViewAcrobatics = (TextView)parent.findViewById(R.id.textViewAcrobatics);
                updateSkillValue(textViewAcrobatics, "Acrobatics", parent);

                TextView textViewSleight_of_Hand = (TextView)parent.findViewById(R.id.textViewSleight_of_Hand);
                updateSkillValue(textViewSleight_of_Hand, "Sleight of Hand", parent);

                TextView textViewStealth = (TextView)parent.findViewById(R.id.textViewStealth);
                updateSkillValue(textViewStealth, "Stealth ", parent);

                break;


            case "CON" :

                break;


            case "INT" :

                TextView textViewArcana = (TextView)parent.findViewById(R.id.textViewArcana);
                updateSkillValue(textViewArcana, "Arcana", parent);

                TextView textViewHistory = (TextView)parent.findViewById(R.id.textViewHistory);
                updateSkillValue(textViewHistory, "History", parent);

                TextView textViewInvestigation = (TextView)parent.findViewById(R.id.textViewInvestigation);
                updateSkillValue(textViewInvestigation, "Investigation", parent);

                TextView textViewNature = (TextView)parent.findViewById(R.id.textViewNature);
                updateSkillValue(textViewNature, "Nature", parent);

                TextView textViewReligion = (TextView)parent.findViewById(R.id.textViewReligion);
                updateSkillValue(textViewReligion, "Religion", parent);

                break;


            case "WIS" :

                TextView textViewAnimalHandling = (TextView)parent.findViewById(R.id.textViewAnimalHandling);
                updateSkillValue(textViewAnimalHandling, "Animal Handling", parent);

                TextView textViewInsight = (TextView)parent.findViewById(R.id.textViewInsight);
                updateSkillValue(textViewInsight, "Insight", parent);

                TextView textViewMedicine = (TextView)parent.findViewById(R.id.textViewMedicine);
                updateSkillValue(textViewMedicine, "Medicine", parent);

                TextView textViewPerception = (TextView)parent.findViewById(R.id.textViewPerception);
                updateSkillValue(textViewPerception, "Perception", parent);

                TextView textViewSurvival = (TextView)parent.findViewById(R.id.textViewSurvival);
                updateSkillValue(textViewSurvival, "Survival", parent);

                break;


            case "CHA" :

                TextView textViewDeception = (TextView)parent.findViewById(R.id.textViewDeception);
                updateSkillValue(textViewDeception, "Deception", parent);

                TextView textViewIntimidation = (TextView)parent.findViewById(R.id.textViewIntimidation);
                updateSkillValue(textViewIntimidation, "Intimidation", parent);

                TextView textViewPerformance = (TextView)parent.findViewById(R.id.textViewPerformance);
                updateSkillValue(textViewPerformance, "Performance", parent);

                TextView textViewPersuasion = (TextView)parent.findViewById(R.id.textViewPersuasion);
                updateSkillValue(textViewPersuasion, "Persuasion", parent);

                break;
        }

    }


    protected void updateSavingThrowsValue(TextView textView, LinearLayout parent){

        String parent_value = ((TextView)parent.findViewById(R.id.textViewValue)).getText().toString();
        String SavingThrown_value = ((CheckBox)parent.findViewById(R.id.checkBoxSavingThrows)).getText().toString();

        try{
            int value = (int)Math.floor((Float.parseFloat(parent_value) - 10) / 2);

            if(this.parent_activity.getCharacter().ContainsSavingThrows(SavingThrown_value))
                value += this.parent_activity.levelBonusSkill();

            textView.setText(String.valueOf(value));

        } catch (NumberFormatException e) {
            return;
        }

    }


    protected void updateSkillValue(TextView textView, String skillName, LinearLayout parent){

        String parent_value = ((TextView)parent.findViewById(R.id.textViewValue)).getText().toString();

        try{
            int value = (int)Math.floor((Float.parseFloat(parent_value) - 10) / 2);

            if(this.parent_activity.containBonusSkill(skillName))
                value += this.parent_activity.levelBonusSkill();

            textView.setText(String.valueOf(value));
            this.parent_activity.getCharacter().getSkills().put(skillName, value);

        } catch (NumberFormatException e) {
            return;
        }

    }


    protected void updateCharacPoints(){

        ((TextView)this.view.findViewById(R.id.textViewCharacPoints)).setText(String.valueOf(this.characPoints));

        if(this.characPoints <= 4){
            this.view.findViewById(R.id.buttonNext).setEnabled(true);
        }
        else{
            this.view.findViewById(R.id.buttonNext).setEnabled(false);
        }

    }

}
