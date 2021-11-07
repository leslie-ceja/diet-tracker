package com.example.diet_tracker;

public class User {
    public String username;
    private String sex;
    private int age;
    private int weight;
    private int height;//in inches for bmr calculation
    private String activityLevel;
    private double basalMetabolicRate;
    private double caloricNeed;
    private boolean profileComplete = false;

    public User() {
    }

    public User(User object){
        this.username = object.username;
        this.sex = object.sex;
        this.age = object.age;
        this.weight = object.weight;
        this.activityLevel = object.activityLevel;
        this.basalMetabolicRate = object.basalMetabolicRate;
        this.caloricNeed = object.caloricNeed;
        this.profileComplete = object.profileComplete;
    }

    public User(String username) {
        this.username = username;
    }

    public User(String username, String sex, int age, int weight, int height, String activityLevel, double basalMetabolicRate, double caloricNeed, boolean profileComplete) {
        this.username = username;
        this.sex = sex;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.activityLevel = activityLevel;
        this.basalMetabolicRate = basalMetabolicRate;
        this.caloricNeed = caloricNeed;
        this.profileComplete = profileComplete;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getActivityLevel() {
        return activityLevel;
    }

    public void setActivityLevel(String activityLevel) {
        this.activityLevel = activityLevel;
    }

    public boolean isProfileComplete() {
        return profileComplete;
    }

    public double getBasalMetabolicRate() {
        return basalMetabolicRate;
    }

    public void setBasalMetabolicRate() {
        double bmr;
        if(sex.equals("Male")){
            bmr = 66 + (6.3 * weight) + (12.9 * height) - (6.8 * age);
        }
        else{
            bmr = 655 + (4.3 * weight) + (4.7 * height) - (4.7 * age);
        }
        this.basalMetabolicRate = bmr;
    }

    public double getCaloricNeed() {
        return caloricNeed;
    }

    public void setCaloricNeed() {
        double cn;
        switch(activityLevel){
            case "Minimal":
                cn= basalMetabolicRate * 1.375;
                break;
            case "Moderate":
                cn= basalMetabolicRate * 1.55;
                break;
            case "High":
                cn= basalMetabolicRate * 1.725;
                break;
            case "Extra":
                cn= basalMetabolicRate * 1.9;
                break;
            default://Sedentary
                cn= basalMetabolicRate * 1.2;
                break;
        }
        this.caloricNeed = cn;
    }

    public void setProfileComplete(boolean profileComplete) {
        this.profileComplete = profileComplete;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", weight=" + weight +
                ", height=" + height +
                ", activityLevel='" + activityLevel + '\'' +
                ", basalMetabolicRate=" + basalMetabolicRate +
                ", caloricNeed=" + caloricNeed +
                ", profileComplete=" + profileComplete +
                '}';
    }
}
