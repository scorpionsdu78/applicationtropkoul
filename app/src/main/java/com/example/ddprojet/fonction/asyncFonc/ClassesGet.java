package com.example.ddprojet.fonction.asyncFonc;

import android.os.AsyncTask;

import com.example.ddprojet.fragment.ClassesFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import connection.Classes;

public class ClassesGet extends AsyncTask<String, String, String> {

    private ClassesFragment.ClassDescriptionAdaptator adaptator;

    public ClassesGet(ClassesFragment.ClassDescriptionAdaptator adaptator) {
        this.adaptator = adaptator;
    }

    @Override
    protected String doInBackground(String... strings) {

        try {
            Classes characterClass = new Classes();
            JSONObject file = characterClass.getFile();
            JSONArray array = file.getJSONArray("results");
            for (String s: strings) {
                for(int i =0; i<array.length(); i++){
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
