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
import androidx.cardview.widget.CardView;

import com.example.restaurant_app.Retrofit.RetrofitClient;
import com.example.restaurant_app.Retrofit.RetrofitInterface;
import com.example.restaurant_app.modelmanager.createCategory.Body;
import com.example.restaurant_app.modelmanager.createCategory.Createcategories;
import com.example.restaurant_app.modelmanager.showCategories.Categorydelete;
import com.example.restaurant_app.modelmanager.showCategories.Categorypost;
import com.example.restaurant_app.modelmanager.showCategories.ShowCategories;
import com.squareup.picasso.Picasso;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Categories extends AppCompatActivity {

    int SELECT_PHOTO = 1;
    Uri uri;
    EditText et1,et2;
    Button btnadd,btnback,btnshowimage;
    GridView gridView;
    ImageView imageView;
    public static String id;


    ShowCategories showCategories = new ShowCategories();
    List<Categorypost> categoryposts = new ArrayList<>();

    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        et1 = (EditText)findViewById(R.id.et1);
       // et2 = (EditText)findViewById(R.id.et2);
        imageView = (ImageView)findViewById(R.id.imageview);
        btnshowimage = (Button)findViewById(R.id.btnshowimage);
        btnshowimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,SELECT_PHOTO);
            }
        });

        btnback = (Button)findViewById(R.id.btnback);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Categories.this,ManagerHome.class);
                startActivity(intent);
            }
        });
        id = getIntent().getStringExtra("_id");

        btnadd = (Button)findViewById(R.id.btnadd);

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddCategory();
            }
        });
        gridView = (GridView)findViewById(R.id.gridview);

        showcategories();

//        btnshowimage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Intent.ACTION_PICK);
//                intent.setType("image/*");
//                startActivityForResult(intent,SELECT_PHOTO);
//            }
//        });

    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode == SELECT_PHOTO && resultCode == RESULT_OK && data != null && data.getData() != null){
//            uri = data.getData();
//            try {
//                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
//                imageView.setImageBitmap(bitmap);
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }

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
                Toast.makeText(Categories.this, "Failure"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
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
                convertView = LayoutInflater.from(context).inflate(R.layout.categorieslayout,parent,false);
                LayoutInflater lInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
                convertView = lInflater.inflate(R.layout.categorieslayout, null);
            }

            TextView textView;
            ImageView imageView;
            CardView cardView;
            Button btn;

            btn = convertView.findViewById(R.id.btndel);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Deleted succesfully..", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Categories.this, ManagerHome.class);
                    startActivity(intent);
                    deleteCategory();

                }
            });
            cardView = convertView.findViewById(R.id.cardview);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String get = categoryposts.get(position).getId();
                    Intent intent = new Intent(Categories.this,CategoryItem.class);
                    intent.putExtra("_id",categoryposts.get(position).getId());
                    startActivity(intent);
                }
            });

            textView = convertView.findViewById(R.id.name);
            imageView = convertView.findViewById(R.id.image);

            textView.setText(categoryposts.get(position).getCategoryName());

            Picasso.with(Categories.this).load(categoryposts.get(position).getImageUrl()).into(imageView);

            return convertView;
        }
    }

    private void AddCategory(){
        Retrofit retrofit = RetrofitClient.getInstance();
        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);

        String categoryname = et1.getText().toString();
        String image = imageView.getDrawable().toString();
//        Drawable img = getResources().getDrawable(R.drawable.i_1);
//        String pathimage = img.toString();
//
//        Uri path = Uri.parse("android.resource://com.example.restaurant_app/" +R.drawable.i_1);
//        String imgpath = path.toString();

        Body body = new Body();

        body.setCategoryname(categoryname);
        body.setImageUrl(image);
      //  body.setImageUrl(imgpath);


        Call<Createcategories> call = retrofitInterface.AddCategory(body);

        call.enqueue(new Callback<Createcategories>() {
            @Override
            public void onResponse(Call<Createcategories> call, Response<Createcategories> response) {
                if(response.isSuccessful()){



                    Toast.makeText(Categories.this, "Succes", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(Categories.this, ""+response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Createcategories> call, Throwable t) {
                Toast.makeText(Categories.this, "Failure"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void deleteCategory(){
        Retrofit retrofit = RetrofitClient.getInstance();
        RetrofitInterface retrofitInterface1 = retrofit.create(RetrofitInterface.class);

        String get = categoryposts.get(position).getId();

        Call<Categorydelete> call = retrofitInterface1.deletecategory(get);

        call.enqueue(new Callback<Categorydelete>() {
            @Override
            public void onResponse(Call<Categorydelete> call, Response<Categorydelete> response) {
                if (response.isSuccessful()){

                    Toast.makeText(Categories.this, "Succes", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(Categories.this, ""+response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Categorydelete> call, Throwable t) {

                Toast.makeText(Categories.this, "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
