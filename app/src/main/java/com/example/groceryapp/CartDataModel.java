package com.example.groceryapp;

import android.graphics.Bitmap;

public class CartDataModel {
    private String dishName;
    private Bitmap dishImage;
    private Double dishPrice;
    private String ingredients;
    public String getIngredients() {
        return ingredients;
    }

    public CartDataModel (String dishName, Bitmap dishImage, Double dishPrice) {
        this.dishName = dishName;
        this.dishImage = dishImage;
        this.dishPrice = dishPrice;
    }

    public CartDataModel(String dishName, Bitmap dishImage, Double dishPrice, String ingredients) {
        this.dishName = dishName;
        this.dishImage = dishImage;
        this.dishPrice = dishPrice;
        this.ingredients = ingredients;
    }
    public String getDishName() {
        return dishName;
    }

    public Bitmap getDishImage() {
        return dishImage;
    }

    public Double getDishPrice() {
        return dishPrice;
    }
}
