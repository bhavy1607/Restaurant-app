package com.example.restaurant_app;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

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

public class AdminManagerFragment extends  Fragment {

    GridView gridView;

    Getmanager getmanager = new Getmanager();
    List<Manager> managers = new ArrayList<>();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.fragment_admin_manager, container, false);
        gridView = (GridView)rootview.findViewById(R.id.gridview);


        showmanager();
        return rootview;
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

                    CustomAdepter customAdepter = new CustomAdepter(AdminManagerFragment.this,managers);
                    gridView.setAdapter(customAdepter);


                }else {
                    //Toast.makeText(getApplicationContext(), ""+response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Getmanager> call, Throwable t) {
                //Toast.makeText(getApplicationContext(), "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }



    static class CustomAdepter extends BaseAdapter {

        List<Manager> managers;
        Context context;
        LayoutInflater inflater;



        public CustomAdepter(AdminManagerFragment adminManagerFragment, List<Manager> managers) {
            this.context = context;
            this.managers = managers;
            inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);        }

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
                view = inflater.inflate(R.layout.cardlayout, null);
            }

            TextView tname = view.findViewById(R.id.tname);
           // TextView temail = view.findViewById(R.id.temail);
            TextView tphone = view.findViewById(R.id.tphone);

            tname.setText(managers.get(i).getName());
           // temail.setText(managers.get(i).getEmail());
            tphone.setText(managers.get(i).getPhone());

            return view;
        }
    }



}