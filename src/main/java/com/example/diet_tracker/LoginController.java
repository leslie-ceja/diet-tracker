package com.example.diet_tracker;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML
    private Button btn_login, btn_signup;

    @FXML
    private TextField tf_username;
    @FXML
    private PasswordField pf_password;

    public void initialize(){
        //Anonymous Class Listener
        //An anonymous inner class must always extend a superclass or implement an interface,
        //but it cannot have an explicit extends or implements clause
        //>>Implements Interface EventHandler<T extends Event>
        //>>has one abstract method void handle(T event)
        btn_login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtils.logInUser(actionEvent, tf_username.getText(), pf_password.getText());
            }
        });

        btn_signup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtils.changeScene(actionEvent, "sign-up.fxml", null);
            }
        });
    }
}
