package com.example.ddprojet.persistance;

import com.example.ddprojet.model.Character;
import com.google.gson.Gson;

public class JsonParser {

    private Character character;
    private String json;
    private Gson converter;

    public JsonParser(Character character) {
        this.character = character;
        converter = new Gson();
        json = converter.toJson(character);
    }

    public String getJson() {
        return json;
    }
}
