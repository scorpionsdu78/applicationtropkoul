package com.example.ddprojet.fonction.asyncFonc;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ddprojet.R;
import com.example.ddprojet.fragment.EquipSortsFragment;

import org.json.JSONException;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.List;

import connection.SpellList;
import model.Spell;

public class spellsGet extends AsyncTask<String,String, List<Spell>> {
    WeakReference<EquipSortsFragment.SpellAdapteur> adaptor;

    public spellsGet(WeakReference<EquipSortsFragment.SpellAdapteur> frv) {
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
        EquipSortsFragment.SpellAdapteur adapteur = adaptor.get();
        if(spellList == null){
            Log.i("alerte","chelou pi");
        }
        for (Spell s: spellList) {
            Log.i("alerte",s.getName());
            adapteur.add(s);
        }
    }
}
