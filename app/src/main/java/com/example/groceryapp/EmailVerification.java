package com.example.groceryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EmailVerification extends AppCompatActivity {
    public String verificationCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_verification);
        Intent getData = getIntent();
        String [] creds = getData.getStringArrayExtra("credentials");
        verificationCode= getData.getStringExtra("code");
    }
    public void codeInput (View v){
        EditText [] codes = { findViewById(R.id.firstCode), findViewById(R.id.secondCode), findViewById(R.id.thirdCode), findViewById(R.id.fourthCode)};
        String code = "";
        for (int i = 0; i <codes.length;i++)
        {
            code += codes[i].getText().toString().trim();
        }
        if (verificationCode.equals(code)){
            Toast.makeText(this, "CORRECT VERIFICATION ", Toast.LENGTH_SHORT).show();
            // Save Data To DATAFUCKING BASE PANO ??
            Intent backToLogin = new Intent(this, LogIn.class);
            startActivity(backToLogin);
        }
        if (!verificationCode.equals(code)){
            Toast.makeText(this, "INCORRECT VERIFICATION CODE", Toast.LENGTH_SHORT).show();
        }

    }

}