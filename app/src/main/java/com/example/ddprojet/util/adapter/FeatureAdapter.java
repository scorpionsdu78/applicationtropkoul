package com.example.ddprojet.util.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ddprojet.R;
import com.example.ddprojet.model.Feature;

import java.util.List;
import java.util.Vector;


public class FeatureAdapter extends RecyclerView.Adapter<FeatureAdapter.FeatureHolder> {

    private Vector<Feature> items;

    public FeatureAdapter(){
        this.items = new Vector<>();
    }

    public int getItemCount(){
        return this.items.size();
    }

    public void addItem(Feature item){
        this.items.add(item);
        this.notifyItemInserted( this.items.size() - 1);
    }

    public void addItems(Feature... features){
        for(Feature feature : features){
            addItem(feature);
        }
    }

    public void addItems(List<Feature> features){
        for(Feature feature : features){
            addItem(feature);
        }
    }

    @NonNull
    @Override
    public FeatureHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater lf = LayoutInflater.from(parent.getContext());
        View view = lf.inflate(R.layout.feature_item_layout, parent, false);

        FeatureHolder sh = new FeatureHolder(view);


        return sh;
    }

    @Override
    public void onBindViewHolder(@NonNull FeatureHolder holder, int position) {
        Feature item = this.items.elementAt(position);

        holder.setName((item.getSubName() != null) ? item.getSubName() : item.getName());
        holder.setDescription(item.getDesc());
    }




    public class FeatureHolder extends RecyclerView.ViewHolder{
        private TextView textViewName;
        private TextView textViewDescription;

        public FeatureHolder(@NonNull View itemView) {
            super(itemView);

            this.textViewName = itemView.findViewById(R.id.textViewFeatureName);
            this.textViewDescription = itemView.findViewById(R.id.textViewFeatureDescription);
        }

        public void setName(String name){
            this.textViewName.setText(name);
        }
        public void setDescription(String description){
            this.textViewDescription.setText(description);
        }
    }

}
