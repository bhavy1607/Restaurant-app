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
import com.example.restaurant_app.modelmanager.delete.Deletecook;
import com.example.restaurant_app.modelmanager.waiterdetails.Bodywaiter;
import com.example.restaurant_app.modelmanager.waiterdetails.Waiterdetails;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ViewWaiter extends AppCompatActivity {

    GridView gridView;
    public static String id;
    Button btnback;

    Waiterdetails waiterdetails = new Waiterdetails();
    List<com.example.restaurant_app.modelmanager.waiterdetails.List> lists = new ArrayList<>();
    private int i;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_waiter);

        gridView = (GridView)findViewById(R.id.gridview);
        id = getIntent().getStringExtra("_id");
        btnback = (Button)findViewById(R.id.btnback);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewWaiter.this,ManagerHome.class);
                startActivity(intent);
            }
        });

        listingdata();

    }

    private void listingdata() {

        Retrofit retrofitclient = RetrofitClient.getInstance();
        RetrofitInterface retrofitInterface = retrofitclient.create(RetrofitInterface.class);

        Bodywaiter bodywaiter = new Bodywaiter("waiter");

        Call<Waiterdetails> call = retrofitInterface.Getwaiter(bodywaiter);
        call.enqueue(new Callback<Waiterdetails>() {
            @Override
            public void onResponse(Call<Waiterdetails> call, Response<Waiterdetails> response) {

                if (response.isSuccessful()) {

                    waiterdetails = response.body();
                    lists = waiterdetails.getList();

                    CustomAdpter customAdpter = new CustomAdpter(lists,ViewWaiter.this);
                    gridView.setAdapter(customAdpter);

                    Toast.makeText(ViewWaiter.this, "Succes", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ViewWaiter.this, "" + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Waiterdetails> call, Throwable t) {
                Toast.makeText(ViewWaiter.this, "Failure" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

        class CustomAdpter extends BaseAdapter {

            List<com.example.restaurant_app.modelmanager.waiterdetails.List> lists;
            Context context;

            public CustomAdpter(List<com.example.restaurant_app.modelmanager.waiterdetails.List> lists, ViewWaiter viewWaiter) {
                this.context = viewWaiter;
                this.lists = lists;
            }

            @Override
            public int getCount() {
                return lists.size();
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
            public View getView(int position, View view, ViewGroup parent) {
                if (view == null) {
                    view = LayoutInflater.from(context).inflate(R.layout.cardlayout, parent, false);
                    LayoutInflater lInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
                    view = lInflater.inflate(R.layout.cardlayout, null);
                }

                TextView tname = view.findViewById(R.id.tname);
                TextView temail = view.findViewById(R.id.temail);
                TextView tphone = view.findViewById(R.id.tphone);
                Button btn = view.findViewById(R.id.btndelete);
                Button btn1 = view.findViewById(R.id.btnupdate);
                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String get = lists.get(position).getId();
                        Intent intent = new Intent(ViewWaiter.this,WaiterUpdateDetails.class);
                        intent.putExtra("_id",lists.get(position).getId());
                        startActivity(intent);
                    }
                });
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        cookdelete();
                        Toast.makeText(context, "Deleted succesfully..", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ViewWaiter.this, ManagerHome.class);
                        startActivity(intent);
                    }
                });

                tname.setText(lists.get(i).getName());
                temail.setText(lists.get(i).getEmail());
                tphone.setText(lists.get(i).getPhone());

                return view;
            }
        }


        private void cookdelete ( ) {
            Retrofit retrofit = RetrofitClient.getInstance();
            RetrofitInterface retrofitInterface1 = retrofit.create(RetrofitInterface.class);

            String get = lists.get(i).getId();
            Call<Deletecook> call1 = retrofitInterface1.cookdelete(get);

            call1.enqueue(new Callback<Deletecook>() {
                @Override
                public void onResponse(Call<Deletecook> call, Response<Deletecook> response) {
                    if (response.isSuccessful()) {

                        Toast.makeText(ViewWaiter.this, "Succes", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ViewWaiter.this, +response.code(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Deletecook> call, Throwable t) {
                    Toast.makeText(ViewWaiter.this, "Failure", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
