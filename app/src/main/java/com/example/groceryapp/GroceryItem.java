package com.example.groceryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class GroceryItem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery_item);
    }
    public void goToSettings(View v){
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }
    public void goToLandingPage(View v){
        Intent intent = new Intent(this, LandingPage.class);
        startActivity(intent);
    }
}