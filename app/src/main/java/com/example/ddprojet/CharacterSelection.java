package com.example.ddprojet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CharacterSelection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_selection);
    }


    public void ChangeActivity(View V){
        Intent callActivity = new Intent(getApplicationContext(), CharacterEditionActivity.class);
        startActivity(callActivity);
    }


}
