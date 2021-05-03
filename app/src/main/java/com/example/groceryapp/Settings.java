package com.example.groceryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Settings extends AppCompatActivity {
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        tv = findViewById(R.id.full_name);
        String s = ((MyApplication) this.getApplication()).getUsername();
        tv.setText(s);
        Button [] btns = {findViewById(R.id.current_orders_button),
                findViewById(R.id.past_orders_button),
                findViewById(R.id.edit_account_button),
                findViewById(R.id.my_payment_button),
                findViewById(R.id.favorites_button),
                findViewById(R.id.faqs_button),
                findViewById(R.id.terms_and_conditions_button)
        };
        for (int i =0 ;i<btns.length;i++){
            int counter = i;
            btns[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(Settings.this, "Future Feature", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    public void goToAccountDetails(View v){
        Intent intent = new Intent(this, AccountDetails.class);
        startActivity(intent);
    }
    public void goToLandingPage(View v){
        Intent intent = new Intent(this, LandingPage.class);
        startActivity(intent);
    }
    public void goToRecipe(View v){
        Intent intent = new Intent(this, RecipePage.class);
        startActivity(intent);
    }
    public void goToCart(View v){
        Intent intent = new Intent(this, CartPage.class);
        startActivity(intent);
    }
}