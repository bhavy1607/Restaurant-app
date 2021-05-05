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
import com.example.restaurant_app.modelmanager.getingrediants.Getingredients;
import com.example.restaurant_app.modelmanager.getingrediants.Ingredient;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Viewingrediant extends AppCompatActivity {

    GridView gridView;
    Getingredients getingredients = new Getingredients();
    List<Ingredient> ingredients = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewingrediant);

        gridView = (GridView)findViewById(R.id.gridview);

        viewingrediants();
    }

    private void viewingrediants(){

        Retrofit retrofit = RetrofitClient.getInstance();
        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);

        Call<Getingredients> call = retrofitInterface.Getingrediants();

        call.enqueue(new Callback<Getingredients>() {
            @Override
            public void onResponse(Call<Getingredients> call, Response<Getingredients> response) {
                if(response.isSuccessful()){

                 getingredients = response.body();
                 ingredients = getingredients.getIngredients();

                 CustomAdepter customAdepter = new CustomAdepter(Viewingrediant.this,ingredients);
                 gridView.setAdapter(customAdepter);

                    Toast.makeText(Viewingrediant.this, "Succes", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(Viewingrediant.this, ""+response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Getingredients> call, Throwable t) {
                Toast.makeText(Viewingrediant.this, "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    class CustomAdepter extends BaseAdapter {

        List<Ingredient>  ingredients;
        Context context;

        public CustomAdepter(Viewingrediant viewingrediant, List<Ingredient> ingredients) {
            this.context = viewingrediant;
            this.ingredients = ingredients;
        }

        @Override
        public int getCount() {
            return ingredients.size();
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

            TextView tname = convertView.findViewById(R.id.tname);
            TextView tdec = convertView.findViewById(R.id.dec);
            TextView tid = convertView.findViewById(R.id.id);
            ImageView imageView = convertView.findViewById(R.id.I1);

            tname.setText(ingredients.get(position).getIngredientName());
            tdec.setText(ingredients.get(position).getDescription());
            tid.setText(ingredients.get(position).getId());

            Picasso.with(Viewingrediant.this).load(ingredients.get(position).getImageUrl()).into(imageView);

            return convertView;
        }
    }
}