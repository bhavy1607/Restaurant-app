package com.example.restaurant_app;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.restaurant_app.Retrofit.RetrofitClient;
import com.example.restaurant_app.Retrofit.RetrofitInterface;
import com.example.restaurant_app.modelmanager.feedback.Avaragerating;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RatingAvarage extends AppCompatActivity {

    GridView gridView;
    Avaragerating avarageratings;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ratingavarage);

        gridView = (GridView)findViewById(R.id.gridview);
        TextView tv_av = (TextView)findViewById(R.id.tv_ava_rating);

        ratingdata();

    }

    private void ratingdata(){

        Retrofit retrofit = RetrofitClient.getInstance();
        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);

        Call<Avaragerating> call = retrofitInterface.Getavaragerating();

        call.enqueue(new Callback<Avaragerating>() {

            @Override
            public void onResponse(Call<Avaragerating> call, Response<Avaragerating> response) {
                if(response.isSuccessful()){

                    avarageratings = response.body();
                    TextView tv_av = (TextView)findViewById(R.id.tv_ava_rating);
                    tv_av.setText(avarageratings.getRating()+"");

                    Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(getApplicationContext(), ""+response.message(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Avaragerating> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Failure"+t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

//    class CustomAdepter extends BaseAdapter {
//
//        Avaragerating avarageratings;
//        Context context;
//
//        public CustomAdepter(Avaragerating avaragerating, RatingAvarage ratingAvarage) {
//            this.avarageratings = avaragerating;
//            this.context = ratingAvarage;
//        }
//
//        @Override
//        public int getCount() {
//            return 0;
//        }
//
//        @Override
//        public Object getItem(int position) {
//            return null;
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return 0;
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//
//            if (convertView == null) {
//                convertView = LayoutInflater.from(context).inflate(R.layout.ratinglayout,parent,false);
//                LayoutInflater lInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
//                convertView = lInflater.inflate(R.layout.ratinglayout, null);
//            }
//
//            TextView t1 = convertView.findViewById(R.id.t1);
//
//            t1.setText(avarageratings.getRating());
//
//            return convertView;
//        }
//    }
}
