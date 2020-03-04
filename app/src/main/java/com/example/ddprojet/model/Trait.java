package com.example.ddprojet.model;

import android.util.Log;

import org.json.JSONException;

import java.io.IOException;

import com.example.ddprojet.connection.APIconnection;

public class Trait extends APIconnection {

    String name;
    String Description;

    public Trait(String path, String _name) throws IOException, JSONException {
        super(path);

        name = _name;
        Log.i("nie",file.toString());
        Description = file.getString("desc");
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return Description;
    }
}