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
        holder.setRecyclerViewProficiencies(proficienciesList.getProficiencies());
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


        public void setRecyclerViewProficiencies(List<Proficiencies> proficiencies){
            RecyclerView.LayoutManager manager = new LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false);
            ProficienciesAdapter adapter = new ProficienciesAdapter();

            this.recyclerViewProficiencies.setLayoutManager(manager);
            this.recyclerViewProficiencies.setAdapter(adapter);
            adapter.addItems(proficiencies.toArray(new Proficiencies[0]));

            /*int count = adapter.getItemCount();
            Log.i("DulcheE", "" + count);

            for(int i = 0; i < count; i++){
                Log.i("DulcheE", "" + i);
                View v = manager.findViewByPosition(i);

                ((CheckBox)v.findViewById(R.id.checkBoxProficienciesName)).setChecked(true);
            }*/
        }





        protected class ProficienciesAdapter extends RecyclerView.Adapter<ProficienciesAdapter.ProficienciesHolder>{

            protected int tagCount = 0;

            protected List<Proficiencies> proficiencies;

            public ProficienciesAdapter(){
                this.proficiencies = new ArrayList<>();
            }

            public void addItem(Proficiencies proficiencie){
                this.proficiencies.add(proficiencie);
            }

            public void addItems(Proficiencies ...proficiencies){
                for(Proficiencies proficiencie : proficiencies)
                    this.addItem(proficiencie);
            }

            @NonNull
            @Override
            public ProficienciesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                FrameLayout frameLayout = (FrameLayout) LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.feature_item_layout, parent, false);

                return new ProficienciesHolder(frameLayout, ++this.tagCount);
            }

            @Override
            public void onBindViewHolder(@NonNull ProficienciesHolder holder, int position) {
                Proficiencies proficiencie = this.proficiencies.get(position);

                holder.setName(proficiencie.getName());
            }

            @Override
            public int getItemCount() {
                return this.proficiencies.size();
            }




            protected class ProficienciesHolder extends RecyclerView.ViewHolder{

                protected CheckBox checkBoxProficienciesName;
                protected TextView textViewDescription;

                public ProficienciesHolder(@NonNull View itemView, int tag) {
                    super(itemView);
                    itemView.setTag(tag);

                    this.checkBoxProficienciesName = itemView.findViewById(R.id.checkBoxFeatureName);
                    this.textViewDescription = itemView.findViewById(R.id.textViewFeatureDescription);
                }


                public void setName(String name){
                    this.checkBoxProficienciesName.setText(name);
                }


                public void setDescription(String description){
                    this.textViewDescription.setText(description);
                }
            }

        }

    }

}