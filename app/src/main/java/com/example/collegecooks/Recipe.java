package com.example.collegecooks;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class Recipe {

private String name;
private String duration;
private ArrayList<Ingredient> ingredients;
private String imageUrl;

private String directions;

    public Recipe() {
        name = "";
        duration = "";
        ingredients = new ArrayList<Ingredient>();
        imageUrl = "";
        directions = "";
    }

    public Recipe(String recipeName, String time, ArrayList<Ingredient> ingredients, String steps, String url) {
        name = recipeName;
        duration = time;
        this.ingredients = ingredients;
        directions = steps;
        imageUrl = url;

    }

    public Recipe(String recipeName, String time, ArrayList<Ingredient> ingredients, String steps){
        name = recipeName;
        duration = time;
        this.ingredients = ingredients;
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
    public String getImageUrl() {return imageUrl; }
    @NonNull
    public String toString() {
        return name;
    }
    public void setName(String recipeName)
    { name = recipeName;}
    public void setDuration(String time) {duration =time;}
    public void setIngredients(ArrayList<Ingredient> ingredients){
        this.ingredients = ingredients;
    }
    public void setUrl(String url) { imageUrl = url;}
    public void setDirections(String directions) {this.directions = directions;}

}
