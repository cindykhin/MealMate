package com.example.mealmate;

public class GroceriesEntry {
    private int entry;
    private String itemList;
    private String quantity;

    public GroceriesEntry(int entry, String itemList, String quantity) {
        this.entry = entry;
        this.itemList = itemList;
        this.quantity = quantity;
    };

    public String getItemList() {
        return this.itemList;
    }

    public String getQuantity() {
        return this.quantity;
    }
}
