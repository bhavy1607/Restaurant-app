package com.example.restaurant_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class ManagerHome extends AppCompatActivity {
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager_home);

        drawer = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigation);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.open, R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawer.closeDrawer(GravityCompat.START);
                switch (item.getItemId()) {
                    case R.id.home:
                        break;
                    case R.id.add_cook:
                        Intent add_cook = new Intent(ManagerHome.this, AddCook.class);
                        startActivity(add_cook);
                        break;
                    case R.id.view_cook:
                        Intent view_cook = new Intent(ManagerHome.this, ViewCook.class);
                        startActivity(view_cook);
                        break;
                    case R.id.add_waiter:
                        Intent add_waiter = new Intent(ManagerHome.this, AddWaiter.class);
                        startActivity(add_waiter);
                        break;
                    case R.id.view_waiter:
                        Intent view_waiter = new Intent(ManagerHome.this, ViewWaiter.class);
                        startActivity(view_waiter);
                        break;
                    case R.id.view_order:
                        Intent view_order = new Intent(ManagerHome.this,ViewOrder.class);
                        startActivity(view_order);
                        break;
                    case R.id.view_revenue:
                        Intent intent = new Intent(ManagerHome.this,Viewrevenue.class);
                        startActivity(intent);
                        break;
                    case R.id.manage_menu:
                        Intent manage_menu = new Intent(ManagerHome.this,Manage_menu.class);
                        startActivity(manage_menu);
                        break;
                    case R.id.view_feedback:
                        Intent view_payment = new Intent(ManagerHome.this, Viewfeedback.class);
                        startActivity(view_payment);
                        break;
                    case R.id.booktable:
                        Intent booktable = new Intent(ManagerHome.this,Book_table.class);
                        startActivity(booktable);
                        break;
                    case R.id.manage_complate:
                        Intent manage_complate = new Intent(ManagerHome.this, Manage_complain.class);
                        startActivity(manage_complate);
                        break;
                    case R.id.logout:
                        SharedPreferences preferences = getSharedPreferences("checked",MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("remember","false");
                        editor.apply();
                        finish();
                        break;

                }
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}

