package com.example.groceryapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class InstructionsAdapter extends RecyclerView.Adapter<InstructionsAdapter.ViewHolder>{
    Context context;
    ArrayList<String> instructionsList;
    RecyclerView recyclerView;

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView instructions;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            instructions = itemView.findViewById(R.id.instructionsrecycler);
        }
    }

    public InstructionsAdapter (Context context, ArrayList<String> instructionsList, RecyclerView rv){
        this.context = context;
        this.instructionsList = instructionsList;
        this.recyclerView = rv;
    }


    @NonNull
    @Override
    public InstructionsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.instructionsrecyclerview, parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull InstructionsAdapter.ViewHolder holder, int position) {
        String instructionsStr = instructionsList.get(position);
        holder.instructions.setText(instructionsStr);

    }

    @Override
    public int getItemCount() {
        return instructionsList.size();
    }
}
