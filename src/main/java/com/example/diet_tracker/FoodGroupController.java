package com.example.diet_tracker;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class FoodGroupController {
    @FXML
    private Label label_food_groups, label_missing_food_groups;

    @FXML
    private CheckBox cb_fruits, cb_vegetables, cb_grains, cb_protein, cb_dairy;

    private Meal meal;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void initializeMeal(Meal object){
        meal = new Meal(object);
    }

    public void initializeView(){
        printMeal();
        setMissingFoodGroupsLabel();
        setFoodGroupCheckBoxes();
    }

    public void gotoMacroView(ActionEvent event){
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("macro-view.fxml"));
            root = loader.load();

            MacroController macroController = loader.getController();

            macroController.initializeMeal(meal);//pass meal object
            macroController.initializeView();

            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void printMeal(){
        System.out.println(meal);
        System.out.println(meal.includedFoodGroups);
        System.out.println(meal.missingFoodGroups);
        System.out.println(meal.getProteinContent());
        System.out.println(meal.getCarbContent());
        System.out.println(meal.getFatContent());
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
        else{
            label_food_groups.setText("Your meal includes all five food Groups!");
        }
        label_missing_food_groups.setText(String.valueOf(missing));
    }

    public void setFoodGroupCheckBoxes(){
        String missing = label_missing_food_groups.getText();
        cb_fruits.setSelected(!missing.contains("Fruits"));

        cb_vegetables.setSelected(!missing.contains("Vegetables"));
        cb_grains.setSelected(!missing.contains("Grains"));
        cb_protein.setSelected(!missing.contains("Protein"));
        cb_dairy.setSelected(!missing.contains("Dairy"));
        setFoodGroupCheckBoxesRed();

    }

    public void setFoodGroupCheckBoxesRed(){
        if(!cb_fruits.isSelected())
            cb_fruits.setTextFill(Color.color(1, 0, 0));
        if(!cb_vegetables.isSelected())
            cb_vegetables.setTextFill(Color.color(1, 0, 0));
        if(!cb_grains.isSelected())
            cb_grains.setTextFill(Color.color(1, 0, 0));
        if(!cb_protein.isSelected())
            cb_protein.setTextFill(Color.color(1, 0, 0));
        if(!cb_dairy.isSelected())
            cb_dairy.setTextFill(Color.color(1, 0, 0));
    }

}
