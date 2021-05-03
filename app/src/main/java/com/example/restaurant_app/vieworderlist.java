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
import com.example.restaurant_app.modelmanager.setdiscount.Item;
import com.example.restaurant_app.modelmanager.setdiscount.Order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class vieworderlist extends AppCompatActivity {

    Button btnoffer;
    EditText editText;
    TextView textView;
    GridView gridView;

    Order order = new Order();
    List<Item> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vieworderlist);

        btnoffer = (Button)findViewById(R.id.btnoffer);
        editText = (EditText)findViewById(R.id.edittext);
        textView = (TextView)findViewById(R.id.txt);

        gridView = (GridView)findViewById(R.id.gridview);

        btnoffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setdiscount();
            }
        });
    }

    private void setdiscount(){
        Retrofit retrofit = RetrofitClient.getInstance();
        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);

        HashMap<String, String> map = new HashMap<>();

        map.put("discount", editText.getText().toString());

        Call<Order> call = retrofitInterface.setdiscount(map);

        call.enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {
                if(response.isSuccessful()){

                    order = response.body();
                    items = order.getItems();

                    CustomAdepter customAdepter = new CustomAdepter(vieworderlist.this,items);
                    gridView.setAdapter(customAdepter);

                    Toast.makeText(vieworderlist.this, "Succes", Toast.LENGTH_SHORT).show();
                }else {

                    Toast.makeText(vieworderlist.this, "", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Order> call, Throwable t) {
                Toast.makeText(vieworderlist.this, "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    class CustomAdepter extends BaseAdapter {

        List<Item> items;
        Context context;



        public CustomAdepter(vieworderlist vieworderlist, List<Item> items) {
            this.context = vieworderlist;
            this.items = items;

        }

        @Override
        public int getCount() {
            return items.size();
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
                convertView = LayoutInflater.from(context).inflate(R.layout.setdiscountitemlayout,parent,false);
                LayoutInflater lInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
                convertView = lInflater.inflate(R.layout.setdiscountitemlayout, null);
            }

            TextView tqty = convertView.findViewById(R.id.tqty);
            TextView tpriority = convertView.findViewById(R.id.tpriority);
            TextView ttotal = convertView.findViewById(R.id.ttotal);
            TextView tid = convertView.findViewById(R.id.tid);
           // CardView cardView = convertView.findViewById(R.id.cardview);

//            cardView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(getApplicationContext(),vieworderlist.class);
//                    startActivity(intent);
//                }
//            });

            tqty.setText(items.get(position).getQty().intValue());
            tpriority.setText(items.get(position).getPriority().intValue());
            ttotal.setText(items.get(position).getTotal().intValue());
            tid.setText(items.get(position).getId());

            return convertView;
        }
    }
}