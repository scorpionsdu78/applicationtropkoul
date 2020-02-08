package com.example.ddprojet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import android.view.View;
import android.widget.TextView;

import org.json.JSONException;

import java.io.IOException;

import connection.APIconnection;
import connection.Classes;

public class DescriptionFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.description_layout, container, false);
    }


}
