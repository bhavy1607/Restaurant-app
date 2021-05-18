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

public class Categories extends AppCompatActivity {

    EditText et1,et2;
    Button button;
    GridView gridView;

    ShowCategories showCategories = new ShowCategories();
    List<Categorypost> categoryposts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        et1 = (EditText)findViewById(R.id.et1);
        et2 = (EditText)findViewById(R.id.et2);
        button = (Button) findViewById(R.id.btn);
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

                    CustomAdepter customAdepter = new CustomAdepter(Categories.this,categoryposts);
                    gridView.setAdapter(customAdepter);


                    Toast.makeText(Categories.this, "Succes", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(Categories.this, ""+response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ShowCategories> call, Throwable t) {
                Toast.makeText(Categories.this, "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }
    class CustomAdepter extends BaseAdapter {

        List<Categorypost> categoryposts;
        Context context;

        public CustomAdepter(Categories categories, List<Categorypost> categoryposts) {
            this.context = categories;
            this.categoryposts = categoryposts;
        }


        @Override
        public int getCount() {
            return categoryposts.size();
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
                convertView = LayoutInflater.from(context).inflate(R.layout.ingrediantlayout,parent,false);
                LayoutInflater lInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
                convertView = lInflater.inflate(R.layout.ingrediantlayout, null);
            }

            TextView t1 = convertView.findViewById(R.id.catename);
            ImageView imageView = convertView.findViewById(R.id.cateimage);

           // t1.setText(categoryposts.get(position).getId());
                t1.setText(categoryposts.get(position).getCategoryName());

            Picasso.with(Categories.this).load(categoryposts.get(position).getImageUrl()).into(imageView);

            return convertView;
        }
    }
}