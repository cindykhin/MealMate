package com.example.mealmate;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        //Set up the RecyclerView
        List<CalorieEntry> entries = getCalorieEntriesForDay();
        RecyclerView recyclerView = findViewById(R.id.recyclerViewCalorieEntries);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new CalorieEntryAdapter(entries));
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation item clicks here
        int itemId = item.getItemId();
        Intent intent = null;
        if (itemId == R.id.nav_home) {
            intent = new Intent(MainActivity.this, MainActivity.class);
        } else if (itemId == R.id.nav_profile) {
            intent = new Intent(MainActivity.this, ProfileActivity.class);
        } else if (itemId == R.id.nav_scanner){
            intent = new Intent(MainActivity.this, ScannerActivity.class);
        }else if (itemId == R.id.nav_list){
            intent = new Intent(MainActivity.this, GroceryList.class);
        }else if (itemId == R.id.nav_recipes){
            intent = new Intent(MainActivity.this, RecipeList.class);
        }

        if (intent != null) {
            startActivity(intent);
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private List<CalorieEntry> getCalorieEntriesForDay() {
        // Implement this method to fetch calorie intake entries
        List<CalorieEntry> entries = new ArrayList<>();
        entries.add(new CalorieEntry(0, "Snack Bar", "200"));
        entries.add(new CalorieEntry(1, "Big Mac", "500"));
        entries.add(new CalorieEntry(2, "Coke", "140"));
        return entries;
    }

}
