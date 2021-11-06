package com.example.diet_tracker;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class IngredientSelectionController {

    @FXML
    private VBox vbox_fruit,vbox_vegetables, vbox_grains, vbox_protein, vbox_dairy;

    @FXML Button btn_next;

    @FXML
    private ChoiceBox cb_time;

    @FXML
    private DatePicker dp_date;

    @FXML
    private TextField tf_meal_name;

    public void initialize(){
        initializeTimeChoiceBox();
        initializeNextButton();
    }

    public void initializeTimeChoiceBox(){
        ObservableList<String> choiceBoxData = FXCollections.observableArrayList("Breakfast", "Morning Snack", "Lunch", "Afternoon Snack", "Dinner", "Evening Snack");
        cb_time.setItems(choiceBoxData);
    }

    public void initializeNextButton(){
        btn_next.setOnAction(actionEvent ->{
            Meal meal = new Meal(tf_meal_name.getText(), getRadioButtonInput());//(1) create meal object from user input

            Singleton.getSingleton().getMealList().addMeal(meal);

            FXMLLoader fxmlLoader = Singleton.getSingleton().fxmlLoader("food-group-view.fxml");
            Singleton.getSingleton().switchScene(btn_next, fxmlLoader);
            System.out.println(Singleton.getSingleton().getMealList().getMeals());
        });
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