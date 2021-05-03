package com.example.groceryapp;

import android.graphics.Bitmap;

public class GroceryModel {
    private String ingredName;
    private double ingerdPrice;
    private Bitmap ingredImage;

    public GroceryModel(String ingredName, double ingerdPrice, Bitmap ingredImage) {

        this.ingredName = ingredName;
        this.ingerdPrice = ingerdPrice;
        this.ingredImage = ingredImage;
    }

    public String getIngredName() {
        return ingredName;
    }

    public double getIngerdPrice() {
        return ingerdPrice;
    }

    public Bitmap getIngredImage() {
        return ingredImage;
    }
}
