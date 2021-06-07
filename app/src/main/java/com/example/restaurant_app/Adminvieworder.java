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

public class Adminvieworder extends AppCompatActivity {

    GridView gridView;
    Toolbar toolbar;


    Orderdetails orderdetails = new Orderdetails();
    List<Order> orders = new ArrayList<>();

    // Button backbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_view_orderhistory);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        gridView = (GridView)findViewById(R.id.gridview);

        listingdata();

//        backbtn = (Button)findViewById(R.id.btnback);
//        backbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Adminvieworder.this, AdminHome.class);
//                startActivity(intent);
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.parsal,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.parsal:
                Intent intent = new Intent(Adminvieworder.this, AdminViewParsal.class);
                startActivity(intent);
                break;
        }
        return true;
    }

    private void listingdata(){
        Retrofit retrofit = RetrofitClient.getInstance();
        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);

        Call<Orderdetails> call = retrofitInterface.Getorder();

        call.enqueue(new Callback<Orderdetails>() {
            @Override
            public void onResponse(Call<Orderdetails> call, Response<Orderdetails> response) {
                if(response.isSuccessful()){

                    orderdetails = response.body();
                    orders = orderdetails.getOrders();

                    CustomAdepter customAdepter = new CustomAdepter(orders,Adminvieworder.this);
                    gridView.setAdapter(customAdepter);

                    Toast.makeText(Adminvieworder.this, "Succes", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(Adminvieworder.this, ""+response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Orderdetails> call, Throwable t) {
                Toast.makeText(Adminvieworder.this, "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    class CustomAdepter extends BaseAdapter {

        List<Order> orders;
        Context context;

        public CustomAdepter(List<Order> orders, Adminvieworder adminvieworder) {
            this.orders = orders;
            this.context = adminvieworder;
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
            TextView tpayment = convertView.findViewById(R.id.tpaymentmethod);

            tname.setText(orders.get(position).getName());
            temail.setText(orders.get(position).getEmail());
            tpayment.setText(orders.get(position).getPaymentMethod());

            return convertView;
        }
    }
}