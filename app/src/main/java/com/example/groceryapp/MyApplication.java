package com.example.groceryapp;

import android.app.Application;

public class MyApplication extends Application {

    public  static String username;

    public String getUsername(){
        return username;
    }

    public void setUsername(String Username){
        this.username = Username;
    }
}
