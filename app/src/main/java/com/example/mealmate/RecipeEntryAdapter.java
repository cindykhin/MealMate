package com.example.mealmate;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
public class RecipeEntryAdapter extends RecyclerView.Adapter<RecipeEntryAdapter.ViewHolder> {
    private List<RecipeEntry> entries;

    public RecipeEntryAdapter(List<RecipeEntry> entries) {
        this.entries = entries;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_recipe_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RecipeEntry entry = entries.get(position);
        holder.Recipe.setText(entry.getItemList());
        holder.Recipe.setTextColor(Color.BLACK);
    }

    @Override
    public int getItemCount() {
        return entries.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView Recipe;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Recipe = itemView.findViewById(R.id.Recipe);

            Recipe.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = itemView.getContext();
                    Intent intent = new Intent(context, ChocolateChipCookies.class);
                    context.startActivity(intent);
                }
            });
        }
    }
}
