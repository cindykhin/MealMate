package com.example.mealmate;

public class GroceriesEntry {
    private int entry;
    private String itemList;
    private String quantity;
    private boolean isSelected;

    public GroceriesEntry(int entry, String itemList, String quantity, boolean isSelected) {
        this.entry = entry;
        this.itemList = itemList;
        this.quantity = quantity;
        this.isSelected = isSelected;
    };

    public String getItemList() {
        return this.itemList;
    }

    public String getQuantity() {
        return this.quantity;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
