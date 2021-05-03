package com.example.groceryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class CartPage extends AppCompatActivity {
    RecyclerView rv;
    RecyclerView.LayoutManager layoutManager;
    CartDatabase database;
    ArrayList<String> itemsInCart;
    CartAdapter cartAdapter;
    ArrayList<CartDataModel> cartDataModels;

    String username ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_page);
        // intitiazlize cartdata model
        username = ((MyApplication) this.getApplication()).getUsername();
        cartDataModels = new ArrayList<CartDataModel>();
        // get items in cart from database
        database = new CartDatabase(this);
        // set items in cart sa laman sa database
        String name = ((MyApplication) this.getApplication()).getUsername();
        itemsInCart = database.getAllItemsInCart(name);
        for (int i =0; i <itemsInCart.size();i++){
            Log.d("ITEMCART", itemsInCart.get(i).toString());
            Log.d("ITEMCART","AKO SI "+name);
        }

        // open database connection
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        // i store sa cart data model yung price ing etc
        cartDataModels = databaseAccess.getImageNamePriceIngredients(itemsInCart);
        // set recyclerview
        rv = findViewById(R.id.cartRecyclerVIew);
        rv.setHasFixedSize(true);
        // set layout
        layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        // set data
        cartAdapter = new CartAdapter(this,rv,cartDataModels);
        rv.setAdapter(cartAdapter);

        // swipe to delete
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                String foodname = cartDataModels.get(viewHolder.getAdapterPosition()).getDishName();
                Log.d("debug",foodname);
                Log.d("debug",String.valueOf(viewHolder.getAdapterPosition()));
                // delete muna sa lahat ng datasets
                database.deleteItemInCart(foodname,username);
                itemsInCart.remove(viewHolder.getAdapterPosition());
                cartDataModels.remove(viewHolder.getAdapterPosition());
                // delete na sa mismong recycler view
                //     rv.removeViewAt(viewHolder.getAdapterPosition());
                //   cartAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
                // cartAdapter.notifyItemRangeChanged(viewHolder.getAdapterPosition(),cartDataModels.size());
                cartAdapter.notifyDataSetChanged();

            }
        }).attachToRecyclerView(rv);
    }

    public void goToCheckOut(View v){

        Intent intent = new Intent(this, CheckOutPage.class);
        startActivity(intent);
    }
    public void goToLandingPage(View v){
        Intent intent = new Intent(this, LandingPage.class);
        startActivity(intent);
    }

}
