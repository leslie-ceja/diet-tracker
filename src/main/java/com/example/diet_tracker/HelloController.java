package com.example.diet_tracker;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class HelloController {

    @FXML
    private VBox vbox_fruit,vbox_vegetables, vbox_grains, vbox_protein, vbox_dairy;

    @FXML
    private TextField tf_meal_name;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void gotoFoodGroupView(ActionEvent event){
        try {

            ArrayList<String> ingredients = getRadioButtonInput();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("food-group-view.fxml"));
            root = loader.load();

            Meal meal = new Meal(tf_meal_name.getText(), getRadioButtonInput());

            FoodGroupController foodGroupController = loader.getController();
            foodGroupController.initializeMeal(meal);
            foodGroupController.printMeal();

            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getRadioButtonInput(){
        ArrayList<String> ingredientsSelected= new ArrayList<>();

        RadioButton rbtn;

        for(Node child: vbox_fruit.getChildren()){//LOOP THROUGH FRUITS
            rbtn = (RadioButton) child;
            if(rbtn.isSelected()){
                ingredientsSelected.add(rbtn.getText());//ADD SELECTED TO ARRAY LIST
            }
        }

        for(Node child: vbox_vegetables.getChildren()){//LOOP THROUGH VEGETABLES
            rbtn = (RadioButton) child;
            if(rbtn.isSelected()){
                ingredientsSelected.add(rbtn.getText());//ADD SELECTED TO ARRAY LIST
            }
        }

        for(Node child: vbox_grains.getChildren()){//LOOP THROUGH GRAINS
            rbtn = (RadioButton) child;
            if(rbtn.isSelected()){
                ingredientsSelected.add(rbtn.getText());//ADD SELECTED TO ARRAY LIST
            }
        }

        for(Node child: vbox_protein.getChildren()){//LOOP THROUGH PROTEIN
            rbtn = (RadioButton) child;
            if(rbtn.isSelected()){
                ingredientsSelected.add(rbtn.getText());//ADD SELECTED TO ARRAY LIST
            }
        }

        for(Node child: vbox_dairy.getChildren()){//LOOP THROUGH DAIRY
            rbtn = (RadioButton) child;
            if(rbtn.isSelected()){
                ingredientsSelected.add(rbtn.getText());//ADD SELECTED TO ARRAY LIST
            }
        }

        return ingredientsSelected;
    }


}