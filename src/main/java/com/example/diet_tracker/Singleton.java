package com.example.diet_tracker;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class Singleton {
    private static Singleton singleton = new Singleton();
    public ArrayList<Meal> meals = new ArrayList<>();
    private MealList mealList = new MealList();

    private Singleton(){

    }

    public static Singleton getSingleton(){
        return singleton;
    }

    public FXMLLoader fxmlLoader(String fxmlFile) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
        try{
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fxmlLoader;
    }

    public void switchScene(Node node, FXMLLoader fxmlLoader){
        Stage stage = (Stage) node.getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.getRoot());
        stage.setScene(scene);
    }

    public MealList getMealList() {
        return mealList;
    }
}
