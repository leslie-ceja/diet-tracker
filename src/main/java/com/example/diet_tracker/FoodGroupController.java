package com.example.diet_tracker;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.ArrayList;

public class FoodGroupController {
    @FXML
    private Label label_missing_food_groups;

    Meal meal;

    public void initializeMeal(Meal object){
        meal = new Meal(object);
    }

    public void printMeal(){
        System.out.println(meal);
        System.out.println(meal.includedFoodGroups);
        System.out.println(meal.missingFoodGroups);
    }

    public void setMissingFoodGroupsLabel(){
        label_missing_food_groups.setText(label_missing_food_groups.getText() + meal.missingFoodGroups);
    }

    public void displayIngredients(ArrayList<String> ingredients){
        System.out.println("DISPLAY ING FUNC");
        System.out.println(ingredients);
    }
}
