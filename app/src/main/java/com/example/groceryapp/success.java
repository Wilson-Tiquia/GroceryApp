package com.example.groceryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class success extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

        Intent intent = new Intent(this, LandingPage.class);
        startActivity(intent);
        Toast.makeText(this, "Order Placed", Toast.LENGTH_SHORT).show();
        finish();
    }
    @Override
    public void onBackPressed () {


    }
}