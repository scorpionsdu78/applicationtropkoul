package com.example.ddprojet.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ddprojet.R;

import java.util.ArrayList;
import java.util.List;

public class CapaDonsFragment extends Fragment {

    public List<FeaturesList> featuresLists;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.capa_dons_layout, container, false);

        this.featuresLists = new ArrayList<FeaturesList>();

        FeaturesList fl = new FeaturesList();
        fl.features = new ArrayList<Feature>();

        fl.choose = 2;

        Feature f = new Feature();
        f.name = "Feature 1";
        f.description = "Description of feature 1";
        fl.features.add(f);

        f = new Feature();
        f.name = "Feature 2";
        f.description = "Description of feature 2";
        fl.features.add(f);

        f = new Feature();
        f.name = "Feature 3";
        f.description = "Description of feature 3\n\nDAB !";
        fl.features.add(f);

        this.featuresLists.add(fl);

        fl = new FeaturesList();
        fl.features = new ArrayList<Feature>();

        fl.choose = 1;

        f = new Feature();
        f.name = "Feature 2-1";
        f.description = "Description of feature 2-1";
        fl.features.add(f);

        f = new Feature();
        f.name = "Feature 2-2";
        f.description = "Description of feature 2-2";
        fl.features.add(f);

        this.featuresLists.add(fl);

        RecyclerView recyclerViewFeaturesListList = (RecyclerView)v.findViewById(R.id.recyclerViewFeaturesLists);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(this.getActivity(), LinearLayoutManager.VERTICAL, false);
        FeaturesListAdapter adapter = new FeaturesListAdapter(this.featuresLists);

        recyclerViewFeaturesListList.setLayoutManager(manager);
        recyclerViewFeaturesListList.setAdapter(adapter);



        return v;
    }

    public class Feature{
        public String name;
        public String description;
    }
    public class FeaturesList{
        public int choose;
        public List<Feature> features;
    }




    protected class FeaturesListHolder extends RecyclerView.ViewHolder{

        protected TextView textViewChoose;
        protected RecyclerView recyclerViewFeatures;

        public FeaturesListHolder(@NonNull View itemView) {
            super(itemView);
            this.textViewChoose = (TextView)itemView.findViewById(R.id.textViewChoose);
            this.recyclerViewFeatures = (RecyclerView)itemView.findViewById(R.id.recyclerViewFeatures);
        }


        public void setChoose(int choose){
            this.textViewChoose.setText(Integer.toString(choose));
        }


        public void setRecyclerViewFeatures(List<Feature> features){
            RecyclerView.LayoutManager manager = new LinearLayoutManager(CapaDonsFragment.this.getActivity(), LinearLayoutManager.VERTICAL, false);
            FeaturesAdapter adapter = new FeaturesAdapter(features);

            this.recyclerViewFeatures.setLayoutManager(manager);
            this.recyclerViewFeatures.setAdapter(adapter);

            /*int count = adapter.getItemCount();
            Log.i("DulcheE", "" + count);

            for(int i = 0; i < count; i++){
                Log.i("DulcheE", "" + i);
                View v = manager.findViewByPosition(i);

                ((CheckBox)v.findViewById(R.id.checkBoxFeatureName)).setChecked(true);
            }*/
        }
    }


    protected class FeaturesListAdapter extends RecyclerView.Adapter<FeaturesListHolder>{

        protected List<FeaturesList> featuresLists;

        public FeaturesListAdapter(List<FeaturesList> featuresLists){
            this.featuresLists = featuresLists;
        }

        @NonNull
        @Override
        public FeaturesListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            ConstraintLayout constraintLayout = (ConstraintLayout)LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.features_list_layout, parent, false);

            return new FeaturesListHolder(constraintLayout);
        }

        @Override
        public void onBindViewHolder(@NonNull FeaturesListHolder holder, int position) {
            FeaturesList featuresList = this.featuresLists.get(position);

            holder.setChoose(featuresList.choose);
            holder.setRecyclerViewFeatures(featuresList.features);
        }

        @Override
        public int getItemCount() {
            return this.featuresLists.size();
        }

    }




    protected class FeaturesHolder extends RecyclerView.ViewHolder{

        protected CheckBox checkBoxFeatureName;
        protected TextView textViewDescription;

        public FeaturesHolder(@NonNull View itemView, int tag) {
            super(itemView);
            itemView.setTag(tag);

            this.checkBoxFeatureName = (CheckBox) itemView.findViewById(R.id.checkBoxFeatureName);
            this.textViewDescription = (TextView)itemView.findViewById(R.id.textViewFeatureDescription);
        }


        public void setName(String name){
            this.checkBoxFeatureName.setText(name);
        }


        public void setDescription(String description){
            this.textViewDescription.setText(description);
        }
    }


    protected class FeaturesAdapter extends RecyclerView.Adapter<FeaturesHolder>{

        protected int tagCount = 0;

        protected List<Feature> features;

        public FeaturesAdapter(List<Feature> features){
            this.features = features;
        }

        @NonNull
        @Override
        public FeaturesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            FrameLayout frameLayout = (FrameLayout) LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.feature_item_layout, parent, false);

            return new FeaturesHolder(frameLayout, ++this.tagCount);
        }

        @Override
        public void onBindViewHolder(@NonNull FeaturesHolder holder, int position) {
            Feature feature = this.features.get(position);

            holder.setName(feature.name);
            holder.setDescription(feature.description);
        }

        @Override
        public int getItemCount() {
            return this.features.size();
        }
    }
}
