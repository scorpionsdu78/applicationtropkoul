package com.example.ddprojet.model;

import com.example.ddprojet.connection.APIconnection;

import org.json.JSONException;

import java.io.IOException;

public class Feature extends APIconnection {


    String name;
    String subName;
    String Desc;

    public Feature(String path) throws IOException, JSONException {
        super(path);
        name = file.getString("name");
        if(name.contains("Fighting Style:")){
            name = "Fighting Style:";
            for (String s: name.split("Fighting Style:")) {
                subName = s;
            }
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
