package com.example.ddprojet.fonction.asyncFonc;

import android.os.AsyncTask;

import com.example.ddprojet.fragment.RaceFragment.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.String;

import connection.Race;

public class RacesGet extends AsyncTask<String, String, String> {


    private RaceDescriptionAdaptator adaptator;

    public RacesGet(RaceDescriptionAdaptator adaptator) {
        this.adaptator = adaptator;
    }

    @Override
    protected String doInBackground(String... races) {

        try {
            Race race = new Race();
            JSONObject file = race.getFile();
            JSONArray array = file.getJSONArray("results");
            for(String r : races){
                for(int i=0; i<array.length(); i++){
                    JSONObject tmp = array.getJSONObject(i);
                    publishProgress(tmp.getString("name"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        adaptator.add(values[0]);
    }
}
