package com.example.ddprojet.model;

import androidx.annotation.NonNull;

public class Alignment {

    public enum LawfulChaoticAxis {
        LAWFUL("Lawful"),
        NEUTRAL("Neutral"),
        CHAOTIC("Chaotic");

        private String value;

        LawfulChaoticAxis(String value){
            this.value = value;
        }

        public String getValue(){
            return this.value;
        }
    }

    public enum GoodEvilAxis {
        GOOD("Good"),
        NEUTRAL("Neutral"),
        EVIL("Evil");

        private String value;

        GoodEvilAxis(String value){
            this.value = value;
        }

        public String getValue(){
            return this.value;
        }
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
        return this.lawfulChaoticAxis.getValue() + " - " + this.goodEvilAxis.toString();
    }
}
