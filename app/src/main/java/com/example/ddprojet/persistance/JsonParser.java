package com.example.ddprojet.persistance;

import android.util.Log;

import com.google.gson.Gson;

import com.example.ddprojet.model.Character;

public class JsonParser {

    private Character character;
    private String json;
    private Gson converter;

    public JsonParser(Character character) {
        this.character = character;
        converter = new Gson();
        json = converter.toJson(character);
    }

    public void printJson(){
        Log.i("testJson",json);
    }

    public String getJson() {
        return json;
    }
}
