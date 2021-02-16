package com.example.groceryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class SignUp extends AppCompatActivity {
    public EditText eMailAddress;

    public String message;

    // pag start ng program
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        eMailAddress = (EditText) findViewById(R.id.emailInput);
        Button btn = findViewById(R.id.signUpButton);
        btn.setOnClickListener(v -> sendMail());
    }
    // generate code to be sent
    public String generateCode (){
        Random r = new Random();
        String code = "";
        for (int i = 0;  i<4;i++){
            int randomNumber = r.nextInt(10);
            code += String.valueOf(randomNumber).trim();
        }
        Toast.makeText(this, code, Toast.LENGTH_SHORT).show();
        return code;
    }

    public void sendData (){
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
        Intent sendInfo = new Intent(getApplicationContext(), EmailVerification.class);
        sendInfo.putExtra("credentials", credentialInputString);
        sendInfo.putExtra("code", message);
        startActivity(sendInfo);
    }
    // code to be sent

    // send the code
    private void sendMail() {
        String emailAddressStr = eMailAddress.getText().toString().trim();
        message = generateCode();
        String subject = "=========Verification Code===========";
        JavaMailAPI javaMailAPI = new JavaMailAPI(this,emailAddressStr,subject,message);
        javaMailAPI.execute();
        sendData();
    }

}