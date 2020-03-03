package model;

import org.json.JSONException;

import java.io.IOException;

import connection.APIconnection;

public class Feature extends APIconnection {


    String name;
    String Desc;

    public Feature(String path) throws IOException, JSONException {
        super(path);
        name = file.getString("name");
        Desc = file.getString("desc");
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return Desc;
    }
}
