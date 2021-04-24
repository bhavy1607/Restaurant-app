package com.example.restaurant_app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.restaurant_app.Retrofit.RetrofitClient;
import com.example.restaurant_app.Retrofit.RetrofitInterface;
import com.example.restaurant_app.modelmanager.Order;
import com.example.restaurant_app.modelmanager.Orderdetails;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CookHome extends AppCompatActivity {

    private DrawerLayout drawer;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private ActionBarDrawerToggle toggle;
    GridView gridView;

    Orderdetails orderdetails = new Orderdetails();
    List<Order> orders = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cook_home);

        drawer = findViewById(R.id.cook_drawer);
        navigationView = findViewById(R.id.navigation);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        listingdata();

        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.open, R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        gridView = (GridView)findViewById(R.id.gridview);

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

    private void listingdata(){

        Retrofit retrofit = RetrofitClient.getInstance();
        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);

        Call<Orderdetails> listing = retrofitInterface.Getorder();

        listing.enqueue(new Callback<Orderdetails>() {
            @Override
            public void onResponse(Call<Orderdetails> call, Response<Orderdetails> response) {
                if(response.isSuccessful()){

                    orderdetails = response.body();
                    orders = orderdetails.getOrders();

                    CustomAdepter customAdepter = new CustomAdepter(orders,CookHome.this);
                    gridView.setAdapter(customAdepter);

                    Toast.makeText(getApplicationContext(), "Succes", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(), ""+response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Orderdetails> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    class CustomAdepter extends BaseAdapter {

        List<Order> orders;
        Context context;


        public CustomAdepter(List<Order> orders, CookHome cookHome) {
            this.orders = orders;
            this.context = cookHome;
        }

        @Override
        public int getCount() {
            return orders.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.cookorderlayout,parent,false);
                LayoutInflater lInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
                convertView = lInflater.inflate(R.layout.cookorderlayout, null);
            }

            TextView tname = convertView.findViewById(R.id.tname);
            TextView temail = convertView.findViewById(R.id.temail);
            TextView tpayment = convertView.findViewById(R.id.tpaymentmethod);
            Button btn1 = convertView.findViewById(R.id.accept);
            Button btn2 = convertView.findViewById(R.id.reject);

            tname.setText(orders.get(position).getName());
            temail.setText(orders.get(position).getEmail());
            tpayment.setText(orders.get(position).getPaymentMethod());

            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(),cook_receive_order.class);
                    startActivity(intent);
                    Toast.makeText(context, "order is receive succesfully...", Toast.LENGTH_SHORT).show();
                }
            });
            btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "....", Toast.LENGTH_SHORT).show();
                }
            });

            return convertView;
        }
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
