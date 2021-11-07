package com.example.diet_tracker;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;

public class DBUtils {

    private static Connection connection = null;

    public static void changeScene(ActionEvent event, String fxmlFile, String username){
        Parent root = null;

        if(username != null){ //button_login passes in valid username + logged-in.fxml
            try {
                //Singleton.getSingleton().getUser().setUsername(username);//set username

                FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
                root = loader.load();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else { //button_signup passes in null username + signup-view.fxml
            try {
                root = FXMLLoader.load(Objects.requireNonNull(DBUtils.class.getResource(fxmlFile)));//EXCEPTION HERE
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root,600,400));
        stage.show();
    }

    public static void startConnection(){
        //Create connection to database
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx-app", "root", "Songforyou21");
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public static void logInUser(ActionEvent event, String username, String password){
        startConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{
            preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            if(!resultSet.isBeforeFirst()) { //returns false if cursor is at other position or result set contains no rows
                System.out.println("User not found in database!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Provided credentials are incorrect!");
            } else {
                while(resultSet.next()) {//moves cursor forward one row
                    String retrievedPassword = resultSet.getString("password");
                    if(retrievedPassword.equals(password)){
                        String name = resultSet.getString("username");
                        boolean profile = resultSet.getBoolean("profileComplete");
                        User user;

                        if(profile) {//if profile is complete create user with full user constructor
                            String sex = resultSet.getString("sex");
                            int age = resultSet.getInt("age");
                            int weight = resultSet.getInt("weight");
                            int height = resultSet.getInt("height");
                            String activity = resultSet.getString("activity");
                            double bmr = resultSet.getDouble("bmr");
                            double caloricNeed = resultSet.getDouble("caloricNeed");
                            user = new User(name, sex, age, weight, height, activity, bmr, caloricNeed, profile);
                            Singleton.getSingleton().setUser(user);
                        }
                        else{//if user profile is not complete use name only constructor
                            user = new User(name);
                            Singleton.getSingleton().setUser(user);
                        }
                        System.out.println("profileComplete: "  + profile);
                        changeScene(event, "home-view.fxml", username);
                    } else {
                        System.out.println("Passwords did not match!");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("The provided credentials are incorrect!");
                        alert.show();
                    }
                }
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        finally {
            if(resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            }
            //closeConnection();
        }
    }

    public static void signUpUser(ActionEvent event, String username, String password){
        startConnection();
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExists = null;
        ResultSet resultSet = null;

        try {
            psCheckUserExists = connection.prepareStatement("SELECT * FROM users WHERE username + ?");
            psCheckUserExists.setString(1, username);
            resultSet = psCheckUserExists.executeQuery();

            if(resultSet.isBeforeFirst()) {
                System.out.println("User already exists!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("You cannot user this username.");
                alert.show();
            } else {
                psInsert = connection.prepareStatement("INSERT INTO users (username, password) VALUES (?, ?)");
                psInsert.setString(1, username);
                psInsert.setString(2, password);
                psInsert.executeUpdate();

                User user = new User(username);
                Singleton.getSingleton().setUser(user);

                changeScene(event, "home-view.fxml", username);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        finally {
            if(resultSet != null) {
                try {
                    resultSet.close();
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            }
            if(psCheckUserExists != null) {
                try {
                    psCheckUserExists.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            //closeConnection();
        }
    }

    public static void closeConnection(){
        if(connection != null) {
            try {
                connection.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public static void updateUser(String name, String sex, int age, int weight, int height, String activity, double bmr, double caloricNeed){
        PreparedStatement psUpdate = null;
        int rowAffected = 0;
        try{
            psUpdate = connection.prepareStatement("UPDATE users SET sex = ? WHERE username = ?");
            psUpdate.setString(1, sex);
            psUpdate.setString(2, name);
            rowAffected = psUpdate.executeUpdate();

            psUpdate = connection.prepareStatement("UPDATE users SET age = ? WHERE username = ?");
            psUpdate.setInt(1, age);
            psUpdate.setString(2, name);
            rowAffected = psUpdate.executeUpdate();

            psUpdate = connection.prepareStatement("UPDATE users SET weight = ? WHERE username = ?");
            psUpdate.setInt(1, weight);
            psUpdate.setString(2, name);
            rowAffected = psUpdate.executeUpdate();

            psUpdate = connection.prepareStatement("UPDATE users SET height = ? WHERE username = ?");
            psUpdate.setInt(1, height);
            psUpdate.setString(2, name);
            rowAffected = psUpdate.executeUpdate();

            psUpdate = connection.prepareStatement("UPDATE users SET activity = ? WHERE username = ?");
            psUpdate.setString(1, activity);
            psUpdate.setString(2, name);
            rowAffected = psUpdate.executeUpdate();

            psUpdate = connection.prepareStatement("UPDATE users SET bmr = ? WHERE username = ?");
            psUpdate.setDouble(1, bmr);
            psUpdate.setString(2, name);
            rowAffected = psUpdate.executeUpdate();

            psUpdate = connection.prepareStatement("UPDATE users SET caloricNeed = ? WHERE username = ?");
            psUpdate.setDouble(1, caloricNeed);
            psUpdate.setString(2, name);
            rowAffected = psUpdate.executeUpdate();

            psUpdate = connection.prepareStatement("UPDATE users SET profileComplete = ? WHERE username = ?");
            psUpdate.setBoolean(1, true);
            psUpdate.setString(2, name);
            rowAffected = psUpdate.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(psUpdate != null){
                try{
                    psUpdate.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static void insertMeal(String username, String name, Date date, String time, int calories, int protein, int carbs, int fat){
        PreparedStatement psInsert = null;
        try{
            psInsert = connection.prepareStatement("INSERT INTO mealtable (username, date, time, mealName, calories, protein, carbs, fat) VALUES (?,?,?,?,?,?,?,?)");
            psInsert.setString(1, username);
            psInsert.setDate(2, date);
            psInsert.setString(3, time);
            psInsert.setString(4, name);
            psInsert.setInt(5, calories);
            psInsert.setInt(6, protein);
            psInsert.setInt(7, carbs);
            psInsert.setInt(8, fat);
            psInsert.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(psInsert != null){
                try{
                    psInsert.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static ArrayList<Meal> retrieveMeals(String username){
        Meal meal;
        MealList meals = new MealList();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            preparedStatement = connection.prepareStatement("SELECT * FROM mealtable WHERE username = ?");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            if(!resultSet.isBeforeFirst()){//if no results found
                return meals.getMeals();
            }
            else{
                while(resultSet.next()){//if results found
                    Date date = resultSet.getDate("date");
                    String time = resultSet.getString("time");
                    String name = resultSet.getString("mealName");
                    int calories = resultSet.getInt("calories");
                    int protein = resultSet.getInt("protein");
                    int carbs = resultSet.getInt("carbs");
                    int fat = resultSet.getInt("fat");
                    meal = new Meal(name, date,time,calories, protein, carbs, fat);
                    meals.addMeal(meal);
                }
                return meals.getMeals();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return meals.getMeals();
    }

    public static Ingredient getItemFromIngredients(String itemName){//(5) called from Meal addIngredient function
        Ingredient ingredient = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            preparedStatement = connection.prepareStatement("SELECT * FROM ingredients WHERE itemName = ?");
            preparedStatement.setString(1, itemName);
            resultSet = preparedStatement.executeQuery();
            if(!resultSet.isBeforeFirst()){//if no results found
                ingredient = new Ingredient(itemName);//return ingredient with name only constructor
                return ingredient;
            }
            else{
                while(resultSet.next()) {//if results found
                    String name = resultSet.getString("itemName");
                    String foodGroup = resultSet.getString("foodGroup");
                    int calories = resultSet.getInt("calories");
                    int protein = resultSet.getInt("protein");
                    int carbs = resultSet.getInt("carbs");
                    int fat = resultSet.getInt("fat");
                    ingredient = new Ingredient(name, foodGroup, calories, protein, carbs, fat);//return populated ingredient
                    System.out.println(name);
                    return ingredient;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return ingredient;
    }
}
