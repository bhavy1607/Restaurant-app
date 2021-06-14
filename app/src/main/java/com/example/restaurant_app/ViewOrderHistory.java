package com.example.restaurant_app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.restaurant_app.Retrofit.RetrofitClient;
import com.example.restaurant_app.Retrofit.RetrofitInterface;
import com.example.restaurant_app.modelmanager.gettingorder.Order;
import com.example.restaurant_app.modelmanager.gettingorder.Orderdetails;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ViewOrderHistory extends AppCompatActivity {

    GridView gridView;
    Toolbar toolbar;
    public static String id;

    Orderdetails orderdetails = new Orderdetails();
    List<Order> orders = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order_history);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        gridView = (GridView) findViewById(R.id.gridview);

        listingdata();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.parsal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.parsal:
                Intent intent = new Intent(ViewOrderHistory.this, ViewParsal.class);
                startActivity(intent);
                break;
        }
        return true;
    }

    private void listingdata() {
        Retrofit retrofit = RetrofitClient.getInstance();
        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);

        Call<Orderdetails> call = retrofitInterface.Getorder();

        call.enqueue(new Callback<Orderdetails>() {
            @Override
            public void onResponse(Call<Orderdetails> call, Response<Orderdetails> response) {
                if (response.isSuccessful()) {

                    orderdetails = response.body();
                    orders = orderdetails.getOrders();

                    CustomAdepter customAdepter = new CustomAdepter(ViewOrderHistory.this,orders);
                    gridView.setAdapter(customAdepter);


                    Toast.makeText(ViewOrderHistory.this, "Succes", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ViewOrderHistory.this, +response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Orderdetails> call, Throwable t) {
                Toast.makeText(ViewOrderHistory.this, "Failure"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

class CustomAdepter extends BaseAdapter {

            List<Order> orders;
            Context context;

            public CustomAdepter(ViewOrderHistory viewOrderHistory, List<Order> orders) {
                this.orders = orders;
                this.context = viewOrderHistory;
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
                    convertView = LayoutInflater.from(context).inflate(R.layout.orderlayout, parent, false);
                    LayoutInflater lInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
                    convertView = lInflater.inflate(R.layout.orderlayout, null);
                }

                TextView ttotal = convertView.findViewById(R.id.total);
                TextView tname = convertView.findViewById(R.id.tname);
                TextView temail = convertView.findViewById(R.id.temail);
                TextView tdate = convertView.findViewById(R.id.tpaymentmethod);
                TextView tstatus = convertView.findViewById(R.id.tstatus);
                Button btn1 = convertView.findViewById(R.id.btn1);

                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String get = orders.get(position).getId();
                        Intent intent = new Intent(ViewOrderHistory.this, vieworderItemList.class);
                        intent.putExtra("_id", orders.get(position).getId());
                        startActivity(intent);
                    }
                });
                ttotal.setText(orders.get(position).getGrandTotal()+""+"₹");
                tname.setText(orders.get(position).getName());
//                temail.setText(orders.get(position).getEmail());
                tdate.setText(orders.get(position).getCreatedAt());
                tstatus.setText(orders.get(position).getPaymentStatus());

//                ttotal.setText(orders.get(position).getGrandTotal() + "");
//                tname.setText(orders.get(position).getName());
////                temail.setText(orders.get(position).getEmail());
//                tdate.setText(orders.get(position).getCreatedAt());
//                tstatus.setText(orders.get(position).getPaymentStatus());

                return convertView;
            }
        }
    }
