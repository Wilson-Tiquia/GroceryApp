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

public class GroceryItemAdapter extends RecyclerView.Adapter<GroceryItemAdapter.ViewHolder> {
    Context context;
    RecyclerView rv;
    ArrayList<GroceryModel> groceryList;
    private RecyclerViewClickListener listener;

    public GroceryItemAdapter (Context context, RecyclerView rv, ArrayList<GroceryModel> groceryList, RecyclerViewClickListener listener){
        this.context = context;
        this.rv = rv;
        this.groceryList =groceryList;
        this.listener = listener;

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView groceryName;
        TextView groceryPrice;
        ImageView groceryImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            groceryImage = itemView.findViewById(R.id.groceryItemImage);
            groceryName = itemView.findViewById(R.id.groceryItemName);
            groceryPrice = itemView.findViewById(R.id.groceryItemPrice);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onClick(v, getAdapterPosition());
        }
    }


    @NonNull
    @Override
    public GroceryItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.groceryitemsrecycler, parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull GroceryItemAdapter.ViewHolder holder, int position) {
        GroceryModel groceryModel = groceryList.get(position);
        holder.groceryName.setText(groceryModel.getIngredName());
        Double p = groceryModel.getIngerdPrice();
        holder.groceryPrice.setText("₱ "+String.format("%.2f",p));
        holder.groceryImage.setImageBitmap(groceryModel.getIngredImage());
    }

    @Override
    public int getItemCount() {
        return groceryList.size();
    }

    public interface RecyclerViewClickListener {
        void onClick(View v, int position);
    }

}
