package model;

import android.util.Log;

import org.json.JSONException;

import java.io.IOException;

import connection.APIconnection;

public class Trait extends APIconnection {

    String name;
    String Description;

    public Trait(String path, String _name) throws IOException, JSONException {
        super("https://www.dnd5eapi.co"+path);




        name = _name;
        Description = file.getString("desc");
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return Description;
    }
}
