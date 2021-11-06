package com.example.diet_tracker;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

public class NewUserController {
    @FXML
    private ChoiceBox cb_sex, cb_age, cb_weight, cb_height_ft, cb_height_in, cb_activity_level;

    @FXML
    private Label label_bmr, label_caloric_need, label_missing_information;

    @FXML
    private Button btn_done;

    public void initialize(){
        initializeView();
        initializeSexChoiceBox();
        initializeAgeChoiceBox();
        initializeWeightChoiceBox();
        initializeHeightFtChoiceBox();
        initializeHeightInChoiceBox();
        initializeActivityChoiceBox();
        sexListener();
        ageListener();
        weightListener();
        heightFtListener();
        heightInListener();
        activityLevelListener();
        setMissingInformationLabel();
        setBMRandCNLabel();
    }

    public void doneButtonListener(){//don't need setOnAction b/c this function is linked to onAction through scene builder
        if(!Singleton.getSingleton().getUser().isProfileComplete()){
            setMissingInformationLabel();
        }
        else{
            FXMLLoader fxmlLoader = Singleton.getSingleton().fxmlLoader("home-view.fxml");
            Singleton.getSingleton().switchScene(btn_done, fxmlLoader);
        }
    }

    public void initializeView(){
        if(Singleton.getSingleton().getUser().isProfileComplete()){
            cb_sex.setValue(Singleton.getSingleton().getUser().getSex());
            cb_age.setValue(Singleton.getSingleton().getUser().getAge());
            cb_weight.setValue(Singleton.getSingleton().getUser().getWeight());
            int height_ft = Singleton.getSingleton().getUser().getHeight()/12;
            int height_in = Singleton.getSingleton().getUser().getHeight()%12;
            cb_height_ft.setValue(height_ft);
            cb_height_in.setValue(height_in);
            cb_activity_level.setValue(Singleton.getSingleton().getUser().getActivityLevel());
        }
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

    public void sexListener(){
        cb_sex.setOnAction(actionEvent -> {
            Singleton.getSingleton().getUser().setSex((String) cb_sex.getValue());
            setBMRandCNLabel();
        });
    }
    public void ageListener(){
        cb_age.setOnAction(actionEvent -> {
            Singleton.getSingleton().getUser().setAge(Integer.parseInt((String) cb_age.getValue()));
            setBMRandCNLabel();
        });
    }
    public void weightListener(){
        cb_weight.setOnAction(actionEvent -> {
            Singleton.getSingleton().getUser().setWeight(Integer.parseInt((String) cb_weight.getValue()));
            setBMRandCNLabel();
        });
    }
    public void heightFtListener(){
        cb_height_ft.setOnAction(actionEvent -> {
            setHeight();
        });
    }
    public void heightInListener(){
        cb_height_in.setOnAction(actionEvent -> {
            setHeight();
        });
    }
    public void setHeight(){
        if(cb_height_ft.getValue()!=null && cb_height_in.getValue()!=null){
            int height = Integer.parseInt((String)cb_height_ft.getValue()) * 12 + Integer.parseInt((String)cb_height_in.getValue());//converting  input to height in inches
            Singleton.getSingleton().getUser().setHeight(height);
            setBMRandCNLabel();
        }
    }
    public void activityLevelListener(){
        cb_activity_level.setOnAction(actionEvent -> {
            Singleton.getSingleton().getUser().setActivityLevel((String) cb_activity_level.getValue());
            setBMRandCNLabel();
        });
    }

    public void setBMRandCNLabel(){
        if(cb_sex.getValue()!=null && cb_age.getValue()!=null && cb_weight.getValue()!=null
                && cb_height_ft.getValue()!=null && cb_height_in!=null && cb_activity_level.getValue()!=null){

            Singleton.getSingleton().getUser().setBasalMetabolicRate();
            label_bmr.setText(String.valueOf(Singleton.getSingleton().getUser().getBasalMetabolicRate()));

            Singleton.getSingleton().getUser().setCaloricNeed();
            label_caloric_need.setText(String.valueOf(Singleton.getSingleton().getUser().getCaloricNeed()));

            Singleton.getSingleton().getUser().setProfileComplete(true);
            setMissingInformationLabel();
        }
    }
    public void setMissingInformationLabel(){
        label_missing_information.setVisible(!Singleton.getSingleton().getUser().isProfileComplete());
    }
}
