package com.example.diet_tracker;

import java.util.ArrayList;

public class MealList {
    private ArrayList<Meal> meals = new ArrayList<>();

    public ArrayList<Meal> getMeals() {
        return meals;
    }

    public void setMeals(ArrayList<Meal> meals) {
        this.meals = meals;
    }

    public Meal getLastMeal(){
        return this.meals.get(meals.size()-1);
    }

    public void addMeal(Meal meal){
        meals.add(meal);
    }
}
