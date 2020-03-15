package com.example.ddprojet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ddprojet.R;
import com.example.ddprojet.model.Character;
import com.example.ddprojet.model.Feature;
import com.example.ddprojet.persistance.FileJson;
import com.example.ddprojet.util.AvatarLoader;
import com.example.ddprojet.util.adapter.FeatureAdapter;
import com.example.ddprojet.util.adapter.StringAdapter;

public class CharacterDisplayActivity extends AppCompatActivity {

    private Character character;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_display);

        Bundle extras = getIntent().getExtras();
        System.out.println(extras.containsKey("value"));
        character = (Character) extras.getSerializable("value");
        
        TextView vue = findViewById(R.id.name);
        vue.setText(character.getName());

        ((ImageView)findViewById(R.id.imageViewAvatar)).setImageBitmap(new AvatarLoader(this.character.getAvatarPath()).load(this.getResources()));

        vue = findViewById(R.id.race);
        vue.setText(character.getRace());

        vue = findViewById(R.id.classe);
        vue.setText(character.getClass_());

        vue = findViewById((R.id.level));
        vue.setText(Integer.toString(character.getLevel()));

        vue = findViewById(R.id.alignement);
        vue.setText(character.getAlignment().toString());

        vue = findViewById(R.id.characSTR);
        vue.setText("STR: " + character.getCharacteristic().get("STR").toString());

        vue = findViewById(R.id.characCON);
        vue.setText("CON: " + character.getCharacteristic().get("CON").toString());

        vue = findViewById(R.id.characINT);
        vue.setText("INT: " + character.getCharacteristic().get("INT").toString());

        vue = findViewById(R.id.characDEX);
        vue.setText("DEX: " + character.getCharacteristic().get("DEX").toString());

        vue = findViewById(R.id.characWIS);
        vue.setText("WIS: " + character.getCharacteristic().get("WIS").toString());

        vue = findViewById(R.id.characCHA);
        vue.setText("CHA: " + character.getCharacteristic().get("CHA").toString());

        RecyclerView rv = findViewById(R.id.skills);
        rv.setLayoutManager(new GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false));
        StringAdapter adapter = new StringAdapter();
        for (String s: character.skillsHasStrings()) {
            adapter.addItem(s);
        }
        rv.setAdapter(adapter);

        rv = findViewById(R.id.proficiencies);
        rv.setLayoutManager(new GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false));
        adapter = new StringAdapter();
        for (String s: character.getProficiencies()) {
            adapter.addItem(s);
        }
        rv.setAdapter(adapter);

        rv = findViewById(R.id.trait);
        rv.setLayoutManager(new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false));
        adapter = new StringAdapter();
        for (String s: character.getTraits()) {
            adapter.addItem(s);
        }
        rv.setAdapter(adapter);

        rv = findViewById(R.id.languages);
        rv.setLayoutManager(new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false));
        adapter = new StringAdapter();
        for (String s: character.getLanguages()) {
            adapter.addItem(s);
        }
        rv.setAdapter(adapter);

        rv = findViewById(R.id.features);
        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        // TODO create new adapter
        /*FeaturesListAdapter adapter2 = new FeaturesListAdapter(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return null;
            }
        }, character);
        adapter2.addItems(character.getFeatures().toArray(new Feature[0]));
        rv.setAdapter(adapter2);*/

        if(character.getFeatures() != null) {
            FeatureAdapter adapter1 = new FeatureAdapter();
            adapter1.addItems(character.getFeatures().toArray(new Feature[0]));
            rv.setAdapter(adapter1);
        }

        vue = findViewById(R.id.background);
        if(character.getBackground().isEmpty()){
            vue.setText("[NONE]");
        }else {
            vue.setText(character.getBackground());
        }


        vue = findViewById(R.id.personality);
        if(character.getPersonality_traits().isEmpty()){
            vue.setText("[NONE]");
        }else {
            vue.setText(character.getPersonality_traits());
        }

    }

    public void retour(View v){
        Intent callActivity = new Intent(getApplicationContext(), CharacterSelectionActivity.class);
        startActivity(callActivity);
    }

    public void delete(View v){
        try {
            FileJson fj = new FileJson(this.getApplicationContext(),character.getName()+".json");
            fj.delete();
            Intent callActivity = new Intent(getApplicationContext(), CharacterSelectionActivity.class);
            startActivity(callActivity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
