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
import com.example.restaurant_app.modelmanager.managerdetails.Bodymanager;
import com.example.restaurant_app.modelmanager.managerdetails.ManagerDetails;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class View_manager extends AppCompatActivity {

    GridView gridView;
    public static String id;


    ManagerDetails managerDetails = new ManagerDetails();
    List<com.example.restaurant_app.modelmanager.managerdetails.List> lists = new ArrayList<>();
    private int i;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_manager);

        gridView = (GridView) findViewById(R.id.gridview);
        id = getIntent().getStringExtra("_id");


        showmanager();
    }

    private void showmanager() {
        Retrofit retrofit = RetrofitClient.getInstance();
        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);

        Bodymanager bodymanager = new Bodymanager("manager");

        Call<ManagerDetails> call = retrofitInterface.Getmanager(bodymanager);

        call.enqueue(new Callback<ManagerDetails>() {
            @Override
            public void onResponse(Call<ManagerDetails> call, Response<ManagerDetails> response) {
                if (response.isSuccessful()) {

                    managerDetails = response.body();
                    lists = managerDetails.getList();

                    CustomAdepter customAdepter = new CustomAdepter(View_manager.this, lists);
                    gridView.setAdapter(customAdepter);

                    Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "" + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ManagerDetails> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }


     class CustomAdepter extends BaseAdapter {

        List<com.example.restaurant_app.modelmanager.managerdetails.List> lists;
        Context context;



        public CustomAdepter(View_manager view_manager, List<com.example.restaurant_app.modelmanager.managerdetails.List> lists) {
            this.context = view_manager;
            this.lists = lists;
        }

        @Override
        public int getCount() {
            return   lists.size();
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
                view = LayoutInflater.from(context).inflate(R.layout.cardlayout,viewGroup,false);
                LayoutInflater lInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
                view = lInflater.inflate(R.layout.cardlayout, null);
            }

            TextView tname = view.findViewById(R.id.tname);
            TextView temail = view.findViewById(R.id.temail);
            TextView tphone = view.findViewById(R.id.tphone);
            Button btn = view.findViewById(R.id.btndelete);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Deleted succesfully..", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(View_manager.this,AdminHome.class);
                    startActivity(intent);
                    cookdelete();

                }
            });

            tname.setText(lists.get(i).getName());
            temail.setText(lists.get(i).getEmail());
            tphone.setText(lists.get(i).getPhone());

            return view;
        }
    }
    private void cookdelete(){
        Retrofit retrofit = RetrofitClient.getInstance();
        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);

        String get = lists.get(i).getId();
        Call<Deletecook> call = retrofitInterface.cookdelete(get);

        call.enqueue(new Callback<Deletecook>() {
            @Override
            public void onResponse(Call<Deletecook> call, Response<Deletecook> response) {
                if(response.isSuccessful()){

                    Toast.makeText(View_manager.this, "Succes", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(View_manager.this, +response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Deletecook> call, Throwable t) {
                Toast.makeText(View_manager.this, "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }
}