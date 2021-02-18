package com.example.groceryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class SignUp extends AppCompatActivity {
    // pag start ng program
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Button btn = findViewById(R.id.signUpButton);
        btn.setOnClickListener(v -> sendMail());
    }
    private void sendMail() {
        EditText [] inputArray = { findViewById(R.id.usernameInput), findViewById(R.id.emailInput), findViewById(R.id.passwordInput) };
        Registration user = new Registration();
        user.setCredentials(inputArray);
        String [] credentialArrayString = user.getCredentials(); // 0 = usernme // 1 = email //2 = pass
        String verificationCode = user.getVerificationCode();
        JavaMailAPI javaMailAPI = new JavaMailAPI(this, credentialArrayString[1], user.getSubjectOfEmail(),verificationCode);
        javaMailAPI.execute();
        Intent sendInfo = new Intent(getApplicationContext(), EmailVerification.class);
        sendInfo.putExtra("credentials", credentialArrayString );
        sendInfo.putExtra("originalVerificationCode", verificationCode);
        startActivity(sendInfo);
    }

}