package com.example.collegecooks;

import java.util.ArrayList;

public class Recipe {
String name;
String duration;
ArrayList<Ingredient> ingredientList;

ArrayList<String> directions;

    public Recipe(String recipeName, String time, ArrayList<Ingredient> ingredients, ArrayList <String> steps) {
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
    public ArrayList<String> getDirections() {
        return directions;
    }
}
