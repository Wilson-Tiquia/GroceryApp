package com.example.groceryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    public void handleText (View v){

        // pag nakuha mo na id nya pwede mo makuha value
        EditText [] inputArray = new EditText[]{
                 findViewById(R.id.usernameInput),
                 findViewById(R.id.emailInput),
                findViewById(R.id.passwordInput)
        };
        String [] credentialInputString = new String[inputArray.length];
        for(int i =0; i<inputArray.length;i++){
            credentialInputString[i] = inputArray[i].getText().toString();
            Log.d("info", credentialInputString[i]);

        }
    }
    public void goToEmailVerification(View v){
        //Intent intent = new Intent(SignUp.this, EmailVerification.class);
        //startActivity(intent);

    }

}