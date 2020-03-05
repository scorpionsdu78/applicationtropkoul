package com.example.ddprojet.util.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ddprojet.R;

import java.util.Vector;


public class StringAdapter extends RecyclerView.Adapter<StringAdapter.StringHolder> {


    public class StringHolder extends RecyclerView.ViewHolder{
        private TextView textView;

        public StringHolder(@NonNull View itemView) {
            super(itemView);

            this.textView = itemView.findViewById(R.id.textView);
        }

        public void setText(String str){
            this.textView.setText(str);
        }
    }

    private Vector<String> items;

    public StringAdapter(){
        this.items = new Vector<>();
    }

    public int getItemCount(){
        return this.items.size();
    }

    public void addItem(String item){
        this.items.add(item);
        this.notifyItemInserted( this.items.size() - 1);
    }

    @NonNull
    @Override
    public StringHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater lf = LayoutInflater.from(parent.getContext());
        View view = lf.inflate(R.layout.util_list, parent, false);

        StringHolder sh = new StringHolder(view);


        return sh;
    }

    @Override
    public void onBindViewHolder(@NonNull StringHolder holder, int position) {
        String item = this.items.elementAt(position);

        holder.setText(item);
    }



}
