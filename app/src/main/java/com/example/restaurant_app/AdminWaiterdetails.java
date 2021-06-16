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
import com.example.restaurant_app.modelmanager.waiterdetails.Bodywaiter;
import com.example.restaurant_app.modelmanager.waiterdetails.Waiterdetails;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class   AdminWaiterdetails extends AppCompatActivity {

    GridView gridView;
    EditText editText;
    Button button;

    Waiterdetails waiterdetails = new Waiterdetails();
    List<com.example.restaurant_app.modelmanager.waiterdetails.List> waiterList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_waiterdetails);

        gridView = (GridView)findViewById(R.id.gridview);
        editText = (EditText)findViewById(R.id.et);
        button = (Button)findViewById(R.id.btn);

        listingdata();
    }

    private void listingdata(){
        Retrofit retrofitclient = RetrofitClient.getInstance();
        RetrofitInterface retrofitInterface =  retrofitclient.create(RetrofitInterface.class);




        Bodywaiter bodywaiter = new Bodywaiter("waiter");


        Call<Waiterdetails> listing = retrofitInterface.Getwaiter(bodywaiter);

        listing.enqueue(new Callback<Waiterdetails>() {
            @Override
            public void onResponse(Call<Waiterdetails> call, Response<Waiterdetails> response) {
                if(response.isSuccessful()){

                    waiterdetails = response.body();
                    waiterList = waiterdetails.getList();

                    CustomAdepter customAdepter = new CustomAdepter(waiterList,AdminWaiterdetails.this);
                    gridView.setAdapter(customAdepter);

                    Toast.makeText(AdminWaiterdetails.this, "Success", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(AdminWaiterdetails.this, ""+response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Waiterdetails> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Failure",Toast.LENGTH_SHORT).show();
            }
        });
    }

    class CustomAdepter extends BaseAdapter {

        List<com.example.restaurant_app.modelmanager.waiterdetails.List> waiterList;
        private Context context;

        public CustomAdepter(List<com.example.restaurant_app.modelmanager.waiterdetails.List> waiterList, AdminWaiterdetails adminWaiterdetails) {
            this.context = adminWaiterdetails;
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