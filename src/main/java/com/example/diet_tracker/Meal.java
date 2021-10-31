package com.example.diet_tracker;

import java.util.ArrayList;

public class Meal {
    private String name;
    ArrayList<Ingredient> ingredients = new ArrayList<>();

    ArrayList<String> includedFoodGroups = new ArrayList<>();
    ArrayList<String> missingFoodGroups = new ArrayList<>();

    public Meal() {
        name = "undefined";
    }

    public Meal(String name){
        this.name = name;
    }

    public Meal(String name, ArrayList<String> ingredients){
        this.name = name;
        addIngredients(ingredients);
        setFoodGroups();
    }

    //COPY CONSTRUCTOR
    public Meal(Meal object){
        this.name = object.name;
        this.ingredients = object.getIngredients();
        setFoodGroups();
    }

    public void addIngredient(String name, String foodGroup){
        Ingredient ingredient = new Ingredient(name, foodGroup);
        ingredients.add(ingredient);
    }

    public void addIngredient(String name){
        Ingredient ingredient = new Ingredient(name);
        ingredients.add(ingredient);
    }

    public void removeIngredient(String name){
        if(ingredients.size() != 0){
            for(int i=0;i<ingredients.size();i++){
                if(name.equals(ingredients.get(i).getName())){
                    ingredients.remove(i);
                }
            }
        }
    }

    public void addIngredients(ArrayList<String> ingredients){
        if(ingredients.size() != 0){
            for(int i=0;i<ingredients.size();i++){
                addIngredient(ingredients.get(i));
            }
        }
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public ArrayList<String> getFoodGroups(){
        ArrayList<String> foodGroups = new ArrayList<>();
        if(ingredients.size() != 0){
            for (Ingredient ingredient : ingredients) {
                foodGroups.add(ingredient.getFoodGroup());
            }
        }
        return foodGroups;
    }

    public void setFoodGroups(){
        String[] fiveFoodGroups = new String[]{"Fruits", "Vegetables", "Grains", "Protein", "Dairy"};
        ArrayList<String> foodGroups = getFoodGroups();

        if(foodGroups.size() != 0){
            for(int i=0;i<fiveFoodGroups.length;i++){
                if(foodGroups.contains(fiveFoodGroups[i])){
                    includedFoodGroups.add(fiveFoodGroups[i]);
                }
                else {
                    missingFoodGroups.add(fiveFoodGroups[i]);
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Meal{" +
                "name='" + name + '\'' +
                ", ingredients=" + ingredients +
                '}';
    }
}
