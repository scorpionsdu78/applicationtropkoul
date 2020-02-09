package model;

import androidx.annotation.NonNull;

public class Bonus {
    private String characteristic;
    private int value;

    public Bonus(String characteristic, int value) {
        this.characteristic = characteristic;
        this.value = value;
    }

    public String getCharacteristic() {
        return characteristic;
    }

    public int getValue() {
        return value;
    }

    @NonNull
    @Override
    public String toString() {
        return characteristic + Integer.toString(value);
    }
}
