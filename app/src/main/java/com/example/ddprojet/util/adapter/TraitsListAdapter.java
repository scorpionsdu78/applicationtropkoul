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
import com.example.ddprojet.model.Trait;
import com.example.ddprojet.model.TraitsList;

import java.util.ArrayList;
import java.util.List;

public class TraitsListAdapter extends RecyclerView.Adapter<TraitsListAdapter.TraitsListHolder>{

    protected TraitsList traitsList;

    public TraitsListAdapter(){
    }

    public void setTraitsList(TraitsList traitsList){
        this.traitsList = traitsList;
    }

    @NonNull
    @Override
    public TraitsListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ConstraintLayout constraintLayout = (ConstraintLayout)LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.features_list_layout, parent, false);

        return new TraitsListHolder(constraintLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull TraitsListHolder holder, int position) {
        holder.setChoose();
        holder.setRecyclerViewTraits(traitsList);
    }

    @Override
    public int getItemCount() {
        return 1;
    }




    protected class TraitsListHolder extends RecyclerView.ViewHolder{

        protected TextView textViewChoose;
        protected RecyclerView recyclerViewTraits;
        protected Context context;

        public TraitsListHolder(@NonNull View itemView) {
            super(itemView);
            this.context = itemView.getContext();
            this.textViewChoose = itemView.findViewById(R.id.textViewChoose);
            this.recyclerViewTraits = itemView.findViewById(R.id.recyclerViewFeatures);
        }


        public void setChoose(){
            this.textViewChoose.setText("1");
        }


        public void setRecyclerViewTraits(TraitsList traits){
            RecyclerView.LayoutManager manager = new LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false);
            TraitsAdapter adapter = new TraitsAdapter();

            this.recyclerViewTraits.setLayoutManager(manager);
            this.recyclerViewTraits.setAdapter(adapter);
            adapter.addItems(traits.getTraits().toArray(new Trait[0]));

            /*int count = adapter.getItemCount();
            Log.i("DulcheE", "" + count);

            for(int i = 0; i < count; i++){
                Log.i("DulcheE", "" + i);
                View v = manager.findViewByPosition(i);

                ((CheckBox)v.findViewById(R.id.checkBoxTraitName)).setChecked(true);
            }*/
        }





        protected class TraitsAdapter extends RecyclerView.Adapter<TraitsAdapter.TraitsHolder>{

            protected int tagCount = 0;

            protected List<Trait> traits;

            public TraitsAdapter(){
                this.traits = new ArrayList<>();
            }

            public void addItem(Trait trait){
                this.traits.add(trait);
            }

            public void addItems(Trait ...traits){
                for(Trait trait : traits)
                    this.addItem(trait);
            }

            @NonNull
            @Override
            public TraitsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                FrameLayout frameLayout = (FrameLayout) LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.feature_item_layout, parent, false);

                return new TraitsHolder(frameLayout, ++this.tagCount);
            }

            @Override
            public void onBindViewHolder(@NonNull TraitsHolder holder, int position) {
                Trait trait = this.traits.get(position);

                holder.setName(trait.getName());
                holder.setDescription(trait.getDescription());
            }

            @Override
            public int getItemCount() {
                return this.traits.size();
            }




            protected class TraitsHolder extends RecyclerView.ViewHolder{

                protected CheckBox checkBoxTraitName;
                protected TextView textViewDescription;

                public TraitsHolder(@NonNull View itemView, int tag) {
                    super(itemView);
                    itemView.setTag(tag);

                    this.checkBoxTraitName = itemView.findViewById(R.id.checkBoxFeatureName);
                    this.textViewDescription = itemView.findViewById(R.id.textViewFeatureDescription);
                }


                public void setName(String name){
                    this.checkBoxTraitName.setText(name);
                }


                public void setDescription(String description){
                    this.textViewDescription.setText(description);
                }
            }

        }

    }

}