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
import android.widget.EditText;
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
    EditText editText;
    Button button;
    public static String id;


    Waiterdetails waiterdetails = new Waiterdetails();
    List<com.example.restaurant_app.modelmanager.waiterdetails.List> waiterList = new ArrayList<>();
    private int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_waiter);

        gridView = (GridView)findViewById(R.id.gridview);
        id = getIntent().getStringExtra("_id");
        editText = (EditText)findViewById(R.id.et);
        button = (Button)findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listingdata();
            }
        });

    }

    private void listingdata(){
        Retrofit retrofitclient = RetrofitClient.getInstance();
        RetrofitInterface retrofitInterface =  retrofitclient.create(RetrofitInterface.class);


        String name = editText.getText().toString();

        Bodywaiter bodywaiter = new Bodywaiter();
        bodywaiter.setActiverole(name);

        Call<Waiterdetails> listing = retrofitInterface.Getwaiter(bodywaiter);

        listing.enqueue(new Callback<Waiterdetails>() {
            @Override
            public void onResponse(Call<Waiterdetails> call, Response<Waiterdetails> response) {
                if(response.isSuccessful()){

                    waiterdetails = response.body();
                    waiterList = waiterdetails.getList();

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

        List<com.example.restaurant_app.modelmanager.waiterdetails.List> waiterList;
        private Context context;


        public CustomAdepter(List<com.example.restaurant_app.modelmanager.waiterdetails.List> waiterList, ViewWaiter context) {
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
            Button btn = view.findViewById(R.id.btndelete);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Deleted succesfully..", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ViewWaiter.this,ManagerHome.class);
                    startActivity(intent);
                    cookdelete();


                }
            });

            tname.setText(waiterList.get(i).getName());
            temail.setText(waiterList.get(i).getEmail());
            tphone.setText(waiterList.get(i).getPhone());

            return view;
        }
    }

    private void cookdelete(){
        Retrofit retrofit = RetrofitClient.getInstance();
        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);

        String get = waiterList.get(i).getId();
        Call<Deletecook> call = retrofitInterface.cookdelete(get);

        call.enqueue(new Callback<Deletecook>() {
            @Override
            public void onResponse(Call<Deletecook> call, Response<Deletecook> response) {
                if(response.isSuccessful()){

                    Toast.makeText(ViewWaiter.this, "Succes", Toast.LENGTH_SHORT).show();
                }else {
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