package com.example.diet_tracker;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class HomeController {


    @FXML
    private Label label_welcome;

    @FXML
    private TableView<Meal> meal_table;
    public TableColumn<Meal, String> time_col;
    public TableColumn<Meal, String> name_col;
    public TableColumn<Meal, String> calorie_col;

    @FXML
    public void initialize(){
        time_col.setCellValueFactory(new PropertyValueFactory<>("time"));
        name_col.setCellValueFactory(new PropertyValueFactory<>("name"));
        calorie_col.setCellValueFactory(new PropertyValueFactory<>("calories"));

        meal_table.getItems().addAll(Singleton.getSingleton().meals);
    }

    public void setUserInformation(String username) {
        label_welcome.setText("Welcome  " + username + "!");
    }
}
