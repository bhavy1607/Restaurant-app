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
import com.example.restaurant_app.modelmanager.managecomplain.Complaint;
import com.example.restaurant_app.modelmanager.managecomplain.Getcomplate;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AdminShowComplain extends AppCompatActivity {

    GridView  gridView;

    Getcomplate getcomplate = new Getcomplate();
    List<Complaint> complaints = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_show_complate);

        gridView = (GridView)findViewById(R.id.gridview);

        manageComplate();
    }

//    private void showcomplain(){
//        Retrofit retrofit = RetrofitClient.getInstance();
//        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);
//
//        Call<Getcomplate> call = retrofitInterface.Getcomplate();
//
//        call.enqueue(new Callback<Getcomplate>() {
//            @Override
//            public void onResponse(Call<Getcomplate> call, Response<Getcomplate> response) {
//                if(response.isSuccessful()){
//
//                    getcomplate = response.body();
//                    complaints = getcomplate.getComplaints();
//
//                    CustomAdepter customAdepter = new CustomAdepter(AdminShowComplain.this,complaints);
//                    gridView.setAdapter(customAdepter);
//
//                    Toast.makeText(getApplicationContext(), "Succes", Toast.LENGTH_SHORT).show();
//                }else {
//                    Toast.makeText(getApplicationContext(), ""+response.message(), Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Getcomplate> call, Throwable t) {
//                Toast.makeText(getApplicationContext(), "Failure", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
private void manageComplate(){

    Retrofit retrofit = RetrofitClient.getInstance();
    RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);

    Call<Getcomplate> call = retrofitInterface.GetComplate();

    call.enqueue(new Callback<Getcomplate>() {
        @Override
        public void onResponse(Call<Getcomplate> call, Response<Getcomplate> response) {
            if(response.isSuccessful()){

                getcomplate = response.body();
                complaints = getcomplate.getComplaints();

                CustomAdepter customAdepter = new CustomAdepter(AdminShowComplain.this,complaints);
                gridView.setAdapter(customAdepter);


                Toast.makeText(getApplicationContext(), "Succes", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(getApplicationContext(), ""+response.message(), Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(Call<Getcomplate> call, Throwable t) {
            Toast.makeText(getApplicationContext(), "Failure", Toast.LENGTH_SHORT).show();
        }
    });
}
    class CustomAdepter extends BaseAdapter {

        List<Complaint> complaints;
        Context context;

        public CustomAdepter(AdminShowComplain adminShowComplain, List<Complaint> complaints) {
            this.complaints = complaints;
            this.context = adminShowComplain;
        }

        @Override
        public int getCount() {
            return complaints.size();
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
                convertView = LayoutInflater.from(context).inflate(R.layout.managecomplatelayout,parent,false);
                LayoutInflater lInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
                convertView = lInflater.inflate(R.layout.managecomplatelayout, null);
            }

            TextView tid = convertView.findViewById(R.id.tid);
            TextView ttitle = convertView.findViewById(R.id.ttitle);
            TextView tmessage = convertView.findViewById(R.id.tmessage);

            tid.setText(complaints.get(position).getId());
            ttitle.setText(complaints.get(position).getTitle());
            tmessage.setText(complaints.get(position).getMessage());

            return convertView;
        }
    }

//    class CustomAdepter extends BaseAdapter{
//
//        Context context;
//        List<Complaint> complaints;
//
//        public CustomAdepter(AdminShowComplain adminShowComplain, List<Complaint> complaints) {
//            this.context = adminShowComplain;
//            this.complaints = complaints;
//        }
//
//        @Override
//        public int getCount() {
//            return 0;
//        }
//
//        @Override
//        public Object getItem(int position) {
//            return complaints.size();
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return 0;
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            if(convertView == null){
//                convertView = LayoutInflater.from(context).inflate(R.layout.managecomplatelayout,parent,false);
//                LayoutInflater lInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
//                convertView = lInflater.inflate(R.layout.managecomplatelayout, null);
//            }
//
//            TextView tid = convertView.findViewById(R.id.tid);
//            TextView ttitle = convertView.findViewById(R.id.ttitle);
//            TextView tmessage = convertView.findViewById(R.id.tmessage);
//
//            tid.setText(complaints.get(position).getId());
//            ttitle.setText(complaints.get(position).getTitle());
//            tmessage.setText(complaints.get(position).getMessage());
//
//            return convertView;
//        }
//    }
}