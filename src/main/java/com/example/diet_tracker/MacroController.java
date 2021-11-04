package com.example.diet_tracker;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;

public class MacroController {

    public Button btn_save;
    @FXML
    private PieChart pc_macros;

    private Meal meal;

    @FXML
    public void initialize(){
        btn_save.setOnAction(e->{
            FXMLLoader fxmlLoader = Singleton.getSingleton().fxmlLoader("home-view.fxml");
            Singleton.getSingleton().switchScene(btn_save,fxmlLoader);
        });
    }

    public void initializeMeal(Meal object){
        meal = new Meal(object);
    }

    public void initializeView(){
        setMacroPieChart();
    }

    public void gotoFoodLogView(){}

    public void setMacroPieChart(){
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Protein", meal.getProteinContent()),
                new PieChart.Data("Carbohydrates", meal.getCarbContent()),
                new PieChart.Data("Fat", meal.getCarbContent())
        );
        pc_macros.setData(pieChartData);
        pc_macros.setLegendVisible(true);
        pc_macros.setLegendSide(Side.BOTTOM);
    }
}
