package com.example.groceryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class CheckOutPage extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<CartDataModel> cartDataModels;
    TextView totalPrice;
    TextView deliveryFee;
    TextView totalOfAllItems;
    CartDatabase database;
    ArrayList<String> itemsInCart;
    CheckOutAdapter checkOutAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out_page);

        totalPrice = findViewById(R.id.subtotal);
        deliveryFee = findViewById(R.id.deliveryfee);
        totalOfAllItems = findViewById(R.id.total);
        // get items in cart
        database = new CartDatabase(this);
        String username = ((MyApplication) this.getApplication()).getUsername();
        itemsInCart = database.getAllItemsInCart(username);
        // recycler view set
        recyclerView = findViewById(R.id.orderSummaryRV);
        recyclerView.setHasFixedSize(true);
        // layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        // get data
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        cartDataModels = databaseAccess.getAllItemsInCartCheckout(itemsInCart);
        checkOutAdapter = new CheckOutAdapter(this,recyclerView,cartDataModels);
        recyclerView.setAdapter(checkOutAdapter);
        // get subtotal items
        Double total = databaseAccess.getTotal(itemsInCart);
        String formatted = String.format("%.2f",total);
        //set to textview
        totalPrice.setText("₱ "+formatted);
        // delivery fee
        Double fee = 100.00;
        deliveryFee.setText("₱ "+String.valueOf(fee));
        //total
        Double allItemsTotalPlusDelivery = total + fee;
        String allitemsFormat = String.format("%.2f",allItemsTotalPlusDelivery);
        totalOfAllItems.setText("₱ "+allitemsFormat);
    }
    public void goToThankYou (View v){
        // delete all items in cart
        String username = ((MyApplication) this.getApplication()).getUsername();
        ArrayList<String> itemsInCart = database.getAllItemsInCart(username);
        for (int i =0; i < itemsInCart.size();i++){
            database.deleteItemInCart(itemsInCart.get(i),username);
        }
        itemsInCart = database.getAllItemsInCart(username);
        cartDataModels.clear();
        checkOutAdapter.notifyDataSetChanged();
        TextView totalPrice = findViewById(R.id.subtotal);
        TextView deliveryFee = findViewById(R.id.deliveryfee);
        TextView totalOfAllItems = findViewById(R.id.total);
        totalOfAllItems.setText("");
        totalPrice.setText("");
        deliveryFee.setText("");
        Intent intent = new Intent(this, success.class );
        startActivity(intent);
        finish();

    }
    public void goToCarts (View v){
        Intent intent = new Intent(this, CartPage.class);
        startActivity(intent);
    }
}
