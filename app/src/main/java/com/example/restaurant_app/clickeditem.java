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
import com.example.restaurant_app.modelmanager.getmenu.Menudetails;
import com.example.restaurant_app.modelmanager.getmenu.Product;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class clickeditem extends AppCompatActivity {

    GridView gridView;
    Menudetails menudetails = new Menudetails();
    List<Product> products = new ArrayList<>();
    String id;
    Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clickeditem);

        btn1 = (Button)findViewById(R.id.btn1);
        gridView = (GridView)findViewById(R.id.gridview);
        id = getIntent().getStringExtra("_id");

        clickeddata();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(clickeditem.this,Itemavailable.class);
                startActivity(intent);
            }
        });
    }

    private void clickeddata(){

        Retrofit retrofit = RetrofitClient.getInstance();
        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);

        Call<Menudetails> call = retrofitInterface.Getshowmenu();

        call.enqueue(new Callback<Menudetails>() {
            @Override
            public void onResponse(Call<Menudetails> call, Response<Menudetails> response) {
                if(response.isSuccessful()){

                    menudetails = response.body();
                    products = menudetails.getProducts();

                    CustomAdepter customAdepter = new CustomAdepter(products,clickeditem.this);
                    gridView.setAdapter(customAdepter);

                    Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(), ""+response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Menudetails> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }
    class CustomAdepter extends BaseAdapter {

        List<Product> products;
        Context context;

        public CustomAdepter(List<Product> products, clickeditem clickeditem) {
            this.products = products;
            this.context = clickeditem;
        }

        @Override
        public int getCount() {
            return products.size();
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
                convertView = LayoutInflater.from(context).inflate(R.layout.clickeditemlayout,parent,false);
                LayoutInflater lInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
                convertView = lInflater.inflate(R.layout.clickeditemlayout, null);
            }

//            CardView cardView = convertView.findViewById(R.id.cardview);
//            cardView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    String get = products.get(position).getId();
//                    Intent intent = new Intent(clickeditem.this, Itemavailable.class);
//                    intent.putExtra("_id",products.get(position).getId());
//                    startActivity(intent);
//                }
//            });


            TextView tname = convertView.findViewById(R.id.tname);
            TextView tdec = convertView.findViewById(R.id.tdec);
           // TextView tprice = convertView.findViewById(R.id.price);
            TextView tid = convertView.findViewById(R.id.id);

            tname.setText(products.get(position).getName());
            tdec.setText(products.get(position).getDescription());
        //    tprice.setText(products.get(position).getPrice());
            tid.setText(products.get(position).getId());

            return convertView;
        }
    }

}