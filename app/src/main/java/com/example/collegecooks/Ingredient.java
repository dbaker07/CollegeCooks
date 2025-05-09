package com.example.collegecooks;

import androidx.annotation.NonNull;

public class Ingredient {
    private double amt;
    private String unit;
    private String info;

    public Ingredient() {
        amt = 0;
        unit = "";
        info = "";
    }
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
    
    public void setAmt(double ingredientAmt) {
        amt = ingredientAmt;
    }
    public void setUnit(String ingredientUnit) {
        unit = ingredientUnit;
    }
    public void setInfo(String ingredientInfo) {
        info = ingredientInfo;
    }


}