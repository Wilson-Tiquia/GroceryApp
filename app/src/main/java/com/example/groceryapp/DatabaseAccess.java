package com.example.groceryapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;

    /**
     * Private constructor to aboid object creation from outside classes.
     *
     * @param context
     */
    private DatabaseAccess(Context context) {
        this.openHelper = new ExternalDatabase(context);
    }

    /**
     * Return a singleton instance of DatabaseAccess.
     *
     * @param context the Context
     * @return the instance of DabaseAccess
     */
    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    /**
     * Open the database connection.
     */
    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    /**
     * Close the database connection.
     */
    public void close() {
        if (database != null) {
            this.database.close();
        }
    }
    public ArrayList<DishInfo> getImageAndName(){
        ArrayList<DishInfo> dishInfoArrayList = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM recipelist",null);
        while(cursor.moveToNext()){
            String nameofdish = cursor.getString(1);
            String dishdescription = cursor.getString(2);
            byte [] imagebytes = cursor.getBlob(3);
            Bitmap objectbitmap = BitmapFactory.decodeByteArray(imagebytes,0,imagebytes.length);
            Double dishPrice = cursor.getDouble(4);
            dishInfoArrayList.add(new DishInfo(nameofdish, dishdescription, objectbitmap,dishPrice));
        }
        return dishInfoArrayList;
    }
    public Bitmap getImage(String foodname){

        Cursor cursor = database.rawQuery("SELECT RecipeImage FROM recipelist WHERE RecipeName = ?", new String[]{foodname},null);
        cursor.moveToNext();
        byte [] imagebytes = cursor.getBlob(0);
        Bitmap objectbitmap = BitmapFactory.decodeByteArray(imagebytes,0,imagebytes.length);
        return objectbitmap;
    }
    // get ingredients
    public ArrayList<String> getIngredients(String foodname){
        ArrayList<String> ingredientsList = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT Ingredients FROM ingredients WHERE RecipeName = ?", new String[]{foodname},null);
        while(cursor.moveToNext()){
            String ingredientsStr = cursor.getString(0);
            ingredientsList.add(ingredientsStr);
        }
        return ingredientsList;
    }
    // get instructions
    public ArrayList<String> getInstructions(String foodname){
        ArrayList<String> instructionsList = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT Instructions FROM instructions WHERE RecipeName = ?", new String[]{foodname},null);
        while(cursor.moveToNext()){
            String instructionsStr = cursor.getString(0);
            instructionsList.add(instructionsStr);
        }
        return instructionsList;
    }



    public ArrayList<CartDataModel> getImageNamePriceIngredients(ArrayList<String> itemsInCart) {
        ArrayList<CartDataModel> cartDataModels = new ArrayList<CartDataModel>();
        String ing = "";
        for (int i = 0; i < itemsInCart.size(); i++) {
            // get the ingredients
            Cursor cursor = database.rawQuery("SELECT Ingredients FROM ingredients WHERE RecipeName = ?", new String[]{itemsInCart.get(i)});
            while (cursor.moveToNext()) {
                String ingredientoffood = cursor.getString(0);
                ing+= ingredientoffood + "\n";
            }
            // get the name image price and store ingredient add to datamodel
            cursor = database.rawQuery("SELECT * FROM recipelist WHERE RecipeName = ?", new String[]{itemsInCart.get(i)});
            while (cursor.moveToNext()) {
                String nameofdish = cursor.getString(1);
                byte[] imagebytes = cursor.getBlob(3);
                Bitmap objectbitmap = BitmapFactory.decodeByteArray(imagebytes, 0, imagebytes.length);
                Double dishPrice = cursor.getDouble(4);
                cartDataModels.add(new CartDataModel(nameofdish, objectbitmap, dishPrice,ing));
            }
            ing = "";
            // get item from grocery
            cursor = database.rawQuery("SELECT * FROM groceryItems WHERE ItemName = ?", new String[]{itemsInCart.get(i)});
            while(cursor.moveToNext()){
                String name = cursor.getString(0);
                Double price = cursor.getDouble(1);
                byte[] imagebytes = cursor.getBlob(2);
                Bitmap objectbitmap = BitmapFactory.decodeByteArray(imagebytes, 0, imagebytes.length);
                cartDataModels.add(new CartDataModel(name,objectbitmap,price));
            }

        }
        return cartDataModels;
    }

    public ArrayList<GroceryModel> getImageNamePriceGrocery (){
        ArrayList<GroceryModel> cartDataModels = new ArrayList<GroceryModel>();
        Cursor cursor = database.rawQuery("SELECT * FROM groceryItems ",null);
        while (cursor.moveToNext()){
            String itemname = cursor.getString(0);
            Double priceDish = cursor.getDouble(1);
            byte [] imagebytes = cursor.getBlob(2);
            Bitmap objectbitmap = BitmapFactory.decodeByteArray(imagebytes,0,imagebytes.length);
            cartDataModels.add(new GroceryModel(itemname,priceDish,objectbitmap));
        }
        return cartDataModels;
    }

    public ArrayList<CartDataModel> getAllItemsInCartCheckout(ArrayList<String> itemsInCart) {
        ArrayList<CartDataModel> cartDataModels = new ArrayList<CartDataModel>();
        // store here all of the recipe
        ArrayList<String> recipesList = new ArrayList<String>();
        // loop through all recipe
        Cursor cursor = database.rawQuery("SELECT RecipeName FROM recipelist", null);
        while(cursor.moveToNext()){
            String name = cursor.getString(0);
            recipesList.add(name);
        }
        //  loop through item in cart inside the list of recipe;
        for (int i = 0; i < itemsInCart.size(); i++) {
            String currentItem = itemsInCart.get(i);
            if (recipesList.contains(currentItem)){
                cursor = database.rawQuery("SELECT * FROM recipelist WHERE RecipeName = ?", new String[]{itemsInCart.get(i)});
                while(cursor.moveToNext()){
                    String name = cursor.getString(1);
                    Double price=  cursor.getDouble(4);
                    byte [] imagebytes = cursor.getBlob(3);
                    Bitmap objectbitmap = BitmapFactory.decodeByteArray(imagebytes,0,imagebytes.length);
                    cartDataModels.add(new CartDataModel(name,objectbitmap,price));
                }
            }
            else {
                cursor = database.rawQuery("SELECT * FROM groceryItems WHERE ItemName = ?", new String[]{itemsInCart.get(i)});
                while(cursor.moveToNext()) {
                    String name = cursor.getString(0);
                    Double price=  cursor.getDouble(1);
                    byte [] imagebytes = cursor.getBlob(2);
                    Bitmap objectbitmap = BitmapFactory.decodeByteArray(imagebytes,0,imagebytes.length);
                    cartDataModels.add(new CartDataModel(name,objectbitmap,price));
                }
            }
        }
        return cartDataModels;
    }
    public Double getTotal(ArrayList<String> itemsInCart) {
        ArrayList<CartDataModel> cartDataModels = new ArrayList<CartDataModel>();
        Double totalPrice = 0.0;
        // store here all of the recipe
        ArrayList<String> recipesList = new ArrayList<String>();
        // loop through all recipe
        Cursor cursor = database.rawQuery("SELECT RecipeName FROM recipelist", null);
        while(cursor.moveToNext()){
            String name = cursor.getString(0);
            recipesList.add(name);
        }
        //  loop through item in cart inside the list of recipe;
        for (int i = 0; i < itemsInCart.size(); i++) {
            String currentItem = itemsInCart.get(i);
            if (recipesList.contains(currentItem)){
                cursor = database.rawQuery("SELECT * FROM recipelist WHERE RecipeName = ?", new String[]{itemsInCart.get(i)});
                while(cursor.moveToNext()){
                    String name = cursor.getString(1);
                    Double price=  cursor.getDouble(4);
                    totalPrice+=price;
                    byte [] imagebytes = cursor.getBlob(3);
                    Bitmap objectbitmap = BitmapFactory.decodeByteArray(imagebytes,0,imagebytes.length);
                    cartDataModels.add(new CartDataModel(name,objectbitmap,price));
                }
            }
            else {
                cursor = database.rawQuery("SELECT * FROM groceryItems WHERE ItemName = ?", new String[]{itemsInCart.get(i)});
                while(cursor.moveToNext()) {
                    String name = cursor.getString(0);
                    Double price=  cursor.getDouble(1);
                    totalPrice+=price;
                    byte [] imagebytes = cursor.getBlob(2);
                    Bitmap objectbitmap = BitmapFactory.decodeByteArray(imagebytes,0,imagebytes.length);
                    cartDataModels.add(new CartDataModel(name,objectbitmap,price));
                }
            }
        }
        return totalPrice;
    }

    public Bitmap getItemImage(String itemname){

        Cursor cursor = database.rawQuery("SELECT ItemImage FROM groceryItems WHERE ItemName = ?", new String[]{itemname},null);
        cursor.moveToNext();
        byte [] imagebytes = cursor.getBlob(0);
        Bitmap objectbitmap = BitmapFactory.decodeByteArray(imagebytes,0,imagebytes.length);
        return objectbitmap;
    }




}
