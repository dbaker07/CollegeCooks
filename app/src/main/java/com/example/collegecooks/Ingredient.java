package com.example.collegecooks;

public class Ingredient {
    private double amt;
    private String info;
    public Ingredient (double ingredientAmt, String ingredientInfo) {
        amt = ingredientAmt;
        info = ingredientInfo;
    }

    public double getAmt() {
        return amt;
    }
    public String getInfo() {
        return info;
    }

}
