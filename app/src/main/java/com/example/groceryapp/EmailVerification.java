package com.example.groceryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EmailVerification extends AppCompatActivity {


    Registration user = new Registration();
    // public variables
    public Boolean isResendOTPClicked = false;
    public String verificationCode ="";
    public String newCode;
    public String emailAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_verification);
        // button para mapindot verify
        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(v -> codeInput());
        // textview para mapindot yung resend otp
        TextView tv = findViewById(R.id.resendOTP);
        tv.setOnClickListener(v -> resendOTP());
        // get data from prev activity
        Intent getInfo = getIntent();
        String [] credential = getInfo.getStringArrayExtra("credentials");
        verificationCode=getInfo.getStringExtra("originalVerificationCode");
        emailAddress = credential[1];
    }
    public void codeInput (){
        if (isResendOTPClicked){
            verificationCode = newCode;
        }
        EditText [] codes = { findViewById(R.id.firstCode), findViewById(R.id.secondCode), findViewById(R.id.thirdCode), findViewById(R.id.fourthCode)};
        if(user.isVerificationCodeSame(verificationCode,codes)){
            // save data to database :)

            // redirect to login page
            Intent goToLogin = new Intent(this, LogIn.class);
            startActivity(goToLogin);
            finish();
        }
        else{
            Toast.makeText(this, "INCORRECT VERIF CODE TANGA MO ", Toast.LENGTH_SHORT).show();
        }
        Log.d("info", "verification code " + verificationCode);
    }
    public void resendOTP(){
        isResendOTPClicked = true;
        newCode = user.getVerificationCode();
        // send the code to email
        JavaMailAPI javaMailAPI = new JavaMailAPI(this, emailAddress , user.getSubjectOfEmail(),newCode);
        javaMailAPI.execute();
        // code sa timer
        TextView resendOTP = findViewById(R.id.resendOTP);
        TextView timer = findViewById(R.id.timer);
        user.resendOTP(resendOTP, timer);
    }
}