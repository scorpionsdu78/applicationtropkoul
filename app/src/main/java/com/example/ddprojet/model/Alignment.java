package com.example.ddprojet.model;

import androidx.annotation.NonNull;

public class Alignment {

    public enum LawfulChaoticAxis {
        LAWFUL,
        NEUTRAL,
        CHAOTIC
    }

    public enum GoodEvilAxis {
        GOOD,
        NEUTRAL,
        EVIL
    }

    public LawfulChaoticAxis lawfulChaoticAxis;
    public GoodEvilAxis goodEvilAxis;

    public Alignment(LawfulChaoticAxis lawfulChaoticAxis, GoodEvilAxis goodEvilAxis){
        this.lawfulChaoticAxis = lawfulChaoticAxis;
        this.goodEvilAxis = goodEvilAxis;
    }


    @NonNull
    @Override
    public String toString() {
        return this.lawfulChaoticAxis + " - " + this.goodEvilAxis;
    }
}
