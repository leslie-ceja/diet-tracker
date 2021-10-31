package com.example.diet_tracker;

import java.util.ArrayList;

public class FoodGroupController {
    Meal meal;

    public void initializeMeal(Meal object){
        meal = new Meal(object);
    }

    public void printMeal(){
        System.out.println(meal);
        meal.setFoodGroups();
        System.out.println(meal.includedFoodGroups);
        System.out.println(meal.missingFoodGroups);
    }

    public void displayIngredients(ArrayList<String> ingredients){
        System.out.println("DISPLAY ING FUNC");
        System.out.println(ingredients);
    }
}
