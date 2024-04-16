package com.example.mealmate;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GroceriesEntryAdapter extends RecyclerView.Adapter<GroceriesEntryAdapter.ViewHolder> {
    public interface OnSelectedItemListener {
        void onItemSelected(boolean anyItemSelected);
    }
    private ArrayList<GroceriesEntry> entries;
    private OnSelectedItemListener listener;
    public GroceriesEntryAdapter(ArrayList<GroceriesEntry> entries, OnSelectedItemListener listener) {
        this.entries = entries;
        this.listener = listener;
    }

    @NonNull
    @Override
    public GroceriesEntryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_grocery_items, parent, false);
        return new ViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull GroceriesEntryAdapter.ViewHolder holder, int position) {
        GroceriesEntry entry = entries.get(position);
        holder.checkBox.setText(entry.getItemList());
        holder.checkBox.setChecked(entry.isSelected());
        holder.textViewQuantity.setText(entry.getQuantity());
    }

    @Override
    public int getItemCount() {
        return entries.size();
    }

    public void notifySelectionChanged() {
        if (listener != null) {
            listener.onItemSelected(isAnyItemSelected());
        }
    }

    public boolean isAnyItemSelected() {
        for (GroceriesEntry entry : entries) {
            if (entry.isSelected()) {
                return true;
            }
        }
        return false;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewQuantity;
        CheckBox checkBox;
        GroceriesEntryAdapter adapter;

        public ViewHolder(@NonNull View itemView, GroceriesEntryAdapter adapter) {
            super(itemView);
            this.adapter = adapter;
            checkBox = itemView.findViewById(R.id.checkBox);
            textViewQuantity = itemView.findViewById(R.id.textViewQuantity);

            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            adapter.entries.get(position).setSelected(true);
                            checkBox.setTextColor(Color.GRAY);
                            textViewQuantity.setTextColor(Color.GRAY);
                        }
                    } else {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            adapter.entries.get(position).setSelected(false);
                            checkBox.setTextColor(Color.BLACK);
                            textViewQuantity.setTextColor(Color.BLACK);
                        }
                    }

                    adapter.notifySelectionChanged();
                }
            });
        }
    }
}
