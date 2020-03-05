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
import com.example.ddprojet.model.Proficiencies;
import com.example.ddprojet.model.ProficienciesList;

import java.util.ArrayList;
import java.util.List;

public class ProficienciesListAdapter extends RecyclerView.Adapter<ProficienciesListAdapter.ProficienciesListHolder>{

    //TODO tester avec le context de l'activité

    protected List<ProficienciesList> proficienciesLists;

    public ProficienciesListAdapter(){
        this.proficienciesLists = new ArrayList<>();
    }

    public void addItem(ProficienciesList proficienciesList){
        this.proficienciesLists.add(proficienciesList);
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
                .inflate(R.layout.features_list_layout, parent, false);

        return new ProficienciesListHolder(constraintLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull ProficienciesListHolder holder, int position) {
        ProficienciesList proficienciesList = this.proficienciesLists.get(position);

        holder.setChoose(proficienciesList.getChoice());
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

        public ProficienciesListHolder(@NonNull View itemView) {
            super(itemView);
            this.context = itemView.getContext();
            this.textViewChoose = itemView.findViewById(R.id.textViewChoose);
            this.recyclerViewProficiencies = itemView.findViewById(R.id.recyclerViewFeatures);
        }


        public void setChoose(int choose){
            this.textViewChoose.setText(String.valueOf(choose));
        }


        public void setRecyclerViewProficiencies(ProficienciesList proficiencies){
            RecyclerView.LayoutManager manager = new LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false);
            ProficienciesAdapter adapter = new ProficienciesAdapter();

            this.recyclerViewProficiencies.setLayoutManager(manager);
            this.recyclerViewProficiencies.setAdapter(adapter);
            adapter.setProficiencies(proficiencies);

            /*int count = adapter.getItemCount();
            Log.i("DulcheE", "" + count);

            for(int i = 0; i < count; i++){
                Log.i("DulcheE", "" + i);
                View v = manager.findViewByPosition(i);

                ((CheckBox)v.findViewById(R.id.checkBoxProficienciesName)).setChecked(true);
            }*/
        }





        protected class ProficienciesAdapter extends RecyclerView.Adapter<ProficienciesAdapter.ProficienciesHolder>{

            protected List<CheckBox> checkBoxes;
            protected List<CheckBox> checkBoxesChecked;

            protected ProficienciesList proficiencies;

            public ProficienciesAdapter(){
                this.checkBoxes = new ArrayList<>();
                this.checkBoxesChecked = new ArrayList<>();
            }

            public void setProficiencies(ProficienciesList proficiencies) {
                this.proficiencies = proficiencies;
            }

            @NonNull
            @Override
            public ProficienciesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                FrameLayout frameLayout = (FrameLayout) LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.feature_item_layout, parent, false);

                return new ProficienciesHolder(frameLayout, this.checkBoxes);
            }

            @Override
            public void onBindViewHolder(@NonNull ProficienciesHolder holder, int position) {
                Proficiencies proficiencie = this.proficiencies.getProficiencies().get(position);

                holder.setName(proficiencie.getName());
                holder.setCheckBoxOnClick(this.checkBoxesChecked, this.proficiencies.getChoice());
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

                public void setCheckBoxOnClick(final List<CheckBox> checkBoxesChecked, final int count){
                    this.checkBoxProficiencies.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (ProficienciesHolder.this.checkBoxProficiencies.isChecked()) {
                                if (checkBoxesChecked.size() < count) {
                                    checkBoxesChecked.add(ProficienciesHolder.this.checkBoxProficiencies);

                                    if (checkBoxesChecked.size() == count)
                                        for(CheckBox checkBox : ProficienciesHolder.this.checkBoxes)
                                            if (!checkBox.isChecked())
                                                checkBox.setClickable(false);
                                }
                            }else {
                                if (checkBoxesChecked.contains(ProficienciesHolder.this.checkBoxProficiencies)) {
                                    checkBoxesChecked.remove(ProficienciesHolder.this.checkBoxProficiencies);
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