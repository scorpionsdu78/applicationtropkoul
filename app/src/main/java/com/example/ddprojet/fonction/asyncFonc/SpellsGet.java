package com.example.ddprojet.fonction.asyncFonc;

import android.os.AsyncTask;

import com.example.ddprojet.fragment.SpellsFragment;
import com.example.ddprojet.model.Spell;

import org.json.JSONException;

import java.io.IOException;
import java.lang.ref.WeakReference;

public class SpellsGet extends AsyncTask<String,String, Spell> {
    WeakReference<SpellsFragment.SpellAdapteur> adaptor;
    String classe;

    public SpellsGet(WeakReference<SpellsFragment.SpellAdapteur> frv, String inputClass) {
        this.adaptor = frv;
        classe = inputClass;
    }

    @Override
    protected Spell doInBackground(String... strings) {

        for (String s: strings) {
            try {

                Spell result = new Spell(s);
                if(result.isFor(classe)){
                    return result;
                }else {
                    return null;
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    protected void onPostExecute(Spell spell) {
        SpellsFragment.SpellAdapteur adapteur = adaptor.get();
        if(spell != null){
            adapteur.add(spell);
        }
    }
}
