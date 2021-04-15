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
import com.example.restaurant_app.model.Cook;
import com.example.restaurant_app.model.Cookdetails;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ViewCook extends AppCompatActivity {

    GridView gridView;

    Cookdetails cookdetails = new Cookdetails();
    List<Cook> cooks = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cook);

        gridView = (GridView)findViewById(R.id.gridview);

        listingdata();
    }

    private void listingdata() {

        Retrofit retrofit = RetrofitClient.getInstance();
        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);

        Call<Cookdetails> listing = retrofitInterface.Getcook();

        listing.enqueue(new Callback<Cookdetails>() {
            @Override
            public void onResponse(Call<Cookdetails> call, Response<Cookdetails> response) {
                if (response.isSuccessful()) {

                    cookdetails = response.body();
                    cooks = cookdetails.getCooks();

                    CustomAdepter customAdepter = new CustomAdepter(cooks,ViewCook.this);
                    gridView.setAdapter(customAdepter);

                    Toast.makeText(ViewCook.this, "Success", Toast.LENGTH_SHORT).show();

                } else {

                    Toast.makeText(ViewCook.this, "" + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Cookdetails> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }

static class CustomAdepter extends BaseAdapter {

        List<Cook> cooks;
        Context context;

    public CustomAdepter(List<Cook> cooks, ViewCook context) {
        this.context = context;
        this.cooks = cooks;
    }

    @Override
        public int getCount() {
            return   cooks.size();
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

            tname.setText(cooks.get(i).getName());
            temail.setText(cooks.get(i).getEmail());
            tphone.setText(cooks.get(i).getPhone());

            return view;
        }
    }
}
//Cookdetails cookdetails = new Cookdetails();
//List<Cook> cookList = new ArrayList<>();
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        gridView = (GridView)findViewById(R.id.gridview);
//
//        listingdata();
//    }
//
//    private void listingdata() {
//        Retrofit retrofitclient = RetrofitClient.getInstance();
//        RetrofitInterface retrofitInterface = retrofitclient.create(RetrofitInterface.class);
//
//        Call<Cookdetails> listing = retrofitInterface.Getcook();
//
//        listing.enqueue(new Callback<Cookdetails>() {
//
//            @Override
//            public void onResponse(Call<Cookdetails> call, Response<Cookdetails> response) {
//                if (!response.isSuccessful()) {
//
//                    Toast.makeText(ViewCook.this, "Success", Toast.LENGTH_SHORT).show();
//
//                    cookdetails = response.body();
//                    cookList = cookdetails.getCooks();
//
//                    CustomAdepter customAdepter = new CustomAdepter(cookList,ViewCook.this);
//                    gridView.setAdapter(customAdepter);
//
//                } else {
//                    Toast.makeText(ViewCook.this, "" + response.message(), Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Cookdetails> call, Throwable t) {
//                Toast.makeText(getApplicationContext(), "Failure", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//
//    static class CustomAdepter extends BaseAdapter {
//
//        List<Cook> cookList;
//        private Context context;
//
//        public CustomAdepter(List<Cook> cookList, ViewCook context) {
//            this.context = context;
//            this.cookList = cookList;
//        }
//
//        @Override
//        public int getCount() {
//            return cookList.size();
//        }
//
//        @Override
//        public Object getItem(int position) {
//            return null;
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return 0;
//        }
//
//        @Override
//        public View getView(int i, View view, ViewGroup viewGroup) {
//            if (view == null) {
//                LayoutInflater lInflater = (LayoutInflater) context.getSystemService(
//                        Activity.LAYOUT_INFLATER_SERVICE);
//                view = lInflater.inflate(R.layout.cardlayout, null);
//            }
//
//            TextView tname = view.findViewById(R.id.tname);
//            TextView temail = view.findViewById(R.id.temail);
//            TextView tphone = view.findViewById(R.id.tphone);
//
//            tname.setText(cookList.get(i).getName());
//            temail.setText(cookList.get(i).getEmail());
//            tphone.setText(cookList.get(i).getPhone());
//
//            return view;
//        }
//    }
