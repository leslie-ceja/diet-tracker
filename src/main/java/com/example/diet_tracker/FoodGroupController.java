package com.example.diet_tracker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class FoodGroupController {
    @FXML
    private Button btn_next;

    @FXML
    private Label label_food_groups, label_missing_food_groups;

    @FXML
    private CheckBox cb_fruits, cb_vegetables, cb_grains, cb_protein, cb_dairy;

    private Meal meal;

    public void initialize(){
        initializeMeal(Singleton.getSingleton().getMealList().getLastMeal());
        initializeView();
        initializeNextButton();
    }
    public void initializeMeal(Meal object){
        meal = new Meal(object);
    }

    public void initializeView(){
        setMissingFoodGroupsLabel();
        setFoodGroupCheckBoxes();
    }

    public void initializeNextButton(){
        btn_next.setOnAction(actionEvent -> {
            FXMLLoader fxmlLoader = Singleton.getSingleton().fxmlLoader("macro-view.fxml");
            Singleton.getSingleton().switchScene(btn_next, fxmlLoader);
        });
    }

    public void setMissingFoodGroupsLabel(){
        StringBuilder missing = new StringBuilder();

        if(meal.getMissingFoodGroups().size() != 0){
            for(int i=0; i<meal.getMissingFoodGroups().size();i++){
                if(i == meal.getMissingFoodGroups().size()-1){
                    missing.append(meal.getMissingFoodGroups().get(i));
                }
                else {
                    missing.append(meal.getMissingFoodGroups().get(i)).append(", ");
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
