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
import android.widget.ImageView;
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

public class Adminshowmenu extends AppCompatActivity {

    GridView gridView;

    Menudetails menudetails = new Menudetails();
    List<Product> products = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminshowmenu);

        gridView = (GridView)findViewById(R.id.gridview);

        showmenu();
    }

    private void showmenu(){
        Retrofit retrofit = RetrofitClient.getInstance();
        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);

        Call<Menudetails> call = retrofitInterface.Getshowmenu();

        call.enqueue(new Callback<Menudetails>() {
            @Override
            public void onResponse(Call<Menudetails> call, Response<Menudetails> response) {
                if(response.isSuccessful()){

                    menudetails = response.body();
                    products = menudetails.getProducts();

                    CustomAdepter customAdepter = new CustomAdepter(products,Adminshowmenu.this);
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

        public CustomAdepter(List<Product> products, Adminshowmenu adminshowmenu) {
            this.products = products;
            this.context = adminshowmenu;
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
                convertView = LayoutInflater.from(context).inflate(R.layout.showmenu,parent,false);
                LayoutInflater lInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
                convertView = lInflater.inflate(R.layout.showmenu, null);
            }

            TextView t1 = convertView.findViewById(R.id.textview);
            TextView t2 = convertView.findViewById(R.id.txtprice);
            ImageView imageView = convertView.findViewById(R.id.image);
            Button btn = convertView.findViewById(R.id.btnadd);

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Adminshowmenu.this,Viewingrediant.class);
                    startActivity(intent);
                }
            });

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String get = products.get(position).getId();
                    Intent intent = new Intent(Adminshowmenu.this,clickeditem.class);
                    intent.putExtra("_id",products.get(position).getId());
                    startActivity(intent);
                }
            });

            t1.setText(products.get(position).getName());
            t2.setText(products.get(position).getOriginalPrice()+""+" ₹");

          //  Picasso.with(Adminshowmenu.this).load(products.get(position).getImageUrl()).into(imageView);

            return convertView;
        }
    }
}