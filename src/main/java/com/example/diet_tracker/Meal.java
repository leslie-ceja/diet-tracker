package com.example.diet_tracker;

import java.util.ArrayList;

public class Meal {
    private String name;
    private String time;
    private int calories;
    private int proteinContent;
    private int carbContent;
    private int fatContent;
    private ArrayList<Ingredient> ingredients = new ArrayList<>();

    private ArrayList<String> includedFoodGroups = new ArrayList<>();
    private ArrayList<String> missingFoodGroups = new ArrayList<>();



    public Meal() {
        name = "undefined";
        time = "undefined";
        calories=0;
        proteinContent=0;
        carbContent=0;
        fatContent=0;
    }

    public Meal(String name){
        this.name = name;
        time = "undefined";
        calories =0;
        proteinContent=0;
        carbContent=0;
        fatContent=0;
    }

    public Meal(String name, ArrayList<String> ingredients){//(2) called from IngredientSelectionController
        this.name = name;
        addIngredients(ingredients);//here
        setFoodGroups();
        this.calories = determineCalories();
        this.proteinContent = determineProteinContent();
        this.carbContent = determineCarbContent();
        this.fatContent = determineFatContent();
    }

    //COPY CONSTRUCTOR
    public Meal(Meal object){
        this.name = object.name;
        this.ingredients = object.getIngredients();
        setFoodGroups();
        this.calories = determineCalories();
        this.proteinContent = determineProteinContent();
        this.carbContent = determineCarbContent();
        this.fatContent = determineFatContent();
    }

    /*
    public void addIngredient(String name, String foodGroup){
        Ingredient ingredient = new Ingredient(name, foodGroup);
        ingredients.add(ingredient);
    }
    */
    public void addIngredient(String name){//(4) called from addIngredients function

        //Ingredient ingredient = new Ingredient(name);
        Ingredient ingredient = DBUtils.getItemFromIngredients(name);//get ingredients from database
        System.out.println(ingredient);
        ingredients.add(ingredient);
    }

    public void removeIngredient(String name){
        if(ingredients.size() != 0){
            for(int i=0;i<ingredients.size();i++){
                if(name.equals(ingredients.get(i).getName())){
                    ingredients.remove(i);
                }
            }
        }
    }

    public void addIngredients(ArrayList<String> ingredients){//(3) called from Meal constructor
        if(ingredients.size() != 0){
            for(int i=0;i<ingredients.size();i++){
                addIngredient(ingredients.get(i));//here
            }
        }
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public ArrayList<String> getFoodGroups(){
        ArrayList<String> foodGroups = new ArrayList<>();
        if(ingredients.size() != 0){
            for (Ingredient ingredient : ingredients) {
                foodGroups.add(ingredient.getFoodGroup());
            }
        }
        return foodGroups;
    }

    public void setFoodGroups(){
        String[] fiveFoodGroups = new String[]{"Fruits", "Vegetables", "Grains", "Protein", "Dairy"};
        ArrayList<String> foodGroups = getFoodGroups();

        if(foodGroups.size() != 0){
            for(int i=0;i<fiveFoodGroups.length;i++){
                if(foodGroups.contains(fiveFoodGroups[i])){
                    includedFoodGroups.add(fiveFoodGroups[i]);
                }
                else {
                    missingFoodGroups.add(fiveFoodGroups[i]);
                }
            }
        }
    }

    public ArrayList<String> getMissingFoodGroups() {
        return missingFoodGroups;
    }

    public int determineCalories(){
        int calories=0;
        if(ingredients.size() != 0){
            for(Ingredient ingredient : ingredients){
                calories += ingredient.getCalories();
            }
        }
        return calories;
    }

    public int determineProteinContent(){
        int proteinContent=0;
        if(ingredients.size() != 0){
            for(Ingredient ingredient : ingredients){
                proteinContent += ingredient.getProtein();
            }
        }
        return proteinContent;
    }

    public int determineCarbContent(){
        int carbContent=0;
        if(ingredients.size() != 0){
            for(Ingredient ingredient : ingredients){
                carbContent += ingredient.getCarbs();
            }
        }
        return carbContent;
    }

    public int determineFatContent(){
        int fatContent=0;
        if(ingredients.size() != 0){
            for(Ingredient ingredient : ingredients){
                fatContent += ingredient.getFat();
            }
        }
        return fatContent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getProteinContent(){
        return proteinContent;
    }

    public int getCarbContent(){
        return carbContent;
    }

    public int getFatContent(){
        return fatContent;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "name='" + name + '\'' +
                ", time='" + time + '\'' +
                ", calories=" + calories +
                ", proteinContent=" + proteinContent +
                ", carbContent=" + carbContent +
                ", fatContent=" + fatContent +
                ", ingredients=" + ingredients +
                ", includedFoodGroups=" + includedFoodGroups +
                ", missingFoodGroups=" + missingFoodGroups +
                '}';
    }
}
