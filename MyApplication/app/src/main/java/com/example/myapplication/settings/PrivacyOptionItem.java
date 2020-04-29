package com.example.myapplication.settings;

public class PrivacyOptionItem {
    private String itemName;
    private int itemIcon;

    public PrivacyOptionItem(String itemName, int itemIcon) {
        this.itemName = itemName;
        this.itemIcon = itemIcon;
    }

    public String getItemName() {
        return this.itemName;
    }

    public int getItemIcon() {
        return this.itemIcon;
    }
}
