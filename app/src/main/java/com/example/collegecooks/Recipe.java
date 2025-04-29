package com.example.collegecooks;

import java.util.ArrayList;

public class Recipe {
String name;
String duration;
ArrayList<Ingredient> ingredientList;

String directions;


    public Recipe(String recipeName, String time, ArrayList<Ingredient> ingredients, String steps) {
        name = recipeName;
        duration = time;
        ingredientList = ingredients;
        directions = steps;

    }
    public String getName() {
        return name;
    }
    public ArrayList<Ingredient> getIngredients() {
        return ingredientList;
    }
    public String getDuration() {
        return duration;
    }
    public String getDirections() {
        return directions;
    }
}
