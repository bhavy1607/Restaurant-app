package com.example.restaurant_app;

import android.content.Intent;
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

public class CookHome extends AppCompatActivity {

    private DrawerLayout drawer;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cook_home);

        drawer = findViewById(R.id.cook_drawer);
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
                    case R.id.us:
                        Intent updatestock = new Intent(CookHome.this, cook_update_stock.class);
                        startActivity(updatestock);
                        break;
                    case R.id.ro:
                        Intent receiveorder = new Intent(CookHome.this, cook_receive_order.class);
                        startActivity(receiveorder);
                        break;
                    case R.id.po:
                        Intent pendingorder = new Intent(CookHome.this, cook_pending_order.class);
                        startActivity(pendingorder);
                        break;
                    case R.id.co:
                        Intent completeorder = new Intent(CookHome.this, cook_complete_order.class);
                        startActivity(completeorder);
                        break;
                    case R.id.rfs:
                        Intent readyforserve = new Intent(CookHome.this, cook_redy_for_serve.class);
                        startActivity(readyforserve);
                        break;

                    case R.id.logout:
                        Intent logout = new Intent(CookHome.this,  MainActivity.class);
                        startActivity(logout);
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
