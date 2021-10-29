package com.example.diet_tracker;

public class Ingredient {
    private String foodGroup;
    private int protein;
    private int carbs;
    private int fat;

    public Ingredient() {
        foodGroup = "unknown";
        protein = 0;
        carbs = 0;
        fat =0;
    }

    public Ingredient(String foodGroup, int protein, int carbs, int fat) {
        this.foodGroup = foodGroup;
        this.protein = protein;
        this.carbs = carbs;
        this.fat = fat;
    }

    public String getFoodGroup() {
        return foodGroup;
    }

    public void setFoodGroup(String foodGroup) {
        this.foodGroup = foodGroup;
    }

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public int getCarbs() {
        return carbs;
    }

    public void setCarbs(int carbs) {
        this.carbs = carbs;
    }

    public int getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }
}
