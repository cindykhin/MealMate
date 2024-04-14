package com.example.mealmate;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GroceriesEntryAdapter extends RecyclerView.Adapter<GroceriesEntryAdapter.ViewHolder> {
    private List<GroceriesEntry> entries;

    public GroceriesEntryAdapter(List<GroceriesEntry> entries) {
        this.entries = entries;
    }

    @NonNull
    @Override
    public GroceriesEntryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_grocery_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GroceriesEntryAdapter.ViewHolder holder, int position) {
        GroceriesEntry entry = entries.get(position);
        holder.textViewGroceryItemName.setText(entry.getItemList());
        holder.textViewQuantity.setText(entry.getQuantity());
    }

    @Override
    public int getItemCount() {
        return entries.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewGroceryItemName;
        TextView textViewQuantity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewGroceryItemName = itemView.findViewById(R.id.textViewGroceryItemName);
            textViewQuantity = itemView.findViewById(R.id.textViewQuantity);
        }
    }
}
