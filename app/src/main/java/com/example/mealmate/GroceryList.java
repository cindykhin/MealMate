package com.example.mealmate;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class GroceryList extends AppCompatActivity implements GroceriesEntryAdapter.OnSelectedItemListener {
    private Button clear;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private ArrayList<GroceriesEntry> entries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery_list);

        clear = findViewById(R.id.clear);

        Intent intent = getIntent();

        // If items are being added from a recipe
        if (intent.hasExtra("items") && intent.hasExtra("qty")) {
            // Load previous items and add new items
            Bundle extras = intent.getExtras();
            loadGroceryItems();
            ArrayList<String> item = extras.getStringArrayList("items");
            ArrayList<String> qty = extras.getStringArrayList("qty");

            entries = getGroceryItemsCookies(item, qty);
        } else {
            // Load items
            loadGroceryItems();
        }

        RecyclerView recyclerView = findViewById(R.id.recyclerViewGroceries);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new GroceriesEntryAdapter(entries, this));

        // Set up the toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Set up the Navigation Drawer
        drawerLayout = findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        // Set the title text color for the toolbar
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        toolbar.setNavigationIcon(R.drawable.ic_menu_white);

        // Set up the NavigationView
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this::onNavigationItemSelected);

        clear.setEnabled(false);

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showClearConfirmationDialog(entries, recyclerView);
            }
        });
    }

    // Load grocery list items from SharedPreferences
    private void loadGroceryItems() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("groceryItems", null);
        Type type = new TypeToken<ArrayList<GroceriesEntry>>() {}.getType();
        entries = gson.fromJson(json, type);

        if (entries == null) {
            entries = new ArrayList<>();
        }
    }

    // Save grocery list items to SharedPreferences
    private void saveGroceryItems() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(entries);
        editor.putString("groceryItems", json);
        editor.apply();
    }

    // Add grocery items from recipe
    private ArrayList<GroceriesEntry> getGroceryItemsCookies(ArrayList<String> item, ArrayList<String> qty) {
        ArrayList<GroceriesEntry> entries = new ArrayList<>();

        if (item.isEmpty() == false && qty.isEmpty() == false) {
            int listSize = entries.size();

            for (int i = 0; i < item.size(); i++) {
                entries.add(new GroceriesEntry(listSize, item.get(i), qty.get(i), false));
                listSize++;
            }
        }

        return entries;
    }

    @Override
    public void onItemSelected(boolean anyItemSelected) {
        clear.setEnabled(anyItemSelected);
    }

    // Ask to confirm checked items
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
                // Save the updated list
                saveGroceryItems();
                recyclerView.getAdapter().notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("No", null);
        builder.show();
    }

    private boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation item clicks here
        int itemId = item.getItemId();
        Intent intent = null;
        if (itemId == R.id.nav_home) {
            intent = new Intent(GroceryList.this, MainActivity.class);
        } else if (itemId == R.id.nav_profile) {
            intent = new Intent(GroceryList.this, ProfileActivity.class);
        } else if (itemId == R.id.nav_scanner) {
            intent = new Intent(GroceryList.this, ScannerActivity.class);
        } else if (itemId == R.id.nav_list) {
            intent = new Intent(GroceryList.this, GroceryList.class);
        } else if (itemId == R.id.nav_recipes) {
            intent = new Intent(GroceryList.this, RecipeList.class);
        }

        if (intent != null) {
            startActivity(intent);
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveGroceryItems();
    }
}
