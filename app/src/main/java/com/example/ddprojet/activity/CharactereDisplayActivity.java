package com.example.ddprojet.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ddprojet.R;
import com.example.ddprojet.model.Character;
import com.example.ddprojet.model.Feature;
import com.example.ddprojet.util.adapter.FeaturesListAdapter;
import com.example.ddprojet.util.adapter.StringAdapter;

import java.util.concurrent.Callable;

public class CharactereDisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charactere_display);

        Bundle extras = getIntent().getExtras();
        System.out.println(extras.containsKey("value"));
        Character character = (Character) extras.getSerializable("value");

        Log.d("test transfert", character.toString());

        TextView vue = findViewById(R.id.name);
        vue.setText(character.getName());

        vue = findViewById(R.id.race);
        vue.setText(character.getRace());

        vue = findViewById(R.id.classe);
        vue.setText(character.getClass_());

        vue = findViewById((R.id.level));
        vue.setText(Integer.toString(character.getLevel()));

        vue = findViewById(R.id.alignement);
        vue.setText(character.getAlignment().toString());

        vue = findViewById(R.id.charac1);
        Integer tmp = character.getCharacteristic().get("INT");
        vue.setText("INT: " + tmp.toString());

        vue = findViewById(R.id.charac2);
        vue.setText("CON: " + character.getCharacteristic().get("CON").toString());

        vue = findViewById(R.id.charac3);
        vue.setText("CHA: " + character.getCharacteristic().get("CHA").toString());

        vue = findViewById(R.id.charac4);
        vue.setText("WIS: " + character.getCharacteristic().get("WIS").toString());

        vue = findViewById(R.id.charac5);
        vue.setText("STR: " + character.getCharacteristic().get("STR").toString());

        vue = findViewById(R.id.charac6);
        vue.setText("DEX: " + character.getCharacteristic().get("DEX").toString());

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

        vue = findViewById(R.id.background);
        if(character.getBackground() == null){
            vue.setText("BACKGROUNG: NONE");
        }else {
            Log.i("test",character.getBackground());
            vue.setText("BACKGROUNG:\n" + character.getBackground());
        }


        vue = findViewById(R.id.personality);
        vue.setText("PERSONALITY:\n" + character.getPersonality_traits());

    }
}
