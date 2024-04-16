package com.example.mealmate;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class ChocolateChipCookies extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chocolate_chip_cookies);

        button = findViewById(R.id.button);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Set up the Navigation Drawer
        drawerLayout = findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        toolbar.setNavigationIcon(R.drawable.ic_menu_white);

        // Set up the NavigationView
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this::onNavigationItemSelected);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ChocolateChipCookies.this);
                builder.setMessage("Are you sure you want to add these ingredients to your list?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Bundle extras = new Bundle();

                        ArrayList<String> items = new ArrayList<>();
                        items.add("Unsalted Butter");
                        items.add("Granulated Sugar");
                        items.add("Brown Sugar");
                        items.add("Large Eggs");
                        items.add("Vanilla Extract");
                        items.add("All-purpose Flour");
                        items.add("Baking Soda");
                        items.add("Salt");
                        items.add("Chocolate Chips");

                        ArrayList<String> qty = new ArrayList<>();
                        qty.add("2 sticks");
                        qty.add("1 bag");
                        qty.add("1 bag");
                        qty.add("1 dozen");
                        qty.add("1 bottle");
                        qty.add("1 bag");
                        qty.add("1 box");
                        qty.add("1 box");
                        qty.add("1 bag");

                        extras.putStringArrayList("items", items);
                        extras.putStringArrayList("qty", qty);

                        Intent intent = new Intent(ChocolateChipCookies.this, GroceryList.class);
                        intent.putExtras(extras);

                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("No", null);
                builder.show();
            }
        });
    }

    private boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation item clicks here
        int itemId = item.getItemId();
        Intent intent = null;
        if (itemId == R.id.nav_home) {
            intent = new Intent(ChocolateChipCookies.this, MainActivity.class);
        } else if (itemId == R.id.nav_profile) {
            intent = new Intent(ChocolateChipCookies.this, ProfileActivity.class);
        } else if (itemId == R.id.nav_scanner){
            intent = new Intent(ChocolateChipCookies.this, ScannerActivity.class);
        }else if (itemId == R.id.nav_list){
            intent = new Intent(ChocolateChipCookies.this, GroceryList.class);
        }else if (itemId == R.id.nav_recipes){
            intent = new Intent(ChocolateChipCookies.this, RecipeList.class);
        }

        if (intent != null) {
            startActivity(intent);
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}