package com.example.ddprojet.model;

import androidx.annotation.NonNull;

public class Alignment {


    private String lawfulChaoticAxis;
    private String goodEvilAxis;

    public Alignment(String lawfulChaoticAxis, String goodEvilAxis){
        this.lawfulChaoticAxis = lawfulChaoticAxis;
        this.goodEvilAxis = goodEvilAxis;
    }


    @NonNull
    @Override
    public String toString() {
        return this.lawfulChaoticAxis.getValue() + " - " + this.goodEvilAxis.toString();
    }
}
