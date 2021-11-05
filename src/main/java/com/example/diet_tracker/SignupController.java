package com.example.diet_tracker;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SignupController {
    @FXML
    private Button btn_signup, btn_login;

    @FXML
    private TextField tf_username;
    @FXML
    private PasswordField pf_password;

    public void initialize(){
        btn_signup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(!tf_username.getText().trim().isEmpty() && !pf_password.getText().trim().isEmpty()) { //NEED CHANGE HERE CHECK FOR BLANKS
                    DBUtils.signUpUser(actionEvent, tf_username.getText(), pf_password.getText());
                } else {
                    System.out.println("Please fill out all information");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please fill in all information to sign up!");
                    alert.show();
                }
            }
        });

        btn_login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtils.changeScene(actionEvent, "Log in!", null);
            }
        });
    }
}
