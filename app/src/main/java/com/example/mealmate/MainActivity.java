package com.example.mealmate;

import android.os.Bundle;
import android.view.MenuItem;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up the toolbar
        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        // Set up the Navigation Drawer
        //DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        //ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
        //        this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        //drawerLayout.addDrawerListener(toggle);
        //toggle.syncState();

        // Set up the NavigationView
        //NavigationView navigationView = findViewById(R.id.nav_view);
        //navigationView.setNavigationItemSelectedListener(this::onNavigationItemSelected);

        // Set up the RecyclerView
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
        switch (item.getItemId()) {
            //case R.id.nav_home:
                // Navigate to Home activity
                //break;
            //case R.id.nav_profile:
                // Navigate to Profile activity
                //break;
            // Add more cases for other destinations
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
