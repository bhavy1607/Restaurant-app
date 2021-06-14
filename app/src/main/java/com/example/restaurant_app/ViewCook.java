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
import com.example.restaurant_app.modelmanager.cookdetails.Bodycook;
import com.example.restaurant_app.modelmanager.cookdetails.Cookdetails;
import com.example.restaurant_app.modelmanager.delete.Deletecook;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ViewCook extends AppCompatActivity {

    GridView gridView;
    EditText editText;
    Button button;
    public static String id;


    Cookdetails cookdetails = new Cookdetails();
    List<com.example.restaurant_app.modelmanager.cookdetails.List> lists = new ArrayList<>();
    private int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cook);

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

    private void listingdata() {

        Retrofit retrofit = RetrofitClient.getInstance();
        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);

        String name = editText.getText().toString();

        Bodycook bodycook = new Bodycook();
        bodycook.setActiverole(name);

        Call<Cookdetails> listing = retrofitInterface.Getcook(bodycook);

        listing.enqueue(new Callback<Cookdetails>() {
            @Override
            public void onResponse(Call<Cookdetails> call, Response<Cookdetails> response) {
                if (response.isSuccessful()) {

                    if(bodycook.getActiverole().equals("cook")){


                        cookdetails = response.body();
                        lists = cookdetails.getList();

                        CustomAdepter customAdepter = new CustomAdepter(lists, ViewCook.this);
                        gridView.setAdapter(customAdepter);


                    }

                    Toast.makeText(ViewCook.this, "Success", Toast.LENGTH_SHORT).show();



                } else {

                    Toast.makeText(ViewCook.this, "" +response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Cookdetails> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Failure"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    class CustomAdepter extends BaseAdapter {

        List<com.example.restaurant_app.modelmanager.cookdetails.List> lists;
        Context context;


    public CustomAdepter(List<com.example.restaurant_app.modelmanager.cookdetails.List> lists, ViewCook context) {
        this.context = context;
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
                    Intent intent = new Intent(ViewCook.this,ManagerHome.class);
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

                    Toast.makeText(ViewCook.this, "Succes", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(ViewCook.this, +response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Deletecook> call, Throwable t) {
                Toast.makeText(ViewCook.this, "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
