package com.example.diet_tracker;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MealList {
    private ArrayList<Meal> meals = new ArrayList<>();

    public ArrayList<Meal> getMeals() {
        return meals;
    }

    public void setMeals(MealList meals) {
        this.meals = meals.getMeals();
    }

    public Meal getLastMeal(){
        return this.meals.get(meals.size()-1);
    }

    public void addMeal(Meal meal){
        meals.add(meal);
    }

    public ArrayList<String> getMealDates(){
        ArrayList<String> dates = new ArrayList();

        String pattern = "MM/dd/yyyy";
        DateFormat dateFormatter = new SimpleDateFormat(pattern);
        String dateString;

        if(meals.size() != 0){
            for(Meal meal: meals){
                dateString = dateFormatter.format(meal.getDate());//convert Date to String and format it
                dates.add(dateString);
            }
        }
        Set<String> set = new HashSet<>(dates);//remove duplicate dates
        dates.clear();
        dates.addAll(set);
        return dates;
    }

    public int getCaloriesFromDate(Date searchDate){
        Date date;
        int calories = 0;
        if(meals.size() != 0){
            for(Meal meal: meals){
                date = meal.getDate();
                if(date.equals(searchDate)){
                    calories += meal.getCalories();
                }
            }
        }
        return calories;
    }
}
