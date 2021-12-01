package com.example.diet_tracker;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class HomeController {
    @FXML
    private Button btn_logout, btn_profile, btn_add_item;

    @FXML
    private Label label_welcome, label_update;

    @FXML
    private ChoiceBox cb_dates;

    @FXML
    private BarChart bc_calorie_consumption;

    @FXML
    private TableView<Meal> meal_table;
    public TableColumn<Meal, String> date_col;
    public TableColumn<Meal, String> time_col;
    public TableColumn<Meal, String> name_col;
    public TableColumn<Meal, String> calorie_col;

    @FXML
    public void initialize(){
        setCustomWelcomeLabel();
        setUpdateProfileLabel();
        initializeLogOutButton();
        initializeProfileButton();
        initializeAddItemButton();

        initializeTableView();

        initializeDatesChoiceBox();
    }

    public void setCustomWelcomeLabel() {
        String username = Singleton.getSingleton().getUser().getUsername();
        label_welcome.setText("Welcome  " + username + "!");
    }

    public void setUpdateProfileLabel(){
        label_update.setVisible(!Singleton.getSingleton().getUser().isProfileComplete());
    }

    public void initializeLogOutButton(){
        btn_logout.setOnAction(actionEvent -> {
            FXMLLoader fxmlLoader = Singleton.getSingleton().fxmlLoader("login-view.fxml");
            Singleton.getSingleton().switchScene(btn_logout, fxmlLoader);
        });
    }

    public void initializeProfileButton(){
        btn_profile.setOnAction(actionEvent -> {
            FXMLLoader fxmlLoader = Singleton.getSingleton().fxmlLoader("new-user-view.fxml");
            Singleton.getSingleton().switchScene(btn_profile,fxmlLoader);
        });
    }

    public void initializeAddItemButton(){
        btn_add_item.setOnAction(actionEvent -> {
            FXMLLoader fxmlLoader = Singleton.getSingleton().fxmlLoader("ingredient-selection-view.fxml");
            Singleton.getSingleton().switchScene(btn_add_item, fxmlLoader);
        });
    }

    public void initializeTableView(){
        date_col.setCellValueFactory(new PropertyValueFactory<>("date"));//internally uses getters
        time_col.setCellValueFactory(new PropertyValueFactory<>("time"));
        name_col.setCellValueFactory(new PropertyValueFactory<>("name"));
        calorie_col.setCellValueFactory(new PropertyValueFactory<>("calories"));

        //meal_table.getItems().addAll(Singleton.getSingleton().getMealList().getMeals());
        meal_table.getItems().addAll(DBUtils.retrieveMeals(Singleton.getSingleton().getUser().getUsername()));
        System.out.println(Singleton.getSingleton().getUser());
    }

    public void initializeDatesChoiceBox(){
        System.out.println(Singleton.getSingleton().getMealList().getMeals());
        ObservableList<String> choiceBoxData = FXCollections.observableArrayList(Singleton.getSingleton().getMealList().getMealDates());
        cb_dates.setItems(choiceBoxData);
        //NEED TO SET ON ACTION
        //WHEN BUTTON DATA IS SELECTED >> DISPLAY BAR CHART
        cb_dates.setOnAction(actionEvent -> {
            String selectedDate = (String) cb_dates.getValue();// MM/DD/YYYY
            setBarChart(selectedDate);
        });
    }

    public void setBarChart(String selectedDate){
        //string manipulation//////////////////////////////////////////////////////////////////
        selectedDate = selectedDate.replace('/','-');

        char charArray[] = selectedDate.toCharArray();
        String monthDay = new String(charArray,0,5);
        String year = new String(charArray,6,4);

        String formattedDate = year + "-" + monthDay;//create new string in required format

        Date date = Date.valueOf(formattedDate);//String has to be in YYYY-MM-DD format to work
        ///////////////////////////////////////////////////////////////////////////////////////

        int calorieIntake = Singleton.getSingleton().getMealList().getCaloriesFromDate(date);//get calorie intake >> add to chart
        XYChart.Series intake = new XYChart.Series();
        intake.setName("Intake");
        intake.getData().add(new XYChart.Data("Calories", calorieIntake));
        bc_calorie_consumption.getData().add(intake);

        int caloricNeed = (int) Singleton.getSingleton().getUser().getCaloricNeed();//get caloric need >> add to chart
        XYChart.Series need = new XYChart.Series();
        need.setName("Need");
        need.getData().add(new XYChart.Data("Calories", caloricNeed));
        bc_calorie_consumption.getData().add(need);

        bc_calorie_consumption.setLegendVisible(true);
        bc_calorie_consumption.setVisible(true);
    }
}
