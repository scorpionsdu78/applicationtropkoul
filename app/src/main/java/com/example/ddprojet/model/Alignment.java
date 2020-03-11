package com.example.ddprojet.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Alignment implements Serializable {


    private String lawfulChaoticAxis;
    private String goodEvilAxis;

    public Alignment(String lawfulChaoticAxis, String goodEvilAxis){
        this.lawfulChaoticAxis = lawfulChaoticAxis;
        this.goodEvilAxis = goodEvilAxis;
    }


    @NonNull
    @Override
    public String toString() {
        return this.lawfulChaoticAxis.toString() + " - " + this.goodEvilAxis.toString();
    }
}
