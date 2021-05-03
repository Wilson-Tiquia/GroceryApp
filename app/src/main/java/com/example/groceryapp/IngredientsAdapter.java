package com.example.groceryapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.ViewHolder> {
    Context context;
    ArrayList<String> ingredientsList;
    RecyclerView recyclerView;

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView dishingredients;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dishingredients = (TextView) itemView.findViewById(R.id.ingredientsrec);
        }
    }
    public IngredientsAdapter(Context context, ArrayList<String> arrayList, RecyclerView rv){
        this.context = context;
        this.ingredientsList = arrayList;
        this.recyclerView = rv;
    }
    @NonNull
    @Override
    public IngredientsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.ingredientsrecyclerview, parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientsAdapter.ViewHolder holder, int position) {
        String ingList = ingredientsList.get(position);
        holder.dishingredients.setText(ingList);

    }

    @Override
    public int getItemCount() {
        return ingredientsList.size();
    }
}
