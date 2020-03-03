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

import connection.SpellList;
import model.Spell;

public class spellsGet extends AsyncTask<String,String, SpellList> {
    WeakReference<EquipSortsFragment.SpellAdapteur> adaptor;

    public spellsGet(WeakReference<EquipSortsFragment.SpellAdapteur> frv) {
        this.adaptor = frv;
    }

    @Override
    protected SpellList doInBackground(String... strings) {

        for (String s: strings) {
            try {
                SpellList spels = new SpellList();
                return  spels;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    protected void onPostExecute(SpellList spellList) {
        EquipSortsFragment.SpellAdapteur adapteur = adaptor.get();
        if(spellList == null){
            Log.i("alerte","chelou pi");
        }
        for (Spell s: spellList.getSpellList()) {
            adapteur.add(s);
        }
    }
}
