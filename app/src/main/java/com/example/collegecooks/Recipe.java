package com.example.collegecooks;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class Recipe {

private String name;
private String duration;
private ArrayList<Ingredient> ingredients;
private String imageUrl;

private String directions;

    /**
     * Default constructor (needed for firebase) sets the recipe name, duration,image url, and directions to empty strings and constructs a
     * new arraylist of ingredients
     */
    public Recipe() {
        name = "";
        duration = "";
        ingredients = new ArrayList<Ingredient>();
        imageUrl = "";
        directions = "";
    }

    /**
     *Parameter constructor - sets the recipe name, duration, directions, and url to user-given strings of those values
     * and a user-given arrayList of ingredients
     * @param recipeName is the user-given name of the recipe
     * @param time is the user-given duration
     * @param ingredients the the user-given arrayList of ingredients
     * @param steps is the user-given directions
     * @param url is the user-given url for the recipe image (removed due to programming issues)
     */
    public Recipe(String recipeName, String time, ArrayList<Ingredient> ingredients, String steps, String url) {
        name = recipeName;
        duration = time;
        this.ingredients = ingredients;
        directions = steps;
        imageUrl = url;

    }

    /**
     * Parameter constructor #2 - if the recipe does not have an image, use this constructor
     * @param recipeName is the user-given name of the recipe
     * @param time is the user-given duration
     * @param ingredients the the user-given arrayList of ingredients
     * @param steps is the user-given directions
     */
    public Recipe(String recipeName, String time, ArrayList<Ingredient> ingredients, String steps){
        name = recipeName;
        duration = time;
        this.ingredients = ingredients;
        directions = steps;
    }

    /**
     * Returns the name of the recipe
     * @return will return the name of the recipe
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the arrayList of the ingredients needed for the recipe
     * @return will return an ArrayList of the ingredients needed for the recipe
     */
    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    /**
     * Returns the duration of the recipe
     * @return will return the duration that the recipe will take
     */
    public String getDuration() {
        return duration;
    }

    /**
     * Returns a string of directions to make the recipe
     * @return will return the directions for the recipe
     */
    public String getDirections() {
        return directions;
    }

    /**
     * Returns the url of the image of the recipe (this feature is not currently in use)
     * @return will return the url of the image
     */
    public String getImageUrl() {return imageUrl; }


    /**
     * Sets the name of the recipe to a user-given name (needed for firebase)
     * @param recipeName is the user-given name of the recipe
     */
    public void setName(String recipeName)
    { name = recipeName;}

    /**
     * Sets the duration of the recipe to a user-given duration (needed for firebase)
     * @param time is the user-given duration of the recipe
     */
    public void setDuration(String time) {duration =time;}

    /**
     * Sets the arrayList to a user-given arrayList of ingredients (required for firebase)
     * @param ingredients is the user-given arrayList of ingredients needed for the recipe
     */
    public void setIngredients(ArrayList<Ingredient> ingredients){
        this.ingredients = ingredients;
    }

    /**
     * Sets the url for the image of a recipe to a user-given url (removed from current version) (Required for firebase)
     * @param url is the user-given url
     */
    public void setUrl(String url) { imageUrl = url;}

    /**
     * Sets the directions of the recipe to a user-given string of directions
     * @param directions is the user-given directions of the recipe
     */
    public void setDirections(String directions) {this.directions = directions;}

}
