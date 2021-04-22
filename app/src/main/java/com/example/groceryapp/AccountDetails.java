package com.example.groceryapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class AccountDetails extends AppCompatActivity {
    EditText usernameET, fullName, emailAddress, phoneNumber;
    ImageView picture;

    private static  final int PICK_IMAGE_REQUEST = 100;
    private Uri imageFilePath;
    private Bitmap imageToStore;
    DataBaseHelper dataBaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_details);
        usernameET = findViewById(R.id.settings_username);
        emailAddress = findViewById(R.id.settings_email);
        String s = ((MyApplication) this.getApplication()).getUsername();
        Toast.makeText(this, s  + " AKO TO ", Toast.LENGTH_SHORT).show();

        usernameET.setText(s);
        DataBaseHelper dataBaseHelper = new DataBaseHelper(AccountDetails.this);
        String email = dataBaseHelper.getEmail(s);
        emailAddress.setText(email);


    }
    public void goToVerifyPassword(View V){
        Intent intent = new Intent(this, VerifyPassword.class);
        startActivity(intent);
    }
    public void goToLandingPage(View V){
        Intent intent = new Intent(this, LandingPage.class);
        startActivity(intent);
    }
    public void goToRecipe(View v){
        Intent intent = new Intent(this, RecipePage.class);
        startActivity(intent);
    }

    // pag pili ng image
    public void chooseImage (View v){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        picture = findViewById(R.id.profile_picture);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data.getData()!=null){
            imageFilePath = data.getData();
            try {
                imageToStore = MediaStore.Images.Media.getBitmap(getContentResolver(), imageFilePath);
                picture.setImageBitmap(imageToStore);
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

    }

    // save changes sa inedit na data
    public void saveAllChanges(View v){
        // database to update
    }

}