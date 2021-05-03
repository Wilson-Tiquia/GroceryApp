package com.example.groceryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class Instructions extends AppCompatActivity {

    TextView foodname;
    TextView foodDescription;
    ArrayList<String> instructionsList;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    String foodnameStr = "";
    String foodDescriptionStr = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);

        // get name and descr from prev activity
        Intent intent = getIntent();
        foodnameStr = intent.getStringExtra("foodname");
        foodDescriptionStr = intent.getStringExtra("fooddescription");
        // set ids
        foodname = findViewById(R.id.dishNameInstructions);
        foodDescription = findViewById(R.id.dishDescriptionInstructions);
        recyclerView = findViewById(R.id.instructionsListRV);
        // set data
        foodname.setText(foodnameStr);
        foodDescription.setText(foodDescriptionStr);

        // get database
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        instructionsList = databaseAccess.getInstructions(foodnameStr);
        databaseAccess.close();

        // set recyclerview and layout manager

        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        // instantiate instructions adapter
        InstructionsAdapter instructionsAdapter = new InstructionsAdapter(this,instructionsList,recyclerView);
        recyclerView.setAdapter(instructionsAdapter);

    }
    public void goToRecipeClick (View v){
        Intent intent = new Intent(this, RecipeClick.class);
        intent.putExtra("foodname", foodnameStr);
        intent.putExtra("fooddescription", foodDescriptionStr);
        startActivity(intent);
    }
}