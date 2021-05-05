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
import com.example.restaurant_app.modelmanager.waiterdetails.Waiter;
import com.example.restaurant_app.modelmanager.waiterdetails.Waiterdetails;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ViewWaiter extends AppCompatActivity {

    GridView gridView;

    Waiterdetails waiterdetails = new Waiterdetails();
    List<Waiter> waiterList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_waiter);

        gridView = (GridView)findViewById(R.id.gridview);

        listingdata();

    }

    private void listingdata(){
        Retrofit retrofitclient = RetrofitClient.getInstance();
        RetrofitInterface retrofitInterface =  retrofitclient.create(RetrofitInterface.class);

        Call<Waiterdetails> listing = retrofitInterface.Getwaiter();

        listing.enqueue(new Callback<Waiterdetails>() {
            @Override
            public void onResponse(Call<Waiterdetails> call, Response<Waiterdetails> response) {
                if(response.isSuccessful()){

                    waiterdetails = response.body();
                    waiterList = waiterdetails.getWaiters();

                    CustomAdepter customAdepter = new CustomAdepter(waiterList,ViewWaiter.this);
                    gridView.setAdapter(customAdepter);

                    Toast.makeText(ViewWaiter.this, "Success", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(ViewWaiter.this, ""+response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Waiterdetails> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Failure",Toast.LENGTH_SHORT).show();
            }
        });
    }

    class CustomAdepter extends BaseAdapter {

        List<Waiter> waiterList;
        private Context context;

        public CustomAdepter(List<Waiter> waiterList, ViewWaiter context) {
            this.context = context;
            this.waiterList = waiterList;
        }

       @Override
        public int getCount() {
            return waiterList.size();
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

            tname.setText(waiterList.get(i).getName());
            temail.setText(waiterList.get(i).getEmail());
            tphone.setText(waiterList.get(i).getPhone());

            return view;
        }
    }
}