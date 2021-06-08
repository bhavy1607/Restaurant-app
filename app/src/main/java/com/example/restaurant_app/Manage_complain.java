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
import com.example.restaurant_app.modelmanager.managecomplain.Complaint;
import com.example.restaurant_app.modelmanager.managecomplain.Getcomplate;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Manage_complain extends AppCompatActivity {

    GridView gridView;
    public static String id;


    Getcomplate getcomplate = new Getcomplate();
    List<Complaint> complaints = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_complain);

        gridView = (GridView)findViewById(R.id.gridview);

        Showcomplain();
    }

    private void Showcomplain(){
        Retrofit retrofit = RetrofitClient.getInstance();
        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);

        Call<Getcomplate> call = retrofitInterface.GetComplate();

        call.enqueue(new Callback<Getcomplate>() {
            @Override
            public void onResponse(Call<Getcomplate> call, Response<Getcomplate> response) {
                if(response.isSuccessful()){

                    getcomplate = response.body();
                    complaints = getcomplate.getComplaints();

                    CustomAdepter customAdepter = new CustomAdepter(Manage_complain.this,complaints);
                    gridView.setAdapter(customAdepter);

                    Toast.makeText(Manage_complain.this, "Succes", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(Manage_complain.this, ""+response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Getcomplate> call, Throwable t) {
                Toast.makeText(Manage_complain.this, "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }
    class CustomAdepter extends BaseAdapter {

        List<Complaint> complaints;
        Context context;

        public CustomAdepter(Manage_complain manage_complain, List<Complaint> complaints) {
            this.complaints = complaints;
            this.context = manage_complain;
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

           // TextView tid = convertView.findViewById(R.id.tid);
            TextView ttitle = convertView.findViewById(R.id.ttitle);
            TextView tmessage = convertView.findViewById(R.id.tmessage);
            Button btnreply = convertView.findViewById(R.id.btnreply);

            btnreply.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String get = complaints.get(position).getId();
                    Intent intent = new Intent(Manage_complain.this,ComplainReply.class);
                    intent.putExtra("_id",complaints.get(position).getId());
                    startActivity(intent);
                }
            });

            //tid.setText(complaints.get(position).getId());
            ttitle.setText(complaints.get(position).getTitle());
            tmessage.setText(complaints.get(position).getMessage());

            return convertView;
        }
    }
}