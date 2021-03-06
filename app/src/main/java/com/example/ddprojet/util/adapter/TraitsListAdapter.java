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
import com.example.ddprojet.model.Character;
import com.example.ddprojet.model.Trait;
import com.example.ddprojet.model.TraitsList;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class TraitsListAdapter extends RecyclerView.Adapter<TraitsListAdapter.TraitsListHolder>{

    protected TraitsList traitsList;
    protected List<TraitsListHolder> holders;
    protected Callable<Boolean> updateValidation;
    protected Character character;

    public TraitsListAdapter(Callable<Boolean> updateValidation, Character character){
        this.holders = new ArrayList<>();

        this.updateValidation = updateValidation;
        this.character = character;
    }

    public boolean getValidation() {
        boolean validation = true;
        for (TraitsListHolder holder : this.holders){
            if(!holder.getValidation()){
                validation = false;
                break;
            }
        }


        return validation;
    }


    public void setTraitsList(TraitsList traitsList){
        this.traitsList = traitsList;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TraitsListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ConstraintLayout constraintLayout = (ConstraintLayout)LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.choose_list_layout, parent, false);

        TraitsListHolder holder = new TraitsListHolder(constraintLayout, this.updateValidation, this.character);
        this.holders.add(holder);


        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull TraitsListHolder holder, int position) {
        holder.setChoose((this.traitsList.getTraits().get(0).getSubName() != null) ? this.traitsList.getTraits().get(0).getName() : null);
        holder.setRecyclerViewTraits(this.traitsList);
    }

    @Override
    public int getItemCount() {
        return 1;
    }




    protected class TraitsListHolder extends RecyclerView.ViewHolder{

        protected TextView textViewChoose;
        protected RecyclerView recyclerViewTraits;
        protected Context context;
        protected Callable<Boolean> updateValidation;
        protected Character character;

        public TraitsListHolder(@NonNull View itemView, Callable<Boolean> updateValidation, Character character) {
            super(itemView);
            this.context = itemView.getContext();
            this.textViewChoose = itemView.findViewById(R.id.textViewChoose);
            this.recyclerViewTraits = itemView.findViewById(R.id.recyclerViewFeatures);
            this.updateValidation = updateValidation;
            this.character = character;
        }

        public boolean getValidation(){
            return ((TraitsAdapter)this.recyclerViewTraits.getAdapter()).getValidation();
        }


        public void setChoose(String name){
            this.textViewChoose.setText((name != null) ? name : "1");
        }


        public void setRecyclerViewTraits(TraitsList traits){
            RecyclerView.LayoutManager manager = new LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false);
            TraitsAdapter adapter = new TraitsAdapter(this.updateValidation, this.character);

            this.recyclerViewTraits.setLayoutManager(manager);
            this.recyclerViewTraits.setAdapter(adapter);
            adapter.addItems(traits.getTraits().toArray(new Trait[0]));
        }





        protected class TraitsAdapter extends RecyclerView.Adapter<TraitsAdapter.TraitsHolder>{

            protected List<CheckBox> checkBoxes;
            protected List<CheckBox> checkBoxesChecked;

            protected List<Trait> traits;
            protected Callable<Boolean> updateValidation;
            protected Character character;

            public TraitsAdapter(Callable<Boolean> updateValidation, Character character){
                this.checkBoxes = new ArrayList<>();
                this.checkBoxesChecked = new ArrayList<>();

                this.traits = new ArrayList<>();
                this.updateValidation = updateValidation;
                this.character = character;
            }

            public boolean getValidation(){
                return (this.checkBoxesChecked.size() == 1);
            }

            public void addItem(Trait trait){
                this.traits.add(trait);
                this.notifyItemInserted( this.traits.size() - 1);
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
                        .inflate(R.layout.choose_item_layout, parent, false);

                return new TraitsHolder(frameLayout, this.checkBoxes);
            }

            @Override
            public void onBindViewHolder(@NonNull TraitsHolder holder, int position) {
                Trait trait = this.traits.get(position);

                holder.setName((trait.getSubName() != null) ? trait.getSubName() : trait.getName());
                holder.setDescription(trait.getDescription());
                holder.setCheckBoxOnClick(trait, this.checkBoxesChecked, 1, this.updateValidation, this.character);
            }

            @Override
            public int getItemCount() {
                return this.traits.size();
            }




            protected class TraitsHolder extends RecyclerView.ViewHolder{

                protected List<CheckBox> checkBoxes;

                protected CheckBox checkBoxTrait;
                protected TextView textViewDescription;

                public TraitsHolder(@NonNull View itemView, List<CheckBox> checkBoxes) {
                    super(itemView);

                    this.checkBoxTrait = itemView.findViewById(R.id.checkBoxFeatureName);
                    this.textViewDescription = itemView.findViewById(R.id.textViewFeatureDescription);

                    this.checkBoxes = checkBoxes;
                    this.checkBoxes.add(this.checkBoxTrait);
                }


                public void setName(String name){
                    this.checkBoxTrait.setText(name);
                }


                public void setDescription(String description){
                    this.textViewDescription.setText(description);
                }

                public void setCheckBoxOnClick(final Trait trait, final List<CheckBox> checkBoxesChecked, final int count, final Callable<Boolean> updateValidation, final Character character){
                    this.checkBoxTrait.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (TraitsHolder.this.checkBoxTrait.isChecked()) {
                                if (checkBoxesChecked.size() < count) {
                                    character.addTrait(trait);
                                    checkBoxesChecked.add(TraitsHolder.this.checkBoxTrait);

                                    if (checkBoxesChecked.size() == count)
                                        for(CheckBox checkBox : TraitsHolder.this.checkBoxes)
                                            if (!checkBox.isChecked())
                                                checkBox.setClickable(false);

                                    try {
                                        updateValidation.call();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }else {
                                if (checkBoxesChecked.contains(TraitsHolder.this.checkBoxTrait)) {
                                    character.removeTrait(trait);
                                    checkBoxesChecked.remove(TraitsHolder.this.checkBoxTrait);

                                    try {
                                        updateValidation.call();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                                for(CheckBox checkBox : TraitsHolder.this.checkBoxes)
                                    checkBox.setClickable(true);
                            }
                        }
                    });
                }
            }

        }

    }

}