package com.example.groceryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ResetPasswordChannel extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password_channel);
    }
    public void goToVerifyPassword(View v){
        Intent intent = new Intent(this, VerifyPassword.class);
        startActivity(intent);
    }


}