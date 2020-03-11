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

        String regex = "Breath Weapon ";
        if(name.contains(regex)){
            this.subName = name.split(regex)[1];
            this.subName = this.subName.split("\\(")[1];
            this.subName = this.subName.split("\\)")[0];
            name = name.split(" \\(")[0];
        }

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
