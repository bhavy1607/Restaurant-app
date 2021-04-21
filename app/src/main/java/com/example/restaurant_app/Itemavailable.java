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
import com.example.restaurant_app.modelmanager.Availableitem;
import com.example.restaurant_app.modelmanager.Menu;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Itemavailable extends AppCompatActivity {

    GridView gridView;
    Availableitem availableitem = new Availableitem();
    List<Menu> menus = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_availableitem);

        gridView = (GridView)findViewById(R.id.gridview);

        itemAvailable();
    }

    private void itemAvailable(){

        Retrofit retrofit = RetrofitClient.getInstance();
        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);

        Call<Availableitem> call = retrofitInterface.GetitemAvailable();

        call.enqueue(new Callback<Availableitem>() {
            @Override
            public void onResponse(Call<Availableitem> call, Response<Availableitem> response) {
                if(response.isSuccessful()){

                  availableitem = response.body();
                  menus = availableitem.getMenu();

                   CustomAdepter customAdepter = new CustomAdepter(menus, Itemavailable.this);
                   gridView.setAdapter(customAdepter);

                   Toast.makeText(Itemavailable.this, "Available Item", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(Itemavailable.this, ""+response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Availableitem> call, Throwable t) {
                Toast.makeText(Itemavailable.this, "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    class CustomAdepter extends BaseAdapter {

        List<Menu> menus;
        Context context;

        public CustomAdepter(List<Menu> menus, Itemavailable itemavailable) {
            this.menus = menus;
            this.context = itemavailable;
        }

        @Override
        public int getCount() {
            return menus.size();
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
                convertView = LayoutInflater.from(context).inflate(R.layout.clickeditemlayout,parent,false);
                LayoutInflater lInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
                convertView = lInflater.inflate(R.layout.clickeditemlayout, null);
            }


            TextView tname = convertView.findViewById(R.id.tname);
            TextView tdec = convertView.findViewById(R.id.tdec);
            TextView tid = convertView.findViewById(R.id.id);

            tname.setText(menus.get(position).getName());
            tdec.setText(menus.get(position).getDescription());
            tid.setText(menus.get(position).getId());

            return convertView;
        }
    }
}