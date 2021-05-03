package com.example.groceryapp;

public class CartModel {
    private String foodname;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String username;
    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    public CartModel(String foodname) {
        this.foodname = foodname;
    }

    public CartModel(String foodname, String username) {
        this.foodname = foodname;
        this.username = username;
    }
}
