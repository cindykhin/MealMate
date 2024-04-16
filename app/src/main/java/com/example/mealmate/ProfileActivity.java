package com.example.mealmate;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class ProfileActivity extends AppCompatActivity {

    private EditText editTextProfileName, editTextProfileAge, editTextProfileGoal, editTextProfileGender;
    private SharedPreferences sharedPreferences;
    private ActionBarDrawerToggle toggle;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Set up the toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Set up the Navigation Drawer
        drawerLayout = findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        // Set the title text color for the toolbar
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        toolbar.setNavigationIcon(R.drawable.ic_menu_white);


        // Initialize views
        editTextProfileName = findViewById(R.id.editTextProfileName);
        editTextProfileAge = findViewById(R.id.editTextProfileAge);
        editTextProfileGender = findViewById(R.id.editTextProfileGender);
        editTextProfileGoal = findViewById(R.id.editTextProfileGoal);

        // Retrieve data from SharedPreferences
        sharedPreferences = getSharedPreferences("profile_data", MODE_PRIVATE);
        String savedName = sharedPreferences.getString("name", "");
        String savedAge = sharedPreferences.getString("age", "");
        String savedGender = sharedPreferences.getString("gender", "");
        String savedGoal = sharedPreferences.getString("goal", "");

        // Set the retrieved data to the EditText
        editTextProfileName.setText(savedName);
        editTextProfileAge.setText(savedAge);
        editTextProfileGender.setText(savedGender);
        editTextProfileGoal.setText(savedGoal);

        // Set up the NavigationView
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this::onNavigationItemSelected);
        // Inside onCreate() after setting text to the EditText
        editTextProfileName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Not needed
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Not needed
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Save the updated data to SharedPreferences
                String updatedName = s.toString();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("name", updatedName);
                editor.apply();
            }
        });
        editTextProfileAge.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Not needed
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Not needed
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Save the updated age to SharedPreferences
                String updatedAge = s.toString();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("age", updatedAge);
                editor.apply();
            }
        });

        editTextProfileGender.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Not needed
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Not needed
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Save the updated gender to SharedPreferences
                String updatedGender = s.toString();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("gender", updatedGender);
                editor.apply();
            }
        });

        editTextProfileGoal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Not needed
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Not needed
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Save the updated goal to SharedPreferences
                String updatedGoal = s.toString();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("goal", updatedGoal);
                editor.apply();
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    // Override onPostCreate and onConfigurationChanged methods for ActionBarDrawerToggle
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        toggle.syncState();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        toggle.onConfigurationChanged(newConfig);
    }

    private boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation item clicks here
        int itemId = item.getItemId();
        Intent intent = null;
        if (itemId == R.id.nav_home) {
            intent = new Intent(ProfileActivity.this, MainActivity.class);
        } else if (itemId == R.id.nav_profile) {
            intent = new Intent(ProfileActivity.this, ProfileActivity.class);
        } else if (itemId == R.id.nav_scanner){
            intent = new Intent(ProfileActivity.this, ScannerActivity.class);
        }else if (itemId == R.id.nav_list){
            intent = new Intent(ProfileActivity.this, GroceryList.class);
        }

        if (intent != null) {
            startActivity(intent);
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}

