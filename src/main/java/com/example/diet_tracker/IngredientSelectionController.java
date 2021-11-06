package com.example.diet_tracker;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.sql.Date;
import java.time.LocalDate;
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
        initializeDatePicker();
        initializeNextButton();
    }

    public void initializeTimeChoiceBox(){
        ObservableList<String> choiceBoxData = FXCollections.observableArrayList("Breakfast", "Morning Snack", "Lunch", "Afternoon Snack", "Dinner", "Evening Snack");
        cb_time.setItems(choiceBoxData);
    }

    public void initializeDatePicker(){
        dp_date.setValue(LocalDate.now());//default to current date unless changed
    }

    public void initializeNextButton(){
        btn_next.setOnAction(actionEvent ->{
            Meal meal = new Meal(getNameInput(), getDateInput(), getRadioButtonInput());//(1) create meal object from user input

            Singleton.getSingleton().getMealList().addMeal(meal);

            FXMLLoader fxmlLoader = Singleton.getSingleton().fxmlLoader("food-group-view.fxml");
            Singleton.getSingleton().switchScene(btn_next, fxmlLoader);
            System.out.println(Singleton.getSingleton().getMealList().getMeals());
        });
    }

    public String getNameInput(){
        if(tf_meal_name.getText().isEmpty()){
            return "undefined";
        }
        else{
            return tf_meal_name.getText();
        }
    }

    public Date getDateInput(){
        Date date = Date.valueOf(dp_date.getValue());//convert to sql date for database compatibility
        return date;
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