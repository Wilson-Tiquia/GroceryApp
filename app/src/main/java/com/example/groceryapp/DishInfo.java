package com.example.groceryapp;

import android.graphics.Bitmap;

public class DishInfo {
    private String dishName;
    private Bitmap dishImage;
    private String dishDescription;
    private String dishIngredients;
    private Double dishPrice;
    public DishInfo(String dishName, String dishDescription, Bitmap dishImage, Double dishPrice) {
        this.dishName = dishName;
        this.dishImage = dishImage;
        this.dishDescription = dishDescription;
        this.dishPrice = dishPrice;
    }

    public DishInfo(String dishIngredients) {
        this.dishIngredients = dishIngredients;
    }

    public String getDishName() {
        return dishName;
    }

    public Bitmap getDishImage() {
        return dishImage;
    }

    public String getDishDescription() {
        return dishDescription;
    }

    public String getDishIngredients() {
        return dishIngredients;
    }

    public Double getDishprice() {
        return dishPrice;
    }
}
