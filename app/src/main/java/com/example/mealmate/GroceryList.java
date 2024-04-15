package com.example.mealmate;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Arrays;

public class GroceryList extends AppCompatActivity {
    private Button clear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery_list);

        clear = findViewById(R.id.clear);

        ArrayList<GroceriesEntry> entries = getGroceryItems();
        RecyclerView recyclerView = findViewById(R.id.recyclerViewGroceries);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new GroceriesEntryAdapter(entries));

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showClearConfirmationDialog(entries, recyclerView);
            }
        });
    }

    private ArrayList<GroceriesEntry> getGroceryItems() {
        ArrayList<GroceriesEntry> entries = new ArrayList<>();
        entries.add(new GroceriesEntry(0, "Potatoes", "1 bag", false));
        entries.add(new GroceriesEntry(1, "Eggs", "1 dozen", false));
        entries.add(new GroceriesEntry(0, "Tomatoes", "10", false));

        return entries;
    }

    private void showClearConfirmationDialog(ArrayList<GroceriesEntry> entries, RecyclerView recyclerView) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to clear checked items from your list?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                for (int i = entries.size() - 1; i >= 0; i--) {
                    if (entries.get(i).isSelected()) {
                        entries.remove(i);
                    }
                }

                recyclerView.getAdapter().notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("No", null);
        builder.show();
    }
}