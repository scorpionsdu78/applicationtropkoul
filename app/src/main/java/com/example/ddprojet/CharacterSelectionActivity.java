package com.example.ddprojet;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ddprojet.CustomComponent.CharacterSelectionView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import model.Alignment;
import model.CharacterDescription;

public class CharacterSelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_selection);

        RecyclerView rv = findViewById(R.id.recyclerViewCharacterView);
        CharacterDescriptionViewAdapter characterDescriptionViewAdapter = new CharacterDescriptionViewAdapter();

        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rv.setAdapter(characterDescriptionViewAdapter);

        characterDescriptionViewAdapter.add(new CharacterDescription("Eozen Thelir Daragon", "Elf des sables", "Paladin", new Alignment(Alignment.LawfulChaoticAxis.LAWFUL, Alignment.GoodEvilAxis.GOOD), 12, R.drawable.avatar_barbarian));
        characterDescriptionViewAdapter.add(new CharacterDescription("Edgar", "Margull", "Necromancien", new Alignment(Alignment.LawfulChaoticAxis.CHAOTIC, Alignment.GoodEvilAxis.NEUTRAL), 9000, R.drawable.avatar_dark_wizard));
    }


    public void ChangeActivity(View V){
        Intent callActivity = new Intent(getApplicationContext(), CharacterEditionActivity.class);
        startActivity(callActivity);
    }

    protected class CharacterDescriptionHolder extends RecyclerView.ViewHolder{
        protected TextView name;
        protected TextView race;
        protected TextView class_;
        protected TextView alignment;
        protected TextView level;
        protected ImageView avatar;


        public CharacterDescriptionHolder(@NonNull View itemView) {
            super(itemView);

            this.name = itemView.findViewById(R.id.textViewName);
            this.race = itemView.findViewById(R.id.textViewRace);
            this.class_ = itemView.findViewById(R.id.textViewClass);
            this.alignment = itemView.findViewById(R.id.textViewAlignement);
            this.level = itemView.findViewById(R.id.textViewLevel);
            this.avatar = itemView.findViewById(R.id.imageViewAvatar);
        }

        public void setName(String name){
            this.name.setText(name);
        }

        public void setRace(String race){
            this.race.setText(race);
        }

        public void setClass(String class_){
            this.class_.setText(class_);
        }

        public void setAlignment(Alignment alignment){
            this.alignment.setText(alignment.toString());
        }

        public void setLevel(int level){
            this.level.setText(Integer.toString(level));
        }

        public void setAvatar(@DrawableRes int avatar){
            this.avatar.setImageResource(avatar);
        }

    }


    protected class CharacterDescriptionViewAdapter extends RecyclerView.Adapter<CharacterDescriptionHolder>{

        Vector<CharacterDescription> characters;

        CharacterDescriptionViewAdapter(){
            this.characters = new Vector<>();
        }

        @Override
        public int getItemCount() {
            return this.characters.size();
        }

        @NonNull
        @Override
        public CharacterDescriptionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater lf = LayoutInflater.from(parent.getContext());
            View v = lf.inflate(R.layout.character_selection_view, parent, false);

            CharacterDescriptionHolder cdh = new CharacterDescriptionHolder(v);

            return cdh;
        }

        @Override
        public void onBindViewHolder(@NonNull CharacterDescriptionHolder holder, int position) {
            CharacterDescription characterDescription = this.characters.elementAt(position);

            holder.setName(characterDescription.getName());
            holder.setRace(characterDescription.getRace());
            holder.setClass(characterDescription.getClass_());
            holder.setAlignment(characterDescription.getAlignment());
            holder.setLevel(characterDescription.getLevel());
            holder.setAvatar(characterDescription.getAvatar());

        }

        public void add(CharacterDescription characterDescription){
            this.characters.add(characterDescription);
            this.notifyItemChanged(this.characters.size() - 1);
        }
    }

}
