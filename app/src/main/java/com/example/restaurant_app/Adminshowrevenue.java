package com.example.restaurant_app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.restaurant_app.Retrofit.RetrofitClient;
import com.example.restaurant_app.Retrofit.RetrofitInterface;
import com.example.restaurant_app.modelmanager.showrevenuemodel.Sumrevenue;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Adminshowrevenue extends AppCompatActivity {

    GridView gridView;
    Button btnshow;
    TextView ttotal;

    Sumrevenue sumrevenue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminshowrevenue);

        gridView = (GridView)findViewById(R.id.gridview);
        ttotal = (TextView)findViewById(R.id.ttotal);
        btnshow = (Button)findViewById(R.id.totalsum);

        btnshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTotalrevenue();
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
                     ttotal = (TextView) findViewById(R.id.ttotal);
                    ttotal.setText(sumrevenue.getGrandtotal()+"");

                    Toast.makeText(Adminshowrevenue.this, "Succes", Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(Adminshowrevenue.this, ""+response.code(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Sumrevenue> call, Throwable t) {
                Toast.makeText(Adminshowrevenue.this, "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }

}