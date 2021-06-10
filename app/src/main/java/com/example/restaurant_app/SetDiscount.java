package com.example.restaurant_app;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.restaurant_app.Retrofit.RetrofitClient;
import com.example.restaurant_app.Retrofit.RetrofitInterface;
import com.example.restaurant_app.modelmanager.setdiscount.BodySetDiscount;
import com.example.restaurant_app.modelmanager.setdiscount.Order;
import com.example.restaurant_app.modelmanager.setdiscount.Setdiscount;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SetDiscount extends AppCompatActivity {

    String id;
    Button btn;
    EditText et;
    GridView gridView;

    Setdiscount setdiscount = new Setdiscount();
    List<Order> orders = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_discount);

        gridView = (GridView)findViewById(R.id.gridview);
        id = getIntent().getStringExtra("_id");
        btn = (Button)findViewById(R.id.btn);
        et = (EditText)findViewById(R.id.et);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                complainSetDiscount();
            }
        });
    }

    private void complainSetDiscount(){
        Retrofit retrofit = RetrofitClient.getInstance();
        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);

        String message = et.getText().toString();

        BodySetDiscount bodySetDiscount = new BodySetDiscount();
        bodySetDiscount.setDiscount(message);

        Call<Setdiscount> call = retrofitInterface.SetDiscount(id,bodySetDiscount);

        call.enqueue(new Callback<Setdiscount>() {
            @Override
            public void onResponse(Call<Setdiscount> call, Response<Setdiscount> response) {
                if(response.isSuccessful()){

                    setdiscount = response.body();
                    orders = setdiscount.getOrder();

                    CustomAdepter customAdepter = new CustomAdepter(SetDiscount.this,orders);
                    gridView.setAdapter(customAdepter);

                    Toast.makeText(getApplicationContext(), "discount set...", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(), ""+response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Setdiscount> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    class CustomAdepter extends BaseAdapter {

        List<Order> orders;
        Context context;



        public CustomAdepter(SetDiscount setDiscount, List<Order> orders) {
            this.orders = orders;
            this.context = setDiscount;
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
                convertView = LayoutInflater.from(context).inflate(R.layout.setdiscountorderlayout,parent,false);
                LayoutInflater lInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
                convertView = lInflater.inflate(R.layout.setdiscountorderlayout, null);
            }

            TextView ttotal = convertView.findViewById(R.id.total);
            TextView tname = convertView.findViewById(R.id.tname);
            TextView temail = convertView.findViewById(R.id.temail);
            TextView tdate = convertView.findViewById(R.id.tpaymentmethod);
            TextView tstatus = convertView.findViewById(R.id.tstatus);


            ttotal.setText(orders.get(position).getGrandTotal()+"");
            tname.setText(orders.get(position).getName());
            temail.setText(orders.get(position).getEmail());
            tdate.setText(orders.get(position).getCreatedAt());
            tstatus.setText(orders.get(position).getPaymentStatus());

            return convertView;
        }
    }
}
