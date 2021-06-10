package com.example.restaurant_app;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.restaurant_app.Retrofit.RetrofitClient;
import com.example.restaurant_app.Retrofit.RetrofitInterface;
import com.example.restaurant_app.modelmanager.getOrderItem.Order;
import com.example.restaurant_app.modelmanager.getOrderItem.OrderItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ParsalItem extends AppCompatActivity {

    String id;
    GridView gridView;

    OrderItem orderItem = new OrderItem();
    List<com.example.restaurant_app.modelmanager.getOrderItem.Order> orders = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parsal_item);

        id = getIntent().getStringExtra("_id");
        gridView = (GridView)findViewById(R.id.gridview);

        parsalitem();
    }

    private void parsalitem(){
        Retrofit retrofit = RetrofitClient.getInstance();
        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);

        Call<OrderItem> listing = retrofitInterface.parsalitem(id);

        listing.enqueue(new Callback<OrderItem>() {
            @Override
            public void onResponse(Call<OrderItem> call, Response<OrderItem> response) {
                if(response.isSuccessful()){

                    orderItem = response.body();
                    orders = orderItem.getOrder();

                    CustomAdepter customAdepter = new CustomAdepter(ParsalItem.this,orders);
                    gridView.setAdapter(customAdepter);


                    Toast.makeText(ParsalItem.this, "Succes", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(ParsalItem.this, ""+response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<OrderItem> call, Throwable t) {
                Toast.makeText(ParsalItem.this, "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    class CustomAdepter extends BaseAdapter {

        List<com.example.restaurant_app.modelmanager.getOrderItem.Order> orders;
        Context context;


        public CustomAdepter(ParsalItem parsalItem, List<Order> orders) {
            this.orders = orders;
            this.context = parsalItem;
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
                convertView = LayoutInflater.from(context).inflate(R.layout.viewitemlayout,parent,false);
                LayoutInflater lInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
                convertView = lInflater.inflate(R.layout.viewitemlayout, null);
            }

            TextView ttotal = convertView.findViewById(R.id.tprize);
            TextView tname = convertView.findViewById(R.id.tname);
            TextView tdic = convertView.findViewById(R.id.tdic);
            TextView tdate = convertView.findViewById(R.id.tdate);
            ImageView imageView = convertView.findViewById(R.id.image);

            ttotal.setText(orders.get(position).getItems().get(position).getProductId().getOfferPrice()+""+" ₹");
            tname.setText(orders.get(position).getItems().get(position).getProductId().getName());
            tdic.setText(orders.get(position).getItems().get(position).getProductId().getDescription());
            tdate.setText(orders.get(position).getItems().get(position).getProductId().getCreatedAt());

            Picasso.with(ParsalItem.this).load(orders.get(position).getItems().get(position).getProductId().getImageUrl()).into(imageView);

            return convertView;
        }
    }
}
