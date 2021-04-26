package com.example.restaurant_app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.restaurant_app.Retrofit.RetrofitClient;
import com.example.restaurant_app.Retrofit.RetrofitInterface;
import com.example.restaurant_app.modelmanager.Showrevenue;
import com.example.restaurant_app.modelmanager.Sumrevenue;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Viewrevenue extends AppCompatActivity {

    EditText et1,et2;
    Button btntotal,btnshowrevenue;
    TextView t1,t2;

    Sumrevenue sumrevenue;
    Showrevenue showrevenue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewrevenue);

        et1 = (EditText)findViewById(R.id.et1);
        et2 = (EditText)findViewById(R.id.et2);
        btnshowrevenue = (Button)findViewById(R.id.showrevenue);
        btntotal = (Button)findViewById(R.id.totalsum);
        t1 = (TextView)findViewById(R.id.ttotal);
        t2 = (TextView)findViewById(R.id.tshowrevenue);


        btntotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTotalrevenue();
            }
        });
        btnshowrevenue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               showrevenue();
               // Toast.makeText(Viewrevenue.this, "......", Toast.LENGTH_SHORT).show();
            }
        });
    }
    
    private void showTotalrevenue(){
        Retrofit retrofit = RetrofitClient.getInstance();
        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);

        Call<Sumrevenue> call = retrofitInterface.GetTotalRevenue();

        call.enqueue(new Callback<Sumrevenue>() {
            @Override
            public void onResponse(Call<Sumrevenue> call, Response<Sumrevenue> response) {
                if(response.isSuccessful()){

                    sumrevenue = response.body();
                    t1 = (TextView) findViewById(R.id.ttotal);
                    t1.setText(sumrevenue.getGrandtotal()+"");

                    Toast.makeText(Viewrevenue.this, "Succes", Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(Viewrevenue.this, ""+response.code(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Sumrevenue> call, Throwable t) {
                Toast.makeText(Viewrevenue.this, "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }

//    private void showRevenue(){
//        Retrofit retrofit = RetrofitClient.getInstance();
//        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);
//
//        //HashMap<String, String> map = new HashMap<>();
//
//       // map.put("year",et1.getText().toString());
//        Call<Showrevenue> call = retrofitInterface.GetShowRevenue();
//
//        call.enqueue(new Callback<Showrevenue>() {
//            @Override
//            public void onResponse(Call<Showrevenue> call, Response<Showrevenue> response) {
//                if(response.isSuccessful()){
//
//
//                    showrevenue = (Showrevenue) response.body();
//                    t1.setText(showrevenue.getSum()+"");
//
//                    Toast.makeText(Viewrevenue.this, "Succes", Toast.LENGTH_SHORT).show();
//                }else {
//                    Toast.makeText(Viewrevenue.this, ""+response.code(), Toast.LENGTH_SHORT).show();
//                }
//            }
//            @Override
//            public void onFailure(Call<Showrevenue> call, Throwable t) {
//                Toast.makeText(Viewrevenue.this, "Failure", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

    private void showrevenue(){
        Retrofit retrofit = RetrofitClient.getInstance();
        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);

        Call<Showrevenue> call = retrofitInterface.GetShowRevenue();

        call.enqueue(new Callback<Showrevenue>() {
            @Override
            public void onResponse(Call<Showrevenue> call, Response<Showrevenue> response) {
                if(response.isSuccessful()){

                    showrevenue = response.body();
                    t2 = (TextView) findViewById(R.id.tshowrevenue);
                    t2.setText(sumrevenue.getGrandtotal()+"");

                    Toast.makeText(Viewrevenue.this, "Succes", Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(Viewrevenue.this, ""+response.code(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Showrevenue> call, Throwable t) {
                Toast.makeText(Viewrevenue.this, "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }

}