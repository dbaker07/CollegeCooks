package com.example.collegecooks;


public class Ingredient {
    private double amt;
    private String unit;
    private String info;

    /**
     * Default recipe constructor (needed for firebase) constructs a new recipe object with an empty unit string,
     * an empty info string, and a default amount of 0
     */
    public Ingredient() {
        amt = 0;
        unit = "";
        info = "";
    }

    /**
     * Parameter construct - takes in a user-given amount, a user-given unit, and a user-given ingredient name
     * @param ingredientAmt is the user-given amount of the ingredient
     * @param ingredientUnit is the user-given unit of the amount of the ingredient
     * @param ingredientInfo is the user-given name of the the ingredient
     */
    public Ingredient (double ingredientAmt, String ingredientUnit, String ingredientInfo) {
        amt = ingredientAmt;
        unit = ingredientUnit;
        info = ingredientInfo;
    }

    /**
     * Returns the amount of the ingredient needed
     * @return will return the amount value of an ingredient object
     */
    public double getAmt() {
        return amt;
    }

    /**
     * Returns the unit of the ingredient
     * @return will return the unit of the ingredient object
     */
    public String getUnit() {return unit; }

    /**
     * Returns the name of the ingredient
     * @return will return the name of the ingredient object
     */
    public String getInfo() {
        return info;
    }

    /**
     * Sets the amount of the ingredient object to a user-given amount (needed for firebase)
     * @param ingredientAmt is the user-given amount of the ingredient
     */
    public void setAmt(double ingredientAmt) {
        amt = ingredientAmt;
    }

    /**
     * Sets the unit of the ingredient object to a user-given string (needed for firebase)
     * @param ingredientUnit is the user-given unit of the ingredient
     */
    public void setUnit(String ingredientUnit) {
        unit = ingredientUnit;
    }

    /**
     * Sets the name of the ingredient object to a user-given name (needed for firebase)
     * @param ingredientInfo is the user-given name of the ingredient
     */
    public void setInfo(String ingredientInfo) {
        info = ingredientInfo;
    }


}