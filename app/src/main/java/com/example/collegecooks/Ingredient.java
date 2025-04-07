package com.example.collegecooks;

public class Ingredient {
    private String name;
    private String unit;
    public Ingredient (String ingredientName, String ingredientUnit) {
        name = ingredientName;
        unit = ingredientUnit;
    }

    public String getName() {
        return name;
    }
    public String getUnit() {
        return unit;
    }

}
