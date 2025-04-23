package com.example.collegecooks;

public class Ingredient {
    private final double amt;
    private final String unit;
    private final String info;
    public Ingredient (double ingredientAmt, String ingredientUnit, String ingredientInfo) {
        amt = ingredientAmt;
        unit = ingredientUnit;
        info = ingredientInfo;
    }

    public double getAmt() {
        return amt;
    }
    public String getUnit() {return unit; }
    public String getInfo() {
        return info;
    }
    public boolean isNotEmpty() {
        return !(Double.isNaN(getAmt()) || getUnit().isEmpty() || getInfo().isEmpty());
    }

}
