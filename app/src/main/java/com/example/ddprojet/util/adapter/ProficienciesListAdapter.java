package com.example.ddprojet.util.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ddprojet.R;
import com.example.ddprojet.activity.CharacterEditionActivity;
import com.example.ddprojet.model.Proficiency;
import com.example.ddprojet.model.ProficienciesList;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class ProficienciesListAdapter extends RecyclerView.Adapter<ProficienciesListAdapter.ProficienciesListHolder>{

    //TODO tester avec le context de l'activité

    protected List<ProficienciesList> proficienciesLists;
    protected List<ProficienciesListHolder> holders;
    protected Callable<Boolean> updateValidation;
    protected CharacterEditionActivity parent_activity;

    public ProficienciesListAdapter(Callable<Boolean> updateValidation, CharacterEditionActivity parent_activity){
        this.proficienciesLists = new ArrayList<>();
        this.holders = new ArrayList<>();
        this.updateValidation = updateValidation;
        this.parent_activity = parent_activity;
    }


    public boolean getValidation() {
        boolean validation = true;
        for (ProficienciesListHolder holder : this.holders){
            if(!holder.getValidation()){
                validation = false;
                break;
            }
        }


        return validation;
    }


    public void addItem(ProficienciesList proficienciesList){
        this.proficienciesLists.add(proficienciesList);
        this.notifyItemInserted( this.proficienciesLists.size() - 1);
    }

    public void addItems(ProficienciesList... proficienciesLists){
        for(ProficienciesList proficienciesList : proficienciesLists){
            this.addItem(proficienciesList);
        }
    }

    @NonNull
    @Override
    public ProficienciesListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ConstraintLayout constraintLayout = (ConstraintLayout)LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.choose_list_layout, parent, false);

        ProficienciesListHolder holder = new ProficienciesListHolder(constraintLayout, this.updateValidation, this.parent_activity);
        this.holders.add(holder);


        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProficienciesListHolder holder, int position) {
        ProficienciesList proficienciesList = this.proficienciesLists.get(position);

        holder.setChoose(proficienciesList.getChoice(), (proficienciesList.hasSkills()) ? proficienciesList.getList().get(0).getName() : "");
        holder.setRecyclerViewProficiencies(proficienciesList);
    }

    @Override
    public int getItemCount() {
        return this.proficienciesLists.size();
    }




    protected class ProficienciesListHolder extends RecyclerView.ViewHolder{

        protected TextView textViewChoose;
        protected RecyclerView recyclerViewProficiencies;
        protected Context context;
        protected Callable<Boolean> updateValidation;
        protected CharacterEditionActivity parent_activity;

        public ProficienciesListHolder(@NonNull View itemView, Callable<Boolean> updateValidation, CharacterEditionActivity parent_activity) {
            super(itemView);

            this.context = itemView.getContext();
            this.textViewChoose = itemView.findViewById(R.id.textViewChoose);
            this.recyclerViewProficiencies = itemView.findViewById(R.id.recyclerViewFeatures);
            this.updateValidation = updateValidation;
            this.parent_activity = parent_activity;
        }

        public boolean getValidation(){
            return ((ProficienciesAdapter)this.recyclerViewProficiencies.getAdapter()).getValidation();
        }


        public void setChoose(int choose, String name){
            this.textViewChoose.setText(String.valueOf(choose) + ((name.isEmpty()) ? "" :  " " + name));
        }


        public void setRecyclerViewProficiencies(ProficienciesList proficiencies){
            RecyclerView.LayoutManager manager = new LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false);
            ProficienciesAdapter adapter = new ProficienciesAdapter(this.updateValidation, this.parent_activity);

            this.recyclerViewProficiencies.setLayoutManager(manager);
            this.recyclerViewProficiencies.setAdapter(adapter);
            adapter.setProficiencies(proficiencies);
        }





        protected class ProficienciesAdapter extends RecyclerView.Adapter<ProficienciesAdapter.ProficienciesHolder>{

            protected List<CheckBox> checkBoxes;
            protected List<CheckBox> checkBoxesChecked;

            protected ProficienciesList proficiencies;
            protected Callable<Boolean> updateValidation;
            protected CharacterEditionActivity parent_activity;

            public ProficienciesAdapter(Callable<Boolean> updateValidation, CharacterEditionActivity parent_activity){
                this.checkBoxes = new ArrayList<>();
                this.checkBoxesChecked = new ArrayList<>();
                this.updateValidation = updateValidation;
                this.parent_activity = parent_activity;
            }

            public boolean getValidation(){
                return (this.checkBoxesChecked.size() == this.proficiencies.getChoice());
            }

            public void setProficiencies(ProficienciesList proficiencies) {
                this.proficiencies = proficiencies;
                this.notifyDataSetChanged();
            }

            @NonNull
            @Override
            public ProficienciesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                FrameLayout frameLayout = (FrameLayout) LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.choose_item_layout, parent, false);

                return new ProficienciesHolder(frameLayout, this.checkBoxes);
            }

            @Override
            public void onBindViewHolder(@NonNull ProficienciesHolder holder, int position) {
                Proficiency proficiencie = this.proficiencies.getProficiencies().get(position);

                holder.setName((proficiencie.isSkill()) ? proficiencie.getSubName() : proficiencie.getName());
                holder.setCheckBoxOnClick(proficiencie, this.checkBoxesChecked, this.proficiencies.getChoice(), this.updateValidation, this.parent_activity);
            }

            @Override
            public int getItemCount() {
                return this.proficiencies.getList().size();
            }




            protected class ProficienciesHolder extends RecyclerView.ViewHolder{

                protected List<CheckBox> checkBoxes;

                protected CheckBox checkBoxProficiencies;

                public ProficienciesHolder(@NonNull View itemView, List<CheckBox> checkBoxes) {
                    super(itemView);

                    this.checkBoxProficiencies = itemView.findViewById(R.id.checkBoxFeatureName);

                    this.checkBoxes = checkBoxes;
                    this.checkBoxes.add(this.checkBoxProficiencies);
                }


                public void setName(String name){
                    this.checkBoxProficiencies.setText(name);
                }

                public void setCheckBoxOnClick(final Proficiency proficiency, final List<CheckBox> checkBoxesChecked, final int count, final Callable<Boolean> updateValidation, final CharacterEditionActivity parent_activity){
                    this.checkBoxProficiencies.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (ProficienciesHolder.this.checkBoxProficiencies.isChecked()) {
                                if (checkBoxesChecked.size() < count) {
                                    if(proficiency.isSkill())
                                        parent_activity.addSkill(proficiency.getSubName());
                                    else
                                        parent_activity.getCharacter().addProficiencies(proficiency);
                                    checkBoxesChecked.add(ProficienciesHolder.this.checkBoxProficiencies);

                                    if (checkBoxesChecked.size() == count)
                                        for(CheckBox checkBox : ProficienciesHolder.this.checkBoxes)
                                            if (!checkBox.isChecked())
                                                checkBox.setClickable(false);

                                    try {
                                        updateValidation.call();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }else {
                                if (checkBoxesChecked.contains(ProficienciesHolder.this.checkBoxProficiencies)) {
                                    if(proficiency.isSkill())
                                        parent_activity.removeSkill(proficiency.getSubName());
                                    else
                                        parent_activity.getCharacter().removeProficiencies(proficiency);
                                    checkBoxesChecked.remove(ProficienciesHolder.this.checkBoxProficiencies);

                                    try {
                                        updateValidation.call();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                                for(CheckBox checkBox : ProficienciesHolder.this.checkBoxes)
                                    checkBox.setClickable(true);
                            }
                        }
                    });
                }
            }

        }

    }

}