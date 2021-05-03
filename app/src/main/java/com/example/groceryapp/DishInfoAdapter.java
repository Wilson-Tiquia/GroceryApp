package com.example.groceryapp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DishInfoAdapter extends RecyclerView.Adapter<DishInfoAdapter.ViewHolder> {
    Context context;
    ArrayList<DishInfo> dishInfoArrayList;
    RecyclerView recyclerView;
    private RecyclerViewClickListener listener;

    public class ViewHolder extends RecyclerView.ViewHolder /*implements View.OnClickListener*/{
        TextView dishname;
        ImageView dishpic;
        TextView dishDescription;
        TextView dishprice;
        TextView moreInfo;
        ImageView addItem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dishname = (TextView) itemView.findViewById(R.id.foodNameRecipe);
            dishpic = (ImageView) itemView.findViewById(R.id.foodImageRecipe);
            dishDescription = (TextView) itemView.findViewById(R.id.foodDescriptionRecipe);
            dishprice = (TextView) itemView.findViewById(R.id.foodPriceRecipe);
            addItem = itemView.findViewById(R.id.addItem);
            moreInfo = itemView.findViewById(R.id.moreinfo);
            //itemView.findViewById(R.id.moreinfo).setOnClickListener(this);
            //itemView.findViewById(R.id.addItem).setOnClickListener(this);
            moreInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.moreInfoClick(v, getAdapterPosition());
                }
            });
            addItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.addCartClick(v,getAdapterPosition());

                }
            });
        }
        /*
        @Override
        public void onClick(View v) {

            listener.onClick(v, getAdapterPosition());
            Log.d("PININDOT", v);
        }*/
    }
    public DishInfoAdapter (Context context, ArrayList<DishInfo> dishInfos, RecyclerView rv, RecyclerViewClickListener listener){
        this.context = context;
        this.dishInfoArrayList = dishInfos;
        this.recyclerView = rv;
        this.listener = listener;
    }

    @NonNull
    @Override
    public DishInfoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recipeitemrecyclerview, parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DishInfoAdapter.ViewHolder holder, int position) {
        DishInfo dishInfo = dishInfoArrayList.get(position);
        holder.dishname.setText(""+dishInfo.getDishName());
        holder.dishpic.setImageBitmap(dishInfo.getDishImage());
        holder.dishDescription.setText(""+dishInfo.getDishDescription());
        Double price = dishInfo.getDishprice();
        holder.dishprice.setText("₱ "+String.format("%.2f",price));
    }

    @Override
    public int getItemCount() {
        return dishInfoArrayList.size();
    }

    public interface RecyclerViewClickListener {
        void moreInfoClick(View v, int position);
        void addCartClick(View v, int position);

    }
}
