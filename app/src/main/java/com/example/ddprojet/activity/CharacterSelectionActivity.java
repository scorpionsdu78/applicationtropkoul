package com.example.ddprojet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ddprojet.R;
import com.example.ddprojet.model.Alignment;
import com.example.ddprojet.model.Character;
import com.example.ddprojet.model.CharacterDescription;
import com.example.ddprojet.persistance.FileJson;

import java.io.File;
import java.util.Vector;

public class CharacterSelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_selection);

        RecyclerView rv = findViewById(R.id.recyclerViewCharacterView);
        CharacterDescriptionViewAdapter characterDescriptionViewAdapter = new CharacterDescriptionViewAdapter();

        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rv.setAdapter(characterDescriptionViewAdapter);

        String path = "/data/user/0/com.example.ddprojet/files/";


        File directory = new File(path);
        File[] files = directory.listFiles();
        if(files != null){
            for (int i = 0; i < files.length; i++)
            {
                FileJson fj = new FileJson(this.getApplicationContext(),files[i].getName());

                Character character =  fj.getCharacter();
                if(character != null){
                    characterDescriptionViewAdapter.add(new CharacterDescription(character,R.drawable.avatar_barbarian));
                }
                //characterDescriptionViewAdapter.add(new CharacterDescription(character.getName(), character.getRace(), character.getClass_(),character.getAlignment(),character.getLevel(),R.drawable.avatar_barbarian));


            }
        }

/*        characterDescriptionViewAdapter.add(new CharacterDescription("Eozen Thelir Daragon", "Elf des sables", "Paladin", new Alignment(Alignment.LawfulChaoticAxis.LAWFUL, Alignment.GoodEvilAxis.GOOD), 12, R.drawable.avatar_barbarian));
        characterDescriptionViewAdapter.add(new CharacterDescription("Edgar", "Margull", "Necromancien", new Alignment(Alignment.LawfulChaoticAxis.CHAOTIC, Alignment.GoodEvilAxis.NEUTRAL), 9000, R.drawable.avatar_dark_wizard));
        characterDescriptionViewAdapter.add(new CharacterDescription("Eude", "Elf", "Mandiant", new Alignment(Alignment.LawfulChaoticAxis.NEUTRAL, Alignment.GoodEvilAxis.NEUTRAL), 0, R.drawable.race_half_elf));
        characterDescriptionViewAdapter.add(new CharacterDescription("Ielle", "Non raciste", "Social Justice Warrior", new Alignment(Alignment.LawfulChaoticAxis.CHAOTIC, Alignment.GoodEvilAxis.NEUTRAL), 666, R.drawable.race_dwarf));
*/
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
        protected View itemView;


        public CharacterDescriptionHolder(@NonNull View itemView) {
            super(itemView);

            this.itemView = itemView;

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

        public void setOnclick(final Character character){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    seeCharacter(view, character);
                }
            });
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
            holder.setOnclick(characterDescription.getCharacter());

        }

        public void add(CharacterDescription characterDescription){
            this.characters.add(characterDescription);
            this.notifyItemChanged(this.characters.size() - 1);
        }
    }

    public void seeCharacter(View v, Character character){




        Intent callActivity = new Intent(getApplicationContext(), CharacterDisplayActivity.class);
        callActivity.putExtra("value", character);
        startActivity(callActivity);
    }

}
