package com.example.groceryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class GroceryItem extends AppCompatActivity {
    TextView itemname;
    TextView itemprice;
    ImageView itemimage;
    TextView quantityTV;
    ImageView incrementBtn;
    ImageView decrementBtn;
    String name = "";
    Double price = 0.0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery_item);

        itemname = findViewById(R.id.groceryItemNameSpecific);
        itemprice = findViewById(R.id.groceryItemPriceSpecific);
        itemimage = findViewById(R.id.groceryItemImageSpecific);

        // get data from intent
        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            name = extras.getString("itemname");
            price = extras.getDouble("itemprice");
        }
        // set values
        itemname.setText(name);
        itemprice.setText("₱ "+String.format("%.2f",price));
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        Bitmap bitmap = databaseAccess.getItemImage(name);
        itemimage.setImageBitmap(bitmap);

    }

    public void increment(View v){
        quantityTV = findViewById(R.id.quantity);
        incrementBtn = findViewById(R.id.incrementbutton);
        itemprice = findViewById(R.id.groceryItemPriceSpecific);
        // get quantity
        String quantity = quantityTV.getText().toString();
        // concert sa int yung quantity
        int quantityInt = Integer.parseInt(quantity);
        int total = quantityInt+1;
        Double newPrice = Double.valueOf(total) * price;
        Log.d("PRESYO", String.valueOf(newPrice));
        // also update the price
        quantityTV.setText(String.valueOf(total));
        itemprice.setText("₱ "+String.format("%.2f",newPrice));
    }
    public void decrement(View v){
        quantityTV = findViewById(R.id.quantity);
        decrementBtn = findViewById(R.id.decrementbutton);
        itemprice = findViewById(R.id.groceryItemPriceSpecific);
        String quantity = quantityTV.getText().toString();
        int quantityInt = Integer.parseInt(quantity);
        int total = quantityInt;
        if(total == 1){
            Double newPrice = Double.valueOf(total)*price;
        }
        if(total > 1){
            decrementBtn.setEnabled(true);
            quantityTV.setText(String.valueOf(total-1));
            total=quantityInt-1;
            Double newPrice = Double.valueOf(total)*price;
            Log.d("ERRORULET", String.valueOf(newPrice));
            itemprice.setText("₱"+String.format("%.2f",newPrice));
        }
    }
    public void addToCartGroceryItem (View v){
        String username = ((MyApplication) this.getApplication()).getUsername();
        quantityTV = findViewById(R.id.quantity);
        itemprice = findViewById(R.id.groceryItemPriceSpecific);
        String ilanPinili = quantityTV.getText().toString();
        int ilanInt = Integer.parseInt(ilanPinili);
        CartDatabase database = new CartDatabase(this);
        CartModel cartModel = new CartModel(name,username);
        for (int i =0; i < ilanInt;i++){
            database.addData(cartModel);
        }
        Toast.makeText(this, "Item added to cart.", Toast.LENGTH_SHORT).show();
        quantityTV.setText("1");
        itemprice.setText(String.format("%.2f",price));
    }

    public void goToSettings(View v){
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }
    public void goToLandingPage(View v){
        Intent intent = new Intent(this, LandingPage.class);
        startActivity(intent);
    }
    public void goToRecipe(View v){
        Intent intent = new Intent(this, RecipePage.class);
        startActivity(intent);
    }
    public void goToCart(View v){
        Intent intent = new Intent(this, CartPage.class);
        startActivity(intent);
    }
}