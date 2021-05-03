package com.example.groceryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LogIn extends AppCompatActivity {

    EditText usernameInput, passwordInput;
    Button logInBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);


    }
    public void goToLandingPage(View v){
        // if account exist go to login
        usernameInput = findViewById(R.id.usernameInput);
        passwordInput = findViewById(R.id.passwordInput);
        RegistrationModel registrationModel = new RegistrationModel();
        registrationModel.setUsername(usernameInput.getText().toString());
        registrationModel.setPassword(passwordInput.getText().toString());
        // database check
        DataBaseHelper dataBaseHelper = new DataBaseHelper(LogIn.this);
        if(dataBaseHelper.isAccountExisting(registrationModel)){
            Intent intent = new Intent(this, LandingPage.class);
            MyApplication ma = new MyApplication();
            ma.setUsername(registrationModel.getUsername());
            startActivity(intent);
            finish();
        }
        else{
            Toast.makeText(this, "The user does not exist.", Toast.LENGTH_SHORT).show();
        }




    }

    public void goToSignUp (View v){
        Intent intent = new Intent(LogIn.this, SignUp.class);
        startActivity(intent);


    }
}