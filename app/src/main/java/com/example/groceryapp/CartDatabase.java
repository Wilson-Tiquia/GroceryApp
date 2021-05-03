package com.example.groceryapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CartDatabase extends SQLiteOpenHelper {

    public static final String CARTS_TABLE = "CartsTable";
    public static final String USERNAME = "Username";
    public static final String RECIPE_NAME = "RecipeName";

    public CartDatabase(@Nullable Context context) {
        super(context, "cartsdatabase.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + CARTS_TABLE + " (" + USERNAME + " TEXT, " + RECIPE_NAME + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public boolean addData(CartModel cartModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(USERNAME, cartModel.getUsername());
        cv.put(RECIPE_NAME, cartModel.getFoodname());
        long insert = db.insert(CARTS_TABLE, null, cv);
        if (insert == -1){
            return false;
        }
        else{
            return true;
        }
    }

    public ArrayList<String> getAllItemsInCart (String username){
        ArrayList<String> dishName = new ArrayList<String>();
        String queryGetAll = "SELECT * FROM CartsTable WHERE Username = ?";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryGetAll, new String[]{username});
        while(cursor.moveToNext()){
            String dish = cursor.getString(1);
            dishName.add(dish);
        }
        return dishName;
    }

    public void deleteItemInCart(String foodname, String username){
        SQLiteDatabase db = this.getReadableDatabase();
        SQLiteDatabase db2 = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT RecipeName FROM CartsTable WHERE RecipeName = ? AND Username = ? LIMIT 1", new String[]{foodname,username});
        if (cursor.moveToFirst()){
            db2.execSQL("DELETE FROM CartsTable WHERE RecipeName = ? AND Username = ?",new String[]{foodname,username});
        }
    }

}
