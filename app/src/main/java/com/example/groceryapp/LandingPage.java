package com.example.groceryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class LandingPage extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    GroceryItemAdapter.RecyclerViewClickListener listener;
    ArrayList<GroceryModel> groceryItems;
    String name;
    @Override
    public void onBackPressed () {


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        name = ((MyApplication) this.getApplication()).getUsername();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);
        String s = ((MyApplication) this.getApplication()).getUsername();
        setOnClickListener();
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        groceryItems = databaseAccess.getImageNamePriceGrocery();

        recyclerView = findViewById(R.id.groceryRV);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayoutManager);
        GroceryItemAdapter groceryItemAdapter = new GroceryItemAdapter(this,recyclerView,groceryItems,listener);
        recyclerView.setAdapter(groceryItemAdapter);
        databaseAccess.close();

    }
    private void setOnClickListener() {
        listener = new GroceryItemAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {

                Intent intent = new Intent(getApplicationContext(), GroceryItem.class);
                intent.putExtra("itemname", groceryItems.get(position).getIngredName());
                intent.putExtra("itemprice", groceryItems.get(position).getIngerdPrice());
                startActivity(intent);

                CartDatabase database = new CartDatabase(getApplicationContext());
                ArrayList<String> inCart = database.getAllItemsInCart(name);
                for (int i =0; i < inCart.size();i++){
                    Log.d("ERRORPOTA", inCart.get(i).toString());
                }
            }
        };
    }


    public void goToSettings(View v){
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }
    public void goToChosenItem(View v){
        Intent intent = new Intent(this, GroceryItem.class);
        startActivity(intent);
    }
    public void goToRecipe(View v){
        Intent intent = new Intent(this, RecipePage.class);
        startActivity(intent);
    }
    public void gotoCart (View v){
        Intent intent = new Intent (this, CartPage.class);
        startActivity(intent);
    }
}


