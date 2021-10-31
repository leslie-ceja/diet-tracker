package com.example.diet_tracker;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;

public class MacroController {

    @FXML
    private PieChart pc_macros;

    private Meal meal;

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
