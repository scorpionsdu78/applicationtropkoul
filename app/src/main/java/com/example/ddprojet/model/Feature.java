package com.example.ddprojet.model;

import com.example.ddprojet.connection.APIconnection;

import org.json.JSONException;

import java.io.IOException;
import java.io.Serializable;

public class Feature extends APIconnection implements Serializable {


    String name;
    String subName;
    String Desc;

    public Feature(String path) throws IOException, JSONException {
        super(path);
        name = file.getString("name");

        String regex = "Fighting Style: ";
        if(name.contains(regex)){
            this.subName = name.split(regex)[1];
            name = name.split(":")[0];
        }
        Desc = file.getJSONArray("desc").getString(0);
    }

    public Feature(String name, String desc) {
        this.name = name;
        Desc = desc;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return Desc;
    }

    public String getSubName() {
        return subName;
    }
}
