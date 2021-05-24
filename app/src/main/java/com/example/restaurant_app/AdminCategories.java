package com.example.restaurant_app;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.restaurant_app.Retrofit.RetrofitClient;
import com.example.restaurant_app.Retrofit.RetrofitInterface;
import com.example.restaurant_app.modelmanager.showCategories.Categorypost;
import com.example.restaurant_app.modelmanager.showCategories.ShowCategories;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AdminCategories extends AppCompatActivity {

    GridView gridView;

    ShowCategories showCategories = new ShowCategories();
    List<Categorypost> categoryposts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admim_categories);

        gridView = (GridView)findViewById(R.id.gridview);

        showcategories();

    }
    private void showcategories(){
        Retrofit retrofit = RetrofitClient.getInstance();
        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);

        Call<ShowCategories> call = retrofitInterface.showCategories();

        call.enqueue(new Callback<ShowCategories>() {
            @Override
            public void onResponse(Call<ShowCategories> call, Response<ShowCategories> response) {

                if(response.isSuccessful()){

                    showCategories = response.body();
                    categoryposts = showCategories.getCategoryposts();

                    CustomAdepter customAdepter = new CustomAdepter(AdminCategories.this,categoryposts);
                    gridView.setAdapter(customAdepter);

                    Toast.makeText(AdminCategories.this, "Succes", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(AdminCategories.this, ""+response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ShowCategories> call, Throwable t) {
                Toast.makeText(AdminCategories.this, "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    class CustomAdepter extends BaseAdapter {

        List<Categorypost> categoryposts;
        Context context;

        public CustomAdepter(AdminCategories adminCategories, List<Categorypost> categoryposts) {
            this.context = adminCategories;
            this.categoryposts = categoryposts;
        }


        @Override
        public int getCount() {
            return   categoryposts.size();
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
        public View getView(int position, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = LayoutInflater.from(context).inflate(R.layout.categorieslayout,viewGroup,false);
                LayoutInflater lInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
                view = lInflater.inflate(R.layout.categorieslayout, null);
            }

            TextView textView = view.findViewById(R.id.name);
            textView.setText(categoryposts.get(position).getCategoryName());

            ImageView imageView = view.findViewById(R.id.image);

            Picasso.with(AdminCategories.this).load(categoryposts.get(position).getImageUrl()).into(imageView);


            return view;
        }
    }
}