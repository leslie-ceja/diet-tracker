package com.example.diet_tracker;

import java.util.ArrayList;
import java.util.Arrays;

public class Ingredient {
    private String name;
    private String foodGroup;
    private int protein;
    private int carbs;
    private int fat;

    public Ingredient() {
        name = "unknown";
        foodGroup = "unknown";
        protein = 0;
        carbs = 0;
        fat =0;
    }

    public Ingredient(String name){
        this.name = name;
        //DETERMINE FOOD GROUP
        this.foodGroup = determineFoodGroup(name);
        //DETERMINE MACROS
        this.protein = determineProtein(name);
        this.carbs = determineCarbs(name);
        this.fat = determineFat(name);
    }

    public Ingredient(String name, String foodGroup) {
        this.name = name;
        this.foodGroup = foodGroup;
        this.protein = determineProtein(name);
        this.carbs = determineCarbs(name);
        this.fat = determineFat(name);
    }

    public Ingredient(String name,String foodGroup, int protein, int carbs, int fat) {
        this.name = name;
        this.foodGroup = foodGroup;
        this.protein = protein;
        this.carbs = carbs;
        this.fat = fat;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
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

    public String determineFoodGroup(String ingredient){
        String food_group;
        switch(ingredient) {
            case "Apple" :
            case "Orange" :
            case "Banana" :
            case "Peach" :
            case "Pear" :
            case "Grapes" :
            case "Watermelon" :
            case "Pomegranate" :
            case "Strawberry" :
            case "Pineapple" :
            case "Mango" :
                food_group = "Fruits";
                break;
            case "Broccoli" :
            case "Spinach" :
                food_group = "Vegetables";
                break;
            case "Chicken" :
                food_group = "Protein";
                break;
            case "Pasta" :
                food_group = "Grains";
                break;
            case "Cheese" :
                food_group = "Dairy";
                break;
            default:
                food_group = "unknown";
        }

        return food_group;
    }

    public int determineProtein(String name){
        String[] proteins = new String[]{"Chicken"};
        ArrayList<String> proteinList = new ArrayList<>(Arrays.asList(proteins));
        if(proteinList.contains(name)){
            return 1;
        }
        else {
            return 0;
        }
    }

    public int determineCarbs(String name){
        String[] carbs = new String[]{"Apple", "Banana", "Orange", "Peach", "Pear", "Grapes", "Watermelon",
                "Pomegranate", "Strawberry", "Pineapple", "Mango", "Broccoli", "Spinach", "Pasta"};
        ArrayList<String> carbList = new ArrayList<>(Arrays.asList(carbs));
        if(carbList.contains(name)){
            return 1;
        }
        else {
            return 0;
        }
    }

    public int determineFat(String name){
        String[] fats = new String[]{"Cheese"};
        ArrayList<String> fatList = new ArrayList<>(Arrays.asList(fats));
        if(fatList.contains(name)){
            return 1;
        }
        else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "name='" + name + '\'' +
                ", foodGroup='" + foodGroup + '\'' +
                ", protein=" + protein +
                ", carbs=" + carbs +
                ", fat=" + fat +
                '}';
    }
}
