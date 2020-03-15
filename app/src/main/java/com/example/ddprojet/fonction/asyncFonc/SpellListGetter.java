package com.example.ddprojet.fonction.asyncFonc;

import android.os.AsyncTask;

import com.example.ddprojet.connection.APIconnection;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;

public class SpellListGetter extends AsyncTask<String, String, JSONArray> {
    @Override
    protected JSONArray doInBackground(String... strings) {
        try {
            APIconnection conecteur = new APIconnection("/api/spells/");
            return conecteur.getFile().getJSONArray("results");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
