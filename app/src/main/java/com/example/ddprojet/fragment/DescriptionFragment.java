package com.example.ddprojet.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ddprojet.R;
import com.example.ddprojet.fonction.asyncFonc.RaceInfoGet;

import java.util.concurrent.ExecutionException;

import com.example.ddprojet.connection.Race;

public class DescriptionFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.race_info, container, false);

        RaceInfoGet getter = new RaceInfoGet(v,(TextView)v.findViewById(R.id.name),(TextView)v.findViewById(R.id.speed),(TextView)v.findViewById(R.id.alignement),(TextView)v.findViewById(R.id.age),
                (TextView)v.findViewById(R.id.size), (TextView)v.findViewById(R.id.sizeDesc),(TextView)v.findViewById(R.id.langDesc) ,(RecyclerView)v.findViewById(R.id.languages),
                (RecyclerView)v.findViewById(R.id.trait),(RecyclerView)v.findViewById(R.id.Bonus));

        getter.execute("dwarf");
        Race race;

        try {
            race = getter.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return v;
    }
}
