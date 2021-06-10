package com.example.restaurant_app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.restaurant_app.Retrofit.RetrofitClient;
import com.example.restaurant_app.Retrofit.RetrofitInterface;
import com.example.restaurant_app.modelmanager.parsal.Order;
import com.example.restaurant_app.modelmanager.parsal.Parsalorder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ViewParsal extends AppCompatActivity {

    GridView gridView;
    public static String id;

    Parsalorder parsalorder = new Parsalorder();
    List<Order> orders = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_parsal);

        gridView = (GridView)findViewById(R.id.gridview);

        parsaldata();

    }
    private void parsaldata(){

        Retrofit retrofit = RetrofitClient.getInstance();
        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);

        Call<Parsalorder> listing = retrofitInterface.Showparsal();

        listing.enqueue(new Callback<Parsalorder>() {
            @Override
            public void onResponse(Call<Parsalorder> call, Response<Parsalorder> response) {
                if(response.isSuccessful()){

                    parsalorder = response.body();
                    orders = parsalorder.getOrders();

                    CustomAdepter customAdepter = new CustomAdepter(ViewParsal.this,orders);
                    gridView.setAdapter(customAdepter);

                    Toast.makeText(ViewParsal.this, "Succes", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(ViewParsal.this, ""+response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Parsalorder> call, Throwable t) {
                Toast.makeText(ViewParsal.this, "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }
    class CustomAdepter extends BaseAdapter {

        List<Order> orders;
        Context context;

        public CustomAdepter(ViewParsal viewParsal, List<Order> orders) {
            this.orders = orders;
            this.context = viewParsal;
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
                convertView = LayoutInflater.from(context).inflate(R.layout.parsallayut,parent,false);
                LayoutInflater lInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
                convertView = lInflater.inflate(R.layout.parsallayut, null);
            }

            TextView tname = convertView.findViewById(R.id.tname);
            TextView temail = convertView.findViewById(R.id.temail);
            TextView tpayment = convertView.findViewById(R.id.tpaymentmethod);
            TextView ttotal = convertView.findViewById(R.id.total);
            Button btn = convertView.findViewById(R.id.btn);

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String get = orders.get(position).getId();
                    Intent intent = new Intent(ViewParsal.this, ParsalItem.class);
                    intent.putExtra("_id",orders.get(position).getId());
                    startActivity(intent);
                }
            });

            tname.setText(orders.get(position).getName());
            temail.setText(orders.get(position).getEmail());
            tpayment.setText(orders.get(position).getPaymentMethod());
            ttotal.setText(orders.get(position).getGrandTotal()+"");

            return convertView;
        }
    }
//    private void parsalitem(){
//
//        Retrofit retrofit = RetrofitClient.getInstance();
//        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);
//
//        Call<Parsalorder> listing = retrofitInterface.Showparsal();
//
//        listing.enqueue(new Callback<Parsalorder>() {
//            @Override
//            public void onResponse(Call<Parsalorder> call, Response<Parsalorder> response) {
//                if(response.isSuccessful()){
//
//                    parsalorder = response.body();
//                    orders = parsalorder.getOrders();
//                    items = orders.get(response.code()).getItems();
//
//                    CustomAdepter customAdepter = new CustomAdepter(ViewParsal.this,items);
//                    gridView.setAdapter(customAdepter);
//
//                    Toast.makeText(ViewParsal.this, "Succes", Toast.LENGTH_SHORT).show();
//                }else {
//                    Toast.makeText(ViewParsal.this, ""+response.code(), Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Parsalorder> call, Throwable t) {
//                Toast.makeText(ViewParsal.this, "Failure", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

//    class CustomAdepter extends BaseAdapter {
//
//        List<Item> items;
//        Context context;
//
//
//        public CustomAdepter(ViewParsal viewParsal, List<Item> items) {
//            this.items = items;
//            this.context = viewParsal;
//        }
//
//        @Override
//        public int getCount() {
//            return items.size();
//        }
//
//        @Override
//        public Object getItem(int position) {
//            return null;
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return 0;
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//
//            if (convertView == null) {
//                convertView = LayoutInflater.from(context).inflate(R.layout.parsallayut,parent,false);
//                LayoutInflater lInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
//                convertView = lInflater.inflate(R.layout.parsallayut, null);
//            }
//
//            TextView tname = convertView.findViewById(R.id.tname);
//            TextView temail = convertView.findViewById(R.id.temail);
//            TextView tpayment = convertView.findViewById(R.id.tpaymentmethod);
//            TextView ttotal = convertView.findViewById(R.id.total);
//            CardView cardView = convertView.findViewById(R.id.cardview);
//
//            cardView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    parsalitem();
//                }
//            });
//
//            tname.setText(items.get(position).getName());
//            temail.setText(items.get(position).getEmail());
//            tpayment.setText(items.get(position).getPaymentMethod());
//            ttotal.setText(items.get(position).getGrandTotal()+"");
//
//            return convertView;
//        }
//    }

}