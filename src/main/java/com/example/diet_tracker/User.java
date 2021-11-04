package com.example.diet_tracker;

public class User {
    public String displayName;
    private String sex;
    private int age;
    private int weight;
    private int heightFt;
    private int heightIn;
    private String activityLevel;

    public User() {
    }

    public User(String displayName) {
        this.displayName = displayName;
    }
}
