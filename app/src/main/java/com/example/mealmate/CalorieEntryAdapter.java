package com.example.mealmate;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CalorieEntryAdapter extends RecyclerView.Adapter<CalorieEntryAdapter.ViewHolder> {

    private List<CalorieEntry> entries;

    public CalorieEntryAdapter(List<CalorieEntry> entries) {
        this.entries = entries;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_calorie_entry, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CalorieEntry entry = entries.get(position);
        holder.itemNameTextView.setText(entry.getItemName());
        holder.calorieCountTextView.setText(entry.getCalories() + " Calories");
    }

    @Override
    public int getItemCount() {
        return entries.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemNameTextView;
        TextView calorieCountTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemNameTextView = itemView.findViewById(R.id.textViewItemName);
            calorieCountTextView = itemView.findViewById(R.id.textViewCalorieCount);
        }
    }
}


