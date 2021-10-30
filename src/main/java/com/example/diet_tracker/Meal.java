package com.example.diet_tracker;

import java.util.ArrayList;

public class Meal {
    private String name;
    ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();

    public Meal() {
        name = "undefined";
    }

    public Meal(String name){
        this.name = name;
    }

    public Meal(String name, ArrayList<String> ingredients){
        this.name = name;
        addIngredients(ingredients);
    }

    //COPY CONSTRUCTOR
    public Meal(Meal object){
        this.name = object.name;
        this.ingredients = object.getIngredients();
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

    @Override
    public String toString() {
        return "Meal{" +
                "name='" + name + '\'' +
                ", ingredients=" + ingredients +
                '}';
    }
}
