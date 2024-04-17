package com.example.mealmate;

public class RecipeEntry {
    private int entry;
    private String itemList;

    public RecipeEntry(int entry, String itemList) {
        this.entry = entry;
        this.itemList = itemList;
    }

    public String getItemList() {
        return this.itemList;
    }
}
