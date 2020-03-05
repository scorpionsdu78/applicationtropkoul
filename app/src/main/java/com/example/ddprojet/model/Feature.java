package com.example.ddprojet.model;

import org.json.JSONException;

import java.io.IOException;

import com.example.ddprojet.connection.APIconnection;

public class Feature extends APIconnection {


    String name;
    String Desc;

    public Feature(String path) throws IOException, JSONException {
        super(path);
        name = file.getString("name");
        Desc = file.getJSONArray("desc").getString(0);
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return Desc;
    }
}
