package com.example.groceryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class VerifyPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_password);
    }
    public void goToAccountDetails(View v){
        Intent intent = new Intent(this, AccountDetails.class);
        startActivity(intent);
    }
    public void goToResetPassword(View v){
        Intent intent = new Intent(this, ResetPassword.class);
        startActivity(intent);
    }
    public void goToResetPasswordChannel(View v){
        Intent intent = new Intent(this, ResetPasswordChannel.class);
        startActivity(intent);
    }
}