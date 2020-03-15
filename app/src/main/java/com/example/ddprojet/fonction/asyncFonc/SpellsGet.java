package com.example.ddprojet.fonction.asyncFonc;

import android.os.AsyncTask;
import android.util.Log;

import com.example.ddprojet.connection.SpellList;
import com.example.ddprojet.fragment.SpellsFragment;
import com.example.ddprojet.model.Spell;

import org.json.JSONException;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.List;

public class SpellsGet extends AsyncTask<String,String, List<Spell>> {
    WeakReference<SpellsFragment.SpellAdapteur> adaptor;

    public SpellsGet(WeakReference<SpellsFragment.SpellAdapteur> frv) {
        this.adaptor = frv;
    }

    @Override
    protected List<Spell> doInBackground(String... strings) {

        for (String s: strings) {
            try {
                SpellList spels = new SpellList();
                List<Spell> result = spels.getSpellFor(s);
                Collections.sort(result);
                return result;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    protected void onPostExecute(List<Spell> spellList) {
        SpellsFragment.SpellAdapteur adapteur = adaptor.get();
        if(spellList == null){
        }
        for (Spell s: spellList) {
            adapteur.add(s);
        }
    }
}
