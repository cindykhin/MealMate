package com.example.mealmate;

public class CalorieEntry {
    private int entry;
    private String itemName;
    private String calories;

    public CalorieEntry(int entry, String itemName, String calories) {
        this.entry = entry;
        this.itemName = itemName;
        this.calories = calories;
    }

    public String getItemName(){
        return this.itemName;
    }
    public String getCalories(){
        return this.calories;
    }
}
