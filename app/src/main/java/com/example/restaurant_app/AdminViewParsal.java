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

public class AdminViewParsal extends AppCompatActivity {

    GridView gridView;

    Parsalorder parsalorder = new Parsalorder();
    List<Order> orders = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_parsal);

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

                    CustomAdepter customAdepter = new CustomAdepter(AdminViewParsal.this,orders);
                    gridView.setAdapter(customAdepter);

                    Toast.makeText(AdminViewParsal.this, "Succes", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(AdminViewParsal.this, ""+response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Parsalorder> call, Throwable t) {
                Toast.makeText(AdminViewParsal.this, "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }
    class CustomAdepter extends BaseAdapter {

        List<Order> orders;
        Context context;




        public CustomAdepter(AdminViewParsal adminViewParsal, List<Order> orders) {
            this.orders = orders;
            this.context = adminViewParsal;
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
//            CardView cardView = convertView.findViewById(R.id.cardview);
//
//            cardView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(getApplicationContext(),vieworderlist.class);
//                    startActivity(intent);
//                }
//            });

            tname.setText(orders.get(position).getName());
            temail.setText(orders.get(position).getEmail());
            tpayment.setText(orders.get(position).getPaymentMethod());
            ttotal.setText(orders.get(position).getGrandTotal()+"");

            return convertView;
        }
    }

}