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
        StringBuilder missing = new StringBuilder();

        if(meal.missingFoodGroups.size() != 0){
            for(int i=0; i<meal.missingFoodGroups.size();i++){
                if(i == meal.missingFoodGroups.size()-1){
                    missing.append(meal.missingFoodGroups.get(i));
                }
                else {
                    missing.append(meal.missingFoodGroups.get(i)).append(", ");
                }
            }
        }
        label_missing_food_groups.setText(String.valueOf(missing));
    }

    public void displayIngredients(ArrayList<String> ingredients){
        System.out.println("DISPLAY ING FUNC");
        System.out.println(ingredients);
    }
}
