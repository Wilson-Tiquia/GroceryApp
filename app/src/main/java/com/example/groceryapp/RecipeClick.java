package com.example.groceryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RecipeClick extends AppCompatActivity {
    TextView foodNameTV;
    TextView foodDescriptionTV;
    ImageView dishImage;
    ArrayList<String> ingredientsList;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    String foodStrPublic;
    String foodDescriptionPublic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_click);
        foodNameTV = findViewById(R.id.dishnameRC);
        foodDescriptionTV = findViewById(R.id.dishDescriptionRC);
        dishImage = findViewById(R.id.dishpicRC);
        recyclerView = findViewById(R.id.ingredientsRV);
        String foodname = "Some food name";
        String fooddescription = "Some description";
        // get data from prev activity
        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            foodname = extras.getString("foodname");
            fooddescription = extras.getString("fooddescription");
        }
        foodStrPublic = foodname;
        foodDescriptionPublic = fooddescription;
        // get image to database;
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        Bitmap foodimg = databaseAccess.getImage(foodname);
        ingredientsList = databaseAccess.getIngredients(foodname);
        // set layout
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        IngredientsAdapter ingredientsAdapter = new IngredientsAdapter(this,ingredientsList,recyclerView);
        recyclerView.setAdapter(ingredientsAdapter);
        databaseAccess.close();
        // set data
        // set data
        foodNameTV.setText(foodname);
        foodDescriptionTV.setText(fooddescription);
        dishImage.setImageBitmap(foodimg);

    }
    public void goToInstructions (View v){
        Intent intent = new Intent(this, Instructions.class);
        intent.putExtra("foodname", foodStrPublic);
        intent.putExtra("fooddescription", foodDescriptionPublic);
        startActivity(intent);
    }

    public void addToCart(View v){
        String name = ((MyApplication) this.getApplication()).getUsername();
        CartDatabase cartDatabase = new CartDatabase(this);
        CartModel cartModel = new CartModel(foodStrPublic,name);
        boolean success = cartDatabase.addData(cartModel);
        Toast.makeText(this, "Item added to cart.", Toast.LENGTH_SHORT).show();
    }
    public void goToAllRecipe (View v){
        Intent intent = new Intent(this, RecipePage.class);
        startActivity(intent);
    }
}