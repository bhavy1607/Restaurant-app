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

public class waiter_home extends AppCompatActivity {
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waiter_home);


    drawer = findViewById(R.id.waiter_drawer);
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
//                    case R.id.to:
//                        Intent takeorder = new Intent(waiter_home.this, waiter_take_order.class);
//                        startActivity(takeorder);
//                        break;
//                    case R.id.so:
//                        Intent sendorder = new Intent(waiter_home.this, waiter_sent_order.class);
//                        startActivity(sendorder);
//                        break;
//
//                    case R.id.rts:
//                        Intent readytoserve = new Intent(waiter_home.this, waiter_ready_to_serve.class);
//                        startActivity(readytoserve);
//                        break;

                    case R.id.logout:
                        Intent logout = new Intent(waiter_home.this,  MainActivity.class);
                        startActivity(logout);
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
