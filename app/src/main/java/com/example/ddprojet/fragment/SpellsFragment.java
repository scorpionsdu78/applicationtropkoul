package com.example.ddprojet.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ddprojet.R;
import com.example.ddprojet.fonction.asyncFonc.SpellsGet;

import java.lang.ref.WeakReference;
import java.util.Vector;

import com.example.ddprojet.model.Spell;

public class SpellsFragment extends Fragment {

    View vue;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.spells_layout, container, false);

        vue = v;

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();

        RecyclerView rv = (RecyclerView) vue.findViewById(R.id.spellList);
        SpellAdapteur adaptor = new SpellAdapteur();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(vue.getContext());
        rv.setLayoutManager(layoutManager);

        rv.setAdapter(adaptor);


        SpellsGet get = new SpellsGet(new WeakReference<SpellAdapteur>(adaptor));
        get.execute("launch");
    }

    public class SpellHolder extends RecyclerView.ViewHolder{

        private TextView name;
        private TextView school;
        private TextView level;


        public SpellHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.SpellName);
            school = itemView.findViewById(R.id.SpellSchool);
            level = itemView.findViewById(R.id.SpellLevel);
        }

        public void setName(String name){
            this.name.setText(name);
        }

        public void setSchool(String school){
            this.school.setText(school);
        }

        public void setLevel(int level){
            this.level.setText(Integer.toString(level));
        }
    }

    public class SpellAdapteur extends RecyclerView.Adapter<SpellHolder> {

        Vector<Spell> spells;

        public SpellAdapteur(){spells = new Vector<>();}

        @NonNull
        @Override
        public SpellHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater lf = LayoutInflater.from(parent.getContext());
            View v = lf.inflate(R.layout.spell_holder_view, parent, false);

            SpellHolder spellHolder = new SpellHolder(v);

            return spellHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull SpellHolder holder, int position) {

            Spell spell = spells.elementAt(position);

            holder.setName(spell.getName());
            holder.setSchool(spell.getSchool());
            holder.setLevel(spell.getLevel());
        }

        @Override
        public int getItemCount() {
            return spells.size();
        }

        public void add(Spell s){
            spells.add(s);
            this.notifyItemChanged(this.spells.size()-1);
        }
    }
}
