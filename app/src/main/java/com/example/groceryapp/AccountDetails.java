package com.example.groceryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AccountDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_details);
    }
    public void goToVerifyPassword(View V){
        Intent intent = new Intent(this, VerifyPassword.class);
        startActivity(intent);
    }
    public void goToLandingPage(View V){
        Intent intent = new Intent(this, LandingPage.class);
        startActivity(intent);
    }
    public void goToRecipe(View v){
        Intent intent = new Intent(this, RecipePage.class);
        startActivity(intent);
    }


}