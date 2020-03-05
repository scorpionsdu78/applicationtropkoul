package com.example.ddprojet.model;

import android.util.Log;

import com.example.ddprojet.connection.APIconnection;

import org.json.JSONException;

import java.io.IOException;

public class Trait extends APIconnection {

    String name;
    String Description;
    String subName;

    public Trait(String path, String _name) throws IOException, JSONException {
        super(path);

        name = _name;
        if(name.contains("Breath Weapon")){
            name = "Breath Weapon";
            Log.i("testregex",_name);
            for (String s: _name.split("Breath Weapon")) {
                Log.i("testregex",s);
                subName = s;
            }
        }

        Log.i("nie",file.toString());
        Description = file.getString("desc");
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return Description;
    }

    public String getSubName() {
        return subName;
    }
}
