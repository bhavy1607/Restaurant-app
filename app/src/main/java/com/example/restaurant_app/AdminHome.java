package com.example.restaurant_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class AdminHome extends AppCompatActivity {

    private DrawerLayout drawer;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private ActionBarDrawerToggle toggle;
   // Button btn,btn1,btn2,btn3,btn4,btn5;
    ScrollView scrollView;
    CardView card1,card2,card3,card4,card5,card6,card7;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_home);

        scrollView = (ScrollView)findViewById(R.id.scrollView);
//        btn = (Button)findViewById(R.id.btn);
//        btn1 = (Button)findViewById(R.id.btn1);
//        btn2 = (Button)findViewById(R.id.btn2);
//        btn3 = (Button)findViewById(R.id.btn3);
//        btn4 = (Button)findViewById(R.id.btn4);
//        btn5 = (Button)findViewById(R.id.btn5);
//
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(AdminHome.this,AdminCategories.class);
//                startActivity(intent);
//            }
//        });
//        btn4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(AdminHome.this,Adminbooktable.class);
//                startActivity(intent);
//            }
//        });
//        btn1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(AdminHome.this,AdminShowFeedback.class);
//                startActivity(intent);
//            }
//        });
//        btn2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(AdminHome.this,AdminCookdetails.class);
//                startActivity(intent);
//            }
//        });
//        btn3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(AdminHome.this,Adminshowrevenue.class);
//                startActivity(intent);
//            }
//        });
//        btn5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(AdminHome.this,AdminWaiterdetails.class);
//                startActivity(intent);
//            }
//        });

        card1 = (CardView)findViewById(R.id.card1);
        card2 = (CardView)findViewById(R.id.card2);
        card3 = (CardView)findViewById(R.id.card3);
        card4 = (CardView)findViewById(R.id.card4);
        card5 = (CardView)findViewById(R.id.card5);
        card6 = (CardView)findViewById(R.id.card6);
        card7 = (CardView)findViewById(R.id.card7);
        card7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminHome.this,View_manager.class);
                startActivity(intent);
            }
        });
        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminHome.this,Categories.class);
                startActivity(intent);
            }
        });
        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminHome.this,Book_table.class);
                startActivity(intent);
            }
        });
        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminHome.this,ViewCook.class);
                startActivity(intent);
            }
        });
        card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminHome.this,ViewWaiter.class);
                startActivity(intent);
            }
        });
        card5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminHome.this,Viewrevenue.class);
                startActivity(intent);
            }
        });
        card6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminHome.this,Viewfeedback.class);
                startActivity(intent);
            }
        });

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
                    case R.id.AddManager:
                        Intent addmanager = new Intent(AdminHome.this, AdminAddManager.class);
                        startActivity(addmanager);
                        break;
//                    case R.id.Viewmanager:
//                        Intent intent1 = new Intent(AdminHome.this,View_manager.class);
//                        startActivity(intent1);
//                        break;
//                    case R.id.Sdetails:
//                        Intent sdetails = new Intent(AdminHome.this, AdminStaffDetails.class);
//                        startActivity(sdetails);
//                        break;
                    case R.id.vieworder:
                        Intent cart = new Intent(AdminHome.this, Adminvieworder.class);
                        startActivity(cart);
                        break;
//                    case R.id.Showfeedback:
//                        Intent feedback = new Intent(AdminHome.this, AdminShowFeedback.class);
//                        startActivity(feedback);
//                        break;
                    case R.id.show_menu:
                        Intent intent = new Intent(AdminHome.this,Adminshowmenu.class);
                        startActivity(intent);
                        break;
                    case R.id.showcomplate:
                        Intent intent2 = new Intent(AdminHome.this, AdminShowComplain.class);
                        startActivity(intent2);
                        break;
//                    case R.id.view_revenue:
//                        Intent intent3 = new Intent(AdminHome.this, Adminshowrevenue.class);
//                        startActivity(intent3);
//                        break;
//                    case R.id.categories:
//                        Intent intent4 = new Intent(AdminHome.this, AdminCategories.class);
//                        startActivity(intent4);
//                        break;
                    case R.id.logout:
                        Intent logout = new Intent(AdminHome.this, MainActivity.class);
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