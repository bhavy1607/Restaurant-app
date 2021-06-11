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
import com.example.restaurant_app.modelmanager.TableWiseOrder.Order;
import com.example.restaurant_app.modelmanager.TableWiseOrder.Tablewiseorder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TableWiseOrder extends AppCompatActivity {

    GridView gridView;
    String id;

    Tablewiseorder tablewiseorder = new Tablewiseorder();
    List<Order> orders = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_wise_order);

        id = getIntent().getStringExtra("_id");
        gridView = (GridView)findViewById(R.id.gridview);

        tableorder();
    }

    private void tableorder() {
        Retrofit retrofit = RetrofitClient.getInstance();
        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);

        Call<Tablewiseorder> call = retrofitInterface.TableOrder(id);

        call.enqueue(new Callback<Tablewiseorder>() {
            @Override
            public void onResponse(Call<Tablewiseorder> call, Response<Tablewiseorder> response) {
                if(response.isSuccessful()){


                    tablewiseorder = response.body();
                    orders = tablewiseorder.getList().getOrders();

                    CustomAdepter customAdepter = new CustomAdepter(TableWiseOrder.this,orders);
                    gridView.setAdapter(customAdepter);

                    Toast.makeText(TableWiseOrder.this, "Succes", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(TableWiseOrder.this, ""+response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Tablewiseorder> call, Throwable t) {
                Toast.makeText(TableWiseOrder.this, "Failure", Toast.LENGTH_SHORT).show();
            }
        });
}
    class CustomAdepter extends BaseAdapter {

        List<Order> orders;
        Context context;


        public CustomAdepter(TableWiseOrder tableWiseOrder, List<Order> orders) {
            this.context = tableWiseOrder;
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
                view = LayoutInflater.from(context).inflate(R.layout.orderlayout,viewGroup,false);
                LayoutInflater lInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
                view = lInflater.inflate(R.layout.orderlayout, null);
            }

            TextView ttotal = view.findViewById(R.id.total);
            TextView tname = view.findViewById(R.id.tname);
            TextView temail = view.findViewById(R.id.temail);
            TextView tdate = view.findViewById(R.id.tpaymentmethod);
            TextView tstatus = view.findViewById(R.id.tstatus);
            Button btn = view.findViewById(R.id.btn1);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String get = orders.get(i).getId();
                    Intent intent = new Intent(TableWiseOrder.this, TableWiseItemList.class);
                    intent.putExtra("_id",orders.get(i).getId());
                    startActivity(intent);
                }
            });

            ttotal.setText(orders.get(i).getGrandTotal()+"");
            tname.setText(orders.get(i).getName());
            temail.setText(orders.get(i).getEmail());
            tdate.setText(orders.get(i).getCreatedAt());
            tstatus.setText(orders.get(i).getPaymentStatus());

            return view;
        }
    }

}