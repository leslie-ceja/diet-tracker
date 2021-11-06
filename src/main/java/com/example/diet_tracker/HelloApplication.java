package com.example.diet_tracker;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ingredient-selection-view.fxml"));//EXCEPTION HERE
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Diet Tracker");
        stage.setScene(scene);
        stage.show();

    }

    public void stop(){
        System.out.println("STOP FUNCTION");
        DBUtils.closeConnection();
    }

    public static void main(String[] args) {
        launch();
    }
}