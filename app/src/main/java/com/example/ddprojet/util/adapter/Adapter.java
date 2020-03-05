package com.example.ddprojet.util.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends FragmentStatePagerAdapter {

    private List<Fragment> Fragments = new ArrayList<>();

    public Adapter(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return Fragments.get(position);
    }

    public void add(Fragment f){
        Fragments.add(f);
    }

    @Override
    public int getCount() {
        return Fragments.size();
    }
}
