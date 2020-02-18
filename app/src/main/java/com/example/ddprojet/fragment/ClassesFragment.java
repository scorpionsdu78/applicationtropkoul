package com.example.ddprojet.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ddprojet.R;
import com.example.ddprojet.fonction.asyncFonc.ClassesGet;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class ClassesFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.class_layout, container, false);

        RecyclerView rv = (v).findViewById(R.id.ClassRecyclerView);
        ClassDescriptionAdaptator adaptator = new ClassDescriptionAdaptator();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),3, LinearLayoutManager.VERTICAL,false);

        rv.setLayoutManager(gridLayoutManager);
        rv.setAdapter(adaptator);

        ClassesGet getClass = new ClassesGet(adaptator);
        getClass.execute("test");

        return v;
    }

    public class classeHolder extends RecyclerView.ViewHolder{

        protected TextView name;
        protected ImageView photo;
        protected FrameLayout fr;

        public classeHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.textViewClass);
            photo = (ImageView) itemView.findViewById(R.id.imageViewClass);
            fr = (FrameLayout) itemView.findViewById(R.id.frameLayoutClass);
            fr.setPadding(5,5,5,5);
        }

        public void setName(String _name) {
            this.name.setText(_name);
        }

        public void setPhoto(@DrawableRes int _photo){
            photo.setImageResource(_photo);
        }

        public void setOnclick(){
            fr.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                }
            });
        }

    }

    public class ClassDescriptionAdaptator extends RecyclerView.Adapter<classeHolder>{

        Vector<String> Classes;
        Map<String, Integer> images;

        public ClassDescriptionAdaptator() {
            Classes = new Vector<>();
            images = new HashMap<>();
            images.put("Barbarian", new Integer(R.drawable.class_barbarian));
            images.put("Bard", new Integer(R.drawable.class_bard));
            images.put("Cleric", new Integer(R.drawable.class_cleric));
            images.put("Druid", new Integer(R.drawable.class_druid));
            images.put("Fighter", new Integer(R.drawable.class_fighter));
            images.put("Monk", new Integer(R.drawable.class_monk));
            images.put("Paladin",new Integer(R.drawable.class_paladin));
            images.put("Ranger", new Integer(R.drawable.class_ranger));
            images.put("Rogue", new Integer(R.drawable.class_rogue));
            images.put("Sorcerer", new Integer(R.drawable.class_sorcerer));
            images.put("Warlock", new Integer(R.drawable.class_warlock));
            images.put("Wizard", new Integer(R.drawable.class_wizard));
        }

        @NonNull
        @Override
        public classeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater lf = LayoutInflater.from(parent.getContext());
            View v = lf.inflate(R.layout.character_class_view, parent, false);

            classeHolder cl = new classeHolder(v);

            return cl;
        }

        @Override
        public void onBindViewHolder(@NonNull classeHolder holder, int position) {
            String classe = Classes.elementAt(position);

            holder.setName(classe);
            holder.setPhoto(images.get(classe).intValue());
        }

        @Override
        public int getItemCount() {
            return Classes.size();
        }

        public void add(String element){
            Classes.add(element);
            this.notifyItemChanged(Classes.size()-1);
        }
    }



}
