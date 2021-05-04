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
import com.example.restaurant_app.modeladmin.Getmanager;
import com.example.restaurant_app.modeladmin.Manager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class View_manager extends AppCompatActivity {

    GridView gridView;

    Getmanager getmanager = new Getmanager();
    List<Manager> managers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_manager);

        gridView = (GridView)findViewById(R.id.gridview);

        showmanager();
    }

    private void showmanager(){
        Retrofit retrofit = RetrofitClient.getInstance();
        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);

        Call<Getmanager> call = retrofitInterface.Getmanager();

        call.enqueue(new Callback<Getmanager>() {
            @Override
            public void onResponse(Call<Getmanager> call, Response<Getmanager> response) {
                if(response.isSuccessful()){

                    getmanager = response.body();
                    managers = getmanager.getManagers();

                    CustomAdepter customAdepter = new CustomAdepter(View_manager.this,managers);
                    gridView.setAdapter(customAdepter);

                    Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(), ""+response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Getmanager> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }


    static class CustomAdepter extends BaseAdapter {

        List<Manager> managers;
        Context context;

        public CustomAdepter(View_manager view_manager, List<Manager> managers) {
            this.context = view_manager;
            this.managers = managers;
        }

        @Override
        public int getCount() {
            return   managers.size();
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

            tname.setText(managers.get(i).getName());
            temail.setText(managers.get(i).getEmail());
            tphone.setText(managers.get(i).getPhone());

            return view;
        }
    }
}