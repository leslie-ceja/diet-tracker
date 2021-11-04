package com.example.diet_tracker;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

public class NewUserController {
    @FXML
    private ChoiceBox cb_sex, cb_age, cb_weight, cb_height_ft, cb_height_in, cb_activity_level;

    public void initialize(){//FUNCTION ISN'T BEING CALLED
        initializeSexChoiceBox();
        initializeAgeChoiceBox();
        initializeWeightChoiceBox();
        initializeHeightFtChoiceBox();
        initializeHeightInChoiceBox();
        initializeActivityChoiceBox();
    }

    public void initializeSexChoiceBox(){
        ObservableList<String> choiceBoxData = FXCollections.observableArrayList("Male", "Female");
        cb_sex.setItems(choiceBoxData);
    }
    public void initializeAgeChoiceBox(){
        ObservableList<String> choiceBoxData = FXCollections.observableArrayList();
        for(int i=18;  i<51; i++){
            choiceBoxData.add((Integer.toString(i)));
        }
        cb_age.setItems(choiceBoxData);
    }
    public void initializeWeightChoiceBox(){
        ObservableList<String> choiceBoxData = FXCollections.observableArrayList();
        for(int i=90; i<200; i++){
            choiceBoxData.add((Integer.toString(i)));
        }
        cb_weight.setItems(choiceBoxData);
    }
    public void initializeHeightFtChoiceBox(){
        ObservableList<String> choiceBoxData = FXCollections.observableArrayList();
        for(int i=1; i<10; i++){
            choiceBoxData.add(Integer.toString(i));
        }
        cb_height_ft.setItems(choiceBoxData);
    }
    public void initializeHeightInChoiceBox(){
        ObservableList<String> choiceBoxData = FXCollections.observableArrayList();
        for(int i=1; i<12; i++){
            choiceBoxData.add(Integer.toString(i));
        }
        cb_height_in.setItems(choiceBoxData);
    }
    public void initializeActivityChoiceBox(){
        ObservableList<String> choiceBoxData = FXCollections.observableArrayList("Sedentary", "Minimal", "Moderate", "High", "Extra");
        cb_activity_level.setItems(choiceBoxData);
    }
}
