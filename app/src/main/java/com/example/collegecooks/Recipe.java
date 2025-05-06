package com.example.collegecooks;

import java.util.ArrayList;

public class Recipe {
String name;
String duration;
ArrayList<Ingredient> ingredients;

String directions;

    public Recipe() {
        name = "";
        duration = "";
        ingredients = new ArrayList<>();
        directions = "";
    }
    public Recipe(String recipeName, String time, ArrayList<Ingredient> i, String steps) {
        name = recipeName;
        duration = time;
        ingredients = i;
        directions = steps;

    }

    public void setName(String recipeName) {
        name = recipeName;
    }
    public void setDuration(String time) {
        duration = time;
        }
    public void setIngredients(ArrayList<Ingredient> i) {
        ingredients = i;
    }
    public void setDirections(String steps) {
        directions = steps;
    }

    public String getName() {
        return name;
    }
    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }
    public String getDuration() {
        return duration;
    }
    public String getDirections() {
        return directions;
    }
}
