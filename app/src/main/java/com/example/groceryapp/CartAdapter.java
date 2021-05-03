package com.example.groceryapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    Context context;
    RecyclerView rv;
    ArrayList<CartDataModel> cartDataModels;
    String ingredientsList;

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView dishname;
        TextView dishprice;
        TextView ingred;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.ulampic);
            dishname=itemView.findViewById(R.id.ulamname);
            dishprice=itemView.findViewById(R.id.ulamprice);
            ingred = itemView.findViewById(R.id.ingredientss);
        }
    }


    public  CartAdapter (Context context, RecyclerView rv, ArrayList<CartDataModel> cartdata, String ingredients){
        this.context = context;
        this.rv = rv;
        this.cartDataModels = cartdata;
        this.ingredientsList = ingredients;

    }
    public  CartAdapter (Context context, RecyclerView rv, ArrayList<CartDataModel> cartdata){
        this.context = context;
        this.rv = rv;
        this.cartDataModels = cartdata;

    }


    // set layout
    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.itemsincartrecycler, parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }
    // set values
    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolder holder, int position) {
        CartDataModel cartDataModel = cartDataModels.get(position);
        holder.image.setImageBitmap(cartDataModel.getDishImage());
        holder.dishname.setText(cartDataModel.getDishName());
        Double price = cartDataModel.getDishPrice();
        holder.dishprice.setText("₱ "+ String.format("%.2f",price));
        holder.ingred.setText(cartDataModel.getIngredients());

    }

    @Override
    public int getItemCount() {
        return cartDataModels.size();
    }




}
