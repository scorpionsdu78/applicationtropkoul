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
import com.example.ddprojet.model.Feature;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class FeaturesListAdapter extends RecyclerView.Adapter<FeaturesListAdapter.FeaturesListHolder>{

    protected List<Feature> features;
    protected List<FeaturesListHolder> holders;
    protected Callable<Boolean> updateValidation;
    protected Character character;

    public FeaturesListAdapter(Callable<Boolean> updateValidation, Character character){
        this.features = new ArrayList<>();
        this.holders = new ArrayList<>();

        this.updateValidation = updateValidation;
        this.character = character;
    }

    public boolean getValidation () {
        boolean validation = true;
        for (FeaturesListHolder holder : this.holders){
            if(!holder.getValidation()){
                validation = false;
                break;
            }
        }


        return validation;
    }

    public void addItem(Feature feature){
        this.features.add(feature);
        this.notifyItemInserted( this.features.size() - 1);
    }

    public void addItems(Feature ...features){
        for(Feature feature : features){
            this.addItem(feature);
        }
    }

    @NonNull
    @Override
    public FeaturesListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ConstraintLayout constraintLayout = (ConstraintLayout)LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.choose_list_layout, parent, false);

        FeaturesListHolder holder = new FeaturesListHolder(constraintLayout, this.updateValidation, this.character);
        this.holders.add(holder);


        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull FeaturesListHolder holder, int position) {
        holder.setChoose(((this.features.size() > 0 ) ? this.features.get(0).getSubName() != null : false) ? this.features.get(0).getName() : null);
        holder.setRecyclerViewFeatures(this.features);
    }

    @Override
    public int getItemCount() {
        return 1;
    }




    protected class FeaturesListHolder extends RecyclerView.ViewHolder{

        protected TextView textViewChoose;
        protected RecyclerView recyclerViewFeatures;
        protected Context context;
        protected Callable<Boolean> updateValidation;
        protected Character character;

        public FeaturesListHolder(@NonNull View itemView, Callable<Boolean> updateValidation, Character character) {
            super(itemView);

            this.context = itemView.getContext();
            this.textViewChoose = itemView.findViewById(R.id.textViewChoose);
            this.recyclerViewFeatures = itemView.findViewById(R.id.recyclerViewFeatures);
            this.updateValidation = updateValidation;
            this.character = character;
        }

        public boolean getValidation() {
            return ((FeaturesAdapter)this.recyclerViewFeatures.getAdapter()).getValidation();
        }


        public void setChoose(String name){
            this.textViewChoose.setText((name != null) ? name : "1");
        }


        public void setRecyclerViewFeatures(List<Feature> features){
            RecyclerView.LayoutManager manager = new LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false);
            FeaturesAdapter adapter = new FeaturesAdapter(this.updateValidation, this.character);

            this.recyclerViewFeatures.setLayoutManager(manager);
            this.recyclerViewFeatures.setAdapter(adapter);
            adapter.addItems(features.toArray(new Feature[0]));

            /*int count = adapter.getItemCount();
            Log.i("DulcheE", "" + count);

            for(int i = 0; i < count; i++){
                Log.i("DulcheE", "" + i);
                View v = manager.findViewByPosition(i);

                ((CheckBox)v.findViewById(R.id.checkBoxFeatureName)).setChecked(true);
            }*/
        }





        protected class FeaturesAdapter extends RecyclerView.Adapter<FeaturesAdapter.FeaturesHolder>{

            protected List<CheckBox> checkBoxes;
            protected List<CheckBox> checkBoxesChecked;

            protected List<Feature> features;
            protected Callable<Boolean> updateValidation;
            protected Character character;

            public FeaturesAdapter(Callable<Boolean> updateValidation, Character character){
                this.checkBoxes = new ArrayList<>();
                this.checkBoxesChecked = new ArrayList<>();

                this.features = new ArrayList<>();
                this.updateValidation = updateValidation;
                this.character = character;
            }

            public boolean getValidation(){
                return (this.checkBoxesChecked.size() == 1);
            }

            public void addItem(Feature feature){
                this.features.add(feature);
                this.notifyItemInserted( this.features.size() - 1);
            }

            public void addItems(Feature ...features){
                for(Feature feature : features)
                    this.addItem(feature);
            }

            @NonNull
            @Override
            public FeaturesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                FrameLayout frameLayout = (FrameLayout) LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.choose_item_layout, parent, false);

                return new FeaturesHolder(frameLayout, this.checkBoxes);
            }

            @Override
            public void onBindViewHolder(@NonNull FeaturesHolder holder, int position) {
                Feature feature = this.features.get(position);

                holder.setName((feature.getSubName() != null) ? feature.getSubName() : feature.getName());
                holder.setDescription(feature.getDesc());
                holder.setCheckBoxOnClick(feature, this.checkBoxesChecked, 1, this.updateValidation, this.character);
            }

            @Override
            public int getItemCount() {
                return this.features.size();
            }




            protected class FeaturesHolder extends RecyclerView.ViewHolder{

                protected List<CheckBox> checkBoxes;

                protected CheckBox checkBoxFeature;
                protected TextView textViewDescription;

                public FeaturesHolder(@NonNull View itemView, List<CheckBox> checkBoxes) {
                    super(itemView);

                    this.checkBoxFeature = (CheckBox) itemView.findViewById(R.id.checkBoxFeatureName);
                    this.textViewDescription = (TextView)itemView.findViewById(R.id.textViewFeatureDescription);

                    this.checkBoxes = checkBoxes;
                    this.checkBoxes.add(this.checkBoxFeature);
                }


                public void setName(String name){
                    this.checkBoxFeature.setText(name);
                }


                public void setDescription(String description){
                    this.textViewDescription.setText(description);
                }

                public void setCheckBoxOnClick(final Feature feature, final List<CheckBox> checkBoxesChecked, final int count, final Callable<Boolean> updateValidation, final Character character){
                    this.checkBoxFeature.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (FeaturesHolder.this.checkBoxFeature.isChecked()) {
                                if (checkBoxesChecked.size() < count) {
                                    character.addFeature(feature);
                                    checkBoxesChecked.add(FeaturesHolder.this.checkBoxFeature);

                                    if (checkBoxesChecked.size() == count)
                                        for(CheckBox checkBox : FeaturesHolder.this.checkBoxes)
                                            if (!checkBox.isChecked())
                                                checkBox.setClickable(false);

                                    try {
                                        updateValidation.call();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }else {
                                if (checkBoxesChecked.contains(FeaturesHolder.this.checkBoxFeature)) {
                                    character.removeFeature(feature);
                                    checkBoxesChecked.remove(FeaturesHolder.this.checkBoxFeature);

                                    try {
                                        updateValidation.call();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                                for(CheckBox checkBox : FeaturesHolder.this.checkBoxes)
                                    checkBox.setClickable(true);
                            }
                        }
                    });
                }
            }

        }

    }

}