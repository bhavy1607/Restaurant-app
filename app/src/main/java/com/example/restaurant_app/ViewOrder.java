package com.example.restaurant_app;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.restaurant_app.Retrofit.RetrofitClient;
import com.example.restaurant_app.Retrofit.RetrofitInterface;
import com.example.restaurant_app.model.Order;
import com.example.restaurant_app.model.Orderdetails;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ViewOrder extends AppCompatActivity {

    GridView gridView;

    Orderdetails orderdetails = new Orderdetails();
    List<Order> orders = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order);

        gridView = (GridView)findViewById(R.id.gridview);

        listingdata();

    }
    private void listingdata(){

        Retrofit retrofit = RetrofitClient.getInstance();
        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);

        Call<Orderdetails> listing = retrofitInterface.Getorder();

        listing.enqueue(new Callback<Orderdetails>() {
            @Override
            public void onResponse(Call<Orderdetails> call, Response<Orderdetails> response) {

                orderdetails = response.body();
                orders = orderdetails.getOrders();

                CustomAdepter customAdepter = new CustomAdepter(orders,ViewOrder.this);
                gridView.setAdapter(customAdepter);

            }

            @Override
            public void onFailure(Call<Orderdetails> call, Throwable t) {

            }
        });
    }

    class CustomAdepter extends BaseAdapter{

        List<Order> orders;
        Context context;

        public CustomAdepter(List<Order> orders, ViewOrder viewOrder) {
            this.orders = orders;
            this.context = viewOrder;
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
                convertView = LayoutInflater.from(context).inflate(R.layout.orderlayout,parent,false);
                LayoutInflater lInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
                convertView = lInflater.inflate(R.layout.orderlayout, null);
            }

            TextView tname = convertView.findViewById(R.id.tname);
            TextView temail = convertView.findViewById(R.id.temail);
            TextView tphone = convertView.findViewById(R.id.tphone);

            tname.setText(orders.get(position).getName());
            temail.setText(orders.get(position).getEmail());
            tphone.setText(orders.get(position).getPaymentMethod());

            return convertView;
        }
    }
}
