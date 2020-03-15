package com.example.ddprojet.fragment;

import android.os.Bundle;
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

import com.example.ddprojet.R;
import com.example.ddprojet.activity.CharacterEditionActivity;
import com.example.ddprojet.util.FragmentEnum;

import java.util.HashMap;
import java.util.Map;

// Fragment to select the level of the characteristics of the character
public class CharacSkillsFragment extends Fragment {

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
        this.view = inflater.inflate(R.layout.charac_skills_layout, container, false);

        //Init of the parent activity
        this.parent_activity = (CharacterEditionActivity)this.getActivity();

        this.view.findViewById(R.id.buttonNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharacSkillsFragment.this.validate();
            }
        });

        //Init the number of point to attribute to the characs
        this.characPoints = 27 + this.parent_activity.levelBonusCharac();

        //Init the cost of points by the level of the characteristic
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
        this.strength_layout = this.view.findViewById(R.id.layoutStrength);
        this.dexterity_layout = this.view.findViewById(R.id.layoutDexterity);
        this.constitution_layout = this.view.findViewById(R.id.layoutConstitution);
        this.intelligence_layout = this.view.findViewById(R.id.layoutIntelligence);
        this.wisdom_layout = this.view.findViewById(R.id.layoutWisdom);
        this.charisma_layout = this.view.findViewById(R.id.layoutCharisma);


        return this.view;
    }


    @Override
    public void onResume() {
        super.onResume();

        // Init the characteristics
        init(this.strength_layout);
        init(this.dexterity_layout);
        init(this.constitution_layout);
        init(this.intelligence_layout);
        init(this.wisdom_layout);
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
    }

    // Init the layout of the characteristic
    // Setup buttons, the init value of the characteristic, and the value of the skills
    protected void init(final LinearLayout parent){
        onClickPlus(parent);
        onClickMinus(parent);

        String characName = parent.findViewById(R.id.textViewLabel).getContentDescription().toString();
        TextView textViewValue = parent.findViewById(R.id.textViewValue);

        int initValue = 8;
        initValue += this.parent_activity.getBonusCharac(characName);


        if(this.parent_activity.getCharacter().getSavingThrows().contains(characName)) {
            CheckBox checkBoxSavingThrows = parent.findViewById(R.id.checkBoxSavingThrows);
            checkBoxSavingThrows.setChecked(true);
        }

        int requierement = this.parent_activity.getClassRequierement(characName);
        while(initValue < requierement){
            initValue++;
            this.characPoints--;
            this.updateCharacPoints();
        }

        textViewValue.setText(String.valueOf(initValue));
        this.parent_activity.getCharacter().getCharacteristic().put(characName, initValue);

        updateSkillsValue(parent);
    }


    // Setup the function for the button plus of the layout for the characteristic
    protected void onClickPlus(final LinearLayout parent){

        Button button = parent.findViewById(R.id.button2);


        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                addValueCharacTextView(parent);
            }

        });

    }

    // Setup the function for the button minus of the layout for the characteristic
    protected void onClickMinus(final LinearLayout parent){

        Button button = parent.findViewById(R.id.button);


        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                soustractValueCharacTextView(parent);
            }

        });

    }


    // Increment the value of the characteristic if we have enough points to pay the cost and if we are below the limit and update the value of the skills
    protected void addValueCharacTextView(LinearLayout parent){

        if(this.characPoints > 0) {

            TextView characTextView = parent.findViewById(R.id.textViewValue);
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

            String characName = parent.findViewById(R.id.textViewLabel).getContentDescription().toString();
            this.parent_activity.getCharacter().getCharacteristic().put(characName, number_parsed);

            this.characPoints -= cost;
            this.updateCharacPoints();

            this.updateSkillsValue(parent);

        }

    }

    // Decrement the value of the characteristic and if we are upside the limit and update the value of the skills
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

    // Update the value of every skills of the characteristic which just changed
    protected void updateSkillsValue(LinearLayout parent){

        String characName = parent.findViewById(R.id.textViewLabel).getContentDescription().toString();
        TextView savingThrows = parent.findViewById(R.id.textViewSavingThrows);
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

    // Update the value of one skill
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

    // Update the value of the saving throws of the characteristic which just changed
    protected void updateSavingThrowsValue(TextView textView, LinearLayout parent){

        String parent_value = ((TextView)parent.findViewById(R.id.textViewValue)).getText().toString();
        String SavingThrown_value = ((CheckBox)parent.findViewById(R.id.checkBoxSavingThrows)).getContentDescription().toString();

        try{
            int value = (int)Math.floor((Float.parseFloat(parent_value) - 10) / 2);

            if(this.parent_activity.getCharacter().ContainsSavingThrows(SavingThrown_value))
                value += this.parent_activity.levelBonusSkill();

            textView.setText(String.valueOf(value));

        } catch (NumberFormatException e) {
            return;
        }

    }

    // On changes to the characPoint, we update the value on the layout and enable or not the button to go to the next fragment
    protected void updateCharacPoints(){

        ((TextView)this.view.findViewById(R.id.textViewCharacPoints)).setText(String.valueOf(this.characPoints));

        if(this.characPoints <= 4){
            this.view.findViewById(R.id.buttonNext).setEnabled(true);
        }
        else{
            this.view.findViewById(R.id.buttonNext).setEnabled(false);
        }

    }


    // When we validate the step and go to the next one
    protected void validate() {
        this.parent_activity.ChangeFragment(FragmentEnum.Spells);
    }

}
