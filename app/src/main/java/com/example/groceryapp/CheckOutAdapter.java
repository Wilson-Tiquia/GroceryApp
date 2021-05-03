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

public class CheckOutAdapter extends RecyclerView.Adapter<CheckOutAdapter.ViewHolder> {
    Context context;
    RecyclerView rv;

    ArrayList<CartDataModel> recipeItems;

    public CheckOutAdapter (Context context, RecyclerView rv,  ArrayList<CartDataModel> cartModels){
        this.context = context;
        this.rv = rv;
        this.recipeItems = cartModels;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView price;
        ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.checkoutItemName);
            price = itemView.findViewById(R.id.checkoutItemPrice);
            image = itemView.findViewById(R.id.checkOutItemImage);
        }
    }

    @NonNull
    @Override
    public CheckOutAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.ordersummaryrecycler, parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CheckOutAdapter.ViewHolder holder, int position) {
        CartDataModel cartDataModel = recipeItems.get(position);
        holder.name.setText(cartDataModel.getDishName());
        Double price2 = cartDataModel.getDishPrice();
        holder.price.setText("₱ "+ String.format("%.2f",price2));
        holder.image.setImageBitmap(cartDataModel.getDishImage());
    }

    @Override
    public int getItemCount() {
        return recipeItems.size();
    }



}
