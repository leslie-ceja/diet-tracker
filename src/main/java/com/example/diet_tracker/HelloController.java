package com.example.diet_tracker;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    @FXML
    private Button btn_next;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void gotoFoodGroupView(ActionEvent event){
        try {
            root = FXMLLoader.load(getClass().getResource("food-group-view.fxml"));
            System.out.println(event.getSource());
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }


}