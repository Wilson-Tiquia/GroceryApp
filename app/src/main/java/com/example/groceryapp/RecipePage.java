package com.example.groceryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class RecipePage extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    DishInfoAdapter.RecyclerViewClickListener listener;
    ArrayList<DishInfo> dishInfoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_page);
        setOnClickListener();
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        dishInfoList = databaseAccess.getImageAndName();
        recyclerView = findViewById(R.id.recipeRV);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        DishInfoAdapter dishInfoAdapter = new DishInfoAdapter(this, dishInfoList,recyclerView,listener);
        recyclerView.setAdapter(dishInfoAdapter);
        databaseAccess.close();
    }

    private void setOnClickListener() {
        listener = new DishInfoAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {

                Intent intent = new Intent(getApplicationContext(), RecipeClick.class);
                intent.putExtra("foodname", dishInfoList.get(position).getDishName());
                intent.putExtra("fooddescription", dishInfoList.get(position).getDishDescription());
                startActivity(intent);


            }
        };
    }
    public void goToSettings(View v){
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }
    public void goToLandingPage(View v){
        Intent intent = new Intent(this, LandingPage.class);
        startActivity(intent);
    }
}