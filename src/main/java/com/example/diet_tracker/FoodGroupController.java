package com.example.diet_tracker;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

import java.util.ArrayList;

public class FoodGroupController {
    @FXML
    private Label label_missing_food_groups;

    @FXML
    private CheckBox cb_fruits, cb_vegetables, cb_grains, cb_protein, cb_dairy;

    Meal meal;

    public void initializeMeal(Meal object){
        meal = new Meal(object);
    }

    public void initializeView(){
        printMeal();
        setMissingFoodGroupsLabel();
        setFoodGroupCheckBoxes();
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

    public void setFoodGroupCheckBoxes(){
        String missing = label_missing_food_groups.getText();
        cb_fruits.setSelected(missing.contains("Fruits"));
        cb_vegetables.setSelected(missing.contains("Vegetables"));
        cb_grains.setSelected(missing.contains("Grains"));
        cb_protein.setSelected(missing.contains("Protein"));
        cb_dairy.setSelected(missing.contains("Dairy"));
    }
}
