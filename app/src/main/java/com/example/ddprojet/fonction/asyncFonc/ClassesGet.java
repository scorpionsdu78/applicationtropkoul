package com.example.ddprojet.fonction.asyncFonc;

import android.os.AsyncTask;

import com.example.ddprojet.fragment.ClassFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import com.example.ddprojet.connection.Class;

public class ClassesGet extends AsyncTask<String, String, String> {

    private ClassFragment.ClassDescriptionAdaptator adaptator;

    public ClassesGet(ClassFragment.ClassDescriptionAdaptator adaptator) {
        this.adaptator = adaptator;
    }

    @Override
    protected String doInBackground(String... strings) {

        try {
            Class characterClass = new Class();
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

