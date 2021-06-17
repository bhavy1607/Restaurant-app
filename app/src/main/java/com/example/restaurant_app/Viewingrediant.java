package com.example.restaurant_app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.restaurant_app.Retrofit.RetrofitClient;
import com.example.restaurant_app.Retrofit.RetrofitInterface;
import com.example.restaurant_app.modelmanager.createIngrediants.Body;
import com.example.restaurant_app.modelmanager.createIngrediants.Createingrediants;
import com.example.restaurant_app.modelmanager.getingrediants.Getingredients;
import com.example.restaurant_app.modelmanager.getingrediants.Ingredient;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Viewingrediant extends AppCompatActivity {

    int SELECT_PHOTO = 1;
    Uri uri;
    ImageView imageView;
    Button button,btn;
    EditText et1,et3,et4;

    GridView gridView;

    Getingredients getingredients = new Getingredients();
    List<Ingredient> ingredients = new ArrayList<>();

    Createingrediants createingrediants = new Createingrediants();
    com.example.restaurant_app.modelmanager.createIngrediants.Ingredient ingredient = new com.example.restaurant_app.modelmanager.createIngrediants.Ingredient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewingrediant);


        btn = (Button)findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    AddIngrediants();
            }
        });

        et1 = (EditText)findViewById(R.id.et1);
        et3 = (EditText)findViewById(R.id.et3);
        et4 = (EditText)findViewById(R.id.et4);
        button = (Button)findViewById(R.id.btnshowimage);
        imageView = (ImageView)findViewById(R.id.imageview);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,SELECT_PHOTO);
            }
        });

        gridView = (GridView)findViewById(R.id.gridview);

        viewingrediants();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == SELECT_PHOTO && resultCode == RESULT_OK && data != null && data.getData() != null){
            uri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                imageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
            TextView price = convertView.findViewById(R.id.price);
            ImageView imageView = convertView.findViewById(R.id.I1);

            tname.setText(ingredients.get(position).getIngredientName());
            tdec.setText(ingredients.get(position).getDescription());
            price.setText(ingredients.get(position).getPrice()+""+" ₹");

            Picasso.with(Viewingrediant.this).load(ingredients.get(position).getImageUrl()).into(imageView);

            return convertView;
        }
    }

    private void AddIngrediants(){

        Retrofit retrofit = RetrofitClient.getInstance();
        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);

        File file = new File(String.valueOf(R.drawable.i_1));
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", file.getName(), RequestBody.create(MediaType.parse("image/*"), file));

        String ingrediantname = et1.getText().toString();
        String price = et3.getText().toString()+"";
        String description = et4.getText().toString();
      //  String image = imageView.toString();

        Body body = new Body();
        //body.setImageUrl(image);
        body.setDescription(description);
        body.setIngredientName(ingrediantname);
        body.setPrice(Integer.parseInt(price));

        Call<Createingrediants> call = retrofitInterface.AddIngrediants(body,filePart);

        call.enqueue(new Callback<Createingrediants>() {
            @Override
            public void onResponse(Call<Createingrediants> call, Response<Createingrediants> response) {
                if(response.isSuccessful()){



                    Toast.makeText(Viewingrediant.this, "Succes", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(Viewingrediant.this, ""+response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Createingrediants> call, Throwable t) {
                Toast.makeText(Viewingrediant.this, "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }

}