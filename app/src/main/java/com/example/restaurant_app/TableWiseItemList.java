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
import com.example.restaurant_app.modelmanager.TableWiseOrderItem.Order;
import com.example.restaurant_app.modelmanager.TableWiseOrderItem.TablewiseorderItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TableWiseItemList extends AppCompatActivity {

    String id;
    GridView gridView;

   TablewiseorderItem tablewiseorderItem = new TablewiseorderItem();
   List<com.example.restaurant_app.modelmanager.TableWiseOrderItem.Order> orders = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_wise_item_list);

        id = getIntent().getStringExtra("_id");
        gridView = (GridView)findViewById(R.id.gridview);

        tableitem();

    }

    private void tableitem() {
        Retrofit retrofit = RetrofitClient.getInstance();
        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);

        Call<TablewiseorderItem> call = retrofitInterface.tableitem(id);

        call.enqueue(new Callback<TablewiseorderItem>() {
            @Override
            public void onResponse(Call<TablewiseorderItem> call, Response<TablewiseorderItem> response) {
                if(response.isSuccessful()){

                    tablewiseorderItem = response.body();
                    orders = tablewiseorderItem.getOrder();

                    CustomAdepter customAdepter = new CustomAdepter(TableWiseItemList.this,orders);
                    gridView.setAdapter(customAdepter);

                    Toast.makeText(TableWiseItemList.this, "Succes", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(TableWiseItemList.this, ""+response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TablewiseorderItem> call, Throwable t) {
                Toast.makeText(TableWiseItemList.this, "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    class CustomAdepter extends BaseAdapter {

        List<Order> orders;
        Context context;

        public CustomAdepter(TableWiseItemList tableWiseItemList, List<com.example.restaurant_app.modelmanager.TableWiseOrderItem.Order> orders) {
            this.context = tableWiseItemList;
            this.orders = orders;
        }

        @Override
        public int getCount() {
            return   orders.size();
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
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = LayoutInflater.from(context).inflate(R.layout.viewitemlayout,viewGroup,false);
                LayoutInflater lInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
                view = lInflater.inflate(R.layout.viewitemlayout, null);
            }

            TextView ttotal = view.findViewById(R.id.tprize);
            TextView tname = view.findViewById(R.id.tname);
            TextView tdic = view.findViewById(R.id.tdic);
            TextView tdate = view.findViewById(R.id.tdate);
            ImageView imageView = view.findViewById(R.id.image);

            ttotal.setText(orders.get(i).getItems().get(i).getProductId().getOfferPrice()+""+" ₹");
            tname.setText(orders.get(i).getItems().get(i).getProductId().getName());
            tdic.setText(orders.get(i).getItems().get(i).getProductId().getDescription());
            tdate.setText(orders.get(i).getItems().get(i).getProductId().getCreatedAt());

            Picasso.with(TableWiseItemList.this).load(orders.get(i).getItems().get(i).getProductId().getImageUrl()).into(imageView);

            return view;
        }
    }

}