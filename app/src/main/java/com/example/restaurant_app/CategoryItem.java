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
import com.example.restaurant_app.modelmanager.categoriesitem.Categoryitem;
import com.example.restaurant_app.modelmanager.categoriesitem.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CategoryItem extends AppCompatActivity {

    String id;
    GridView gridView;

    Categoryitem categoryitem = new Categoryitem();
    List<Product> products = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_item);

        gridView = (GridView)findViewById(R.id.gridview);
        id = getIntent().getStringExtra("_id");

        showcategories();
    }

    private void showcategories(){
        Retrofit retrofit = RetrofitClient.getInstance();
        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);

        Call<Categoryitem> call = retrofitInterface.showCategoryItem(id);

        call.enqueue(new Callback<Categoryitem>() {
            @Override
            public void onResponse(Call<Categoryitem> call, Response<Categoryitem> response) {

                if(response.isSuccessful()){

                    categoryitem = response.body();
                    products = categoryitem.getProducts();

                    CustomAdepter customAdepter = new CustomAdepter(CategoryItem.this,products);
                    gridView.setAdapter(customAdepter);


                    Toast.makeText(CategoryItem.this, "Succes", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(CategoryItem.this, ""+response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Categoryitem> call, Throwable t) {
                Toast.makeText(CategoryItem.this, "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    class CustomAdepter extends BaseAdapter {

        List<Product> products;
        Context context;

        public CustomAdepter(CategoryItem categoryItem, List<Product> products) {
            this.context = categoryItem;
            this.products = products;
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
                convertView = LayoutInflater.from(context).inflate(R.layout.viewitemlayout,parent,false);
                LayoutInflater lInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
                convertView = lInflater.inflate(R.layout.viewitemlayout, null);
            }

            TextView ttotal = convertView.findViewById(R.id.tprize);
            TextView tname = convertView.findViewById(R.id.tname);
            TextView tdic = convertView.findViewById(R.id.tdic);
            TextView tdate = convertView.findViewById(R.id.tdate);
            ImageView imageView = convertView.findViewById(R.id.image);

            ttotal.setText(products.get(position).getOfferPrice()+""+" ₹");
            tname.setText(products.get(position).getName());
            tdic.setText(products.get(position).getDescription());
            tdate.setText(products.get(position).getCreatedAt());

            Picasso.with(CategoryItem.this).load(products.get(position).getImageUrl()).into(imageView);

            return convertView;
        }
    }

}