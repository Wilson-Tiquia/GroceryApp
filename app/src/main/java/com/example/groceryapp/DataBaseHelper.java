package com.example.groceryapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String USER_CREDENTIALS = "UserCredentials";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_USERNAME = "Username";
    public static final String COLUMN_EMAIL_ADDRESS = "EmailAddress";
    public static final String COLUMN_PASSWORD = "Password";
    public static final String USER_INFORMATION = "USER_INFORMATION";
    public static final String COLUMN_FULL_NAME = "FullName";
    public static final String COLUMN_DELIVERY_ADDRESS = "DeliveryAddress";
    public static final String COLUMN_USER_IMAGE = "UserImage";

    //images
    private ByteArrayOutputStream byteArrayOutputStream;
    private byte[] imageInBytes;

    public DataBaseHelper(@Nullable Context context) {
        super(context, "GroceryApp.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + USER_CREDENTIALS + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_USERNAME + " TEXT, " + COLUMN_EMAIL_ADDRESS + " TEXT, " + COLUMN_PASSWORD + " TEXT)";
        db.execSQL(createTable);
       // String createUserInfoTable = "CREATE TABLE " + USER_INFORMATION + " (" + COLUMN_USERNAME + " TEXT, " + COLUMN_FULL_NAME + " TEXT, " + COLUMN_DELIVERY_ADDRESS + " TEXT, " + COLUMN_USER_IMAGE + " BLOB)";
       // db.execSQL(createUserInfoTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // pag create ng account
    public boolean addAccount (RegistrationModel registrationModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_USERNAME, registrationModel.getUsername());
        cv.put(COLUMN_EMAIL_ADDRESS, registrationModel.getEmailAddress());
        cv.put(COLUMN_PASSWORD, registrationModel.getPassword());
        long insert = db.insert(USER_CREDENTIALS, null, cv);
        if (insert == -1){
            return false;
        }
        else{
            return true;
        }

    }
    // pag login ng account

    public boolean isAccountExisting (RegistrationModel registrationModel){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + USER_CREDENTIALS+ " WHERE Username = ? AND Password = ?", new String[] {registrationModel.getUsername(),registrationModel.getPassword()});
        // if greater than 0 ibigsabihin may acc
        if (cursor.getCount() > 0){
            return true;

        }
        else{
            return false;
        }
    }


    public String getEmail (String username){
        SQLiteDatabase db = this.getWritableDatabase();
        String r = "" ;
        Cursor cursor = db.rawQuery("SELECT EmailAddress FROM " + USER_CREDENTIALS+ " WHERE Username = ? ", new String[] {username});
        if(cursor.moveToFirst()){
            String value = cursor.getString(0);
            return value;
        }
        return r;

    }

    public boolean isImageStored (ImageModel imageModel){
        SQLiteDatabase db = this.getWritableDatabase();
        Bitmap bm = imageModel.getImage();
        byteArrayOutputStream = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        imageInBytes = byteArrayOutputStream.toByteArray();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_USER_IMAGE, imageInBytes);
        long insert = db.insert(USER_INFORMATION, null, cv);
        if (insert == -1){
            return false;
        }
        else{
            return true;
        }
    }


}
