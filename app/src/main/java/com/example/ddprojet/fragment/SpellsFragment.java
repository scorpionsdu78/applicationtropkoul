package com.example.ddprojet.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ddprojet.R;
import com.example.ddprojet.activity.CharacterEditionActivity;
import com.example.ddprojet.fonction.asyncFonc.SpellListGetter;
import com.example.ddprojet.fonction.asyncFonc.SpellsGet;
import com.example.ddprojet.model.Spell;
import com.example.ddprojet.util.FragmentEnum;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.ref.WeakReference;
import java.util.Vector;
import java.util.concurrent.ExecutionException;

public class SpellsFragment extends Fragment {

    View view;
    CharacterEditionActivity parent_activity;
    JSONArray list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.spells_layout, container, false);

        this.parent_activity = (CharacterEditionActivity)this.getActivity();

        SpellListGetter slg = new SpellListGetter();
        slg.execute("get");
        try {
            list = slg.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Button buttonNext = this.view.findViewById(R.id.buttonNext);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SpellsFragment.this.parent_activity.ChangeFragment(FragmentEnum.Description);
            }
        });

        Button buttonBack = this.view.findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SpellsFragment.this.parent_activity.ChangeFragment(FragmentEnum.CharacSkills);
            }
        });

        return this.view;
    }

    @Override
    public void onResume() {
        super.onResume();

        RecyclerView rv = (RecyclerView) view.findViewById(R.id.spellList);
        SpellAdapteur adaptor = new SpellAdapteur();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        rv.setLayoutManager(layoutManager);

        rv.setAdapter(adaptor);

        CharacterEditionActivity activity = (CharacterEditionActivity)getActivity();


        if(activity.getCharacter().isHasSpellCasting()) {
            for(int i=0; i<list.length(); i++){
                try {
                    SpellsGet get = new SpellsGet(new WeakReference<SpellAdapteur>(adaptor),activity.getCharacter().getClass_());
                    get.execute("/api/spells/"+list.getJSONObject(i).getString("index"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }else {
            TextView titre = view.findViewById(R.id.SpellTitle);
            titre.setText("no spell for this classe");
        }
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
