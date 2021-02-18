package com.example.groceryapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class Registration{
    // fields
    private String [] credentialStrArray;
    private String subjectOfEmail =  "=========Verification Code===========";
    private String verificationCode;
    // getters
    public String [] getCredentials(){
        return credentialStrArray;
    }

    public String getSubjectOfEmail (){
        return subjectOfEmail;
    }

    public String getVerificationCode(){
        return verificationCode = generateCode();
    }
    //setters

    // set user email pass
    public void setCredentials (EditText [] credentialArray){
       this.credentialStrArray = new String[credentialArray.length];
        for (int i = 0; i < credentialArray.length;i++){
            this.credentialStrArray[i] = credentialArray[i].getText().toString().trim();
        }
    }

    // function para ilagay sa isang string yung input sa textview
    private String setCodeInput(EditText [] inputCodes){
        StringBuilder inputCodeCombination = new StringBuilder();
        for (EditText inputCode : inputCodes) {
            inputCodeCombination.append(inputCode.getText().toString().trim());
        }
        return inputCodeCombination.toString().trim();
    }

    // methods
    private String generateCode(){
        Random random = new Random();
        StringBuilder codeStringBuilder = new StringBuilder();
        for (int i = 0; i <4;i++){
            int randomNumber = random.nextInt(10);
            codeStringBuilder.append(randomNumber);
        }
        return codeStringBuilder.toString();
    }
    // checks if verif code and code entered is the same
    public Boolean isVerificationCodeSame(String originalCode, EditText [] inputs){
        String inputVerificationCode = setCodeInput(inputs);
        return originalCode.equals(inputVerificationCode);
    }

    public void resendOTP(TextView otp, TextView timer){
        new CountDownTimer(30000,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                timer.setVisibility(View.VISIBLE);
                timer.setText(String.valueOf(millisUntilFinished/ 1000));
                otp.setClickable(false);
            }
            @Override
            public void onFinish() {
                timer.setVisibility(View.GONE);
                otp.setClickable(true);
            }
        }.start();
    }
}
