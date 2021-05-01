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
import com.example.restaurant_app.modelmanager.setdiscount.Order;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class vieworderlist extends AppCompatActivity {

    Button btnoffer;
    EditText editText;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vieworderlist);

        btnoffer = (Button)findViewById(R.id.btnoffer);
        editText = (EditText)findViewById(R.id.edittext);
        textView = (TextView)findViewById(R.id.txt);

        btnoffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setdiscount();
            }
        });
    }

    private void setdiscount(){
        Retrofit retrofit = RetrofitClient.getInstance();
        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);

        HashMap<String, String> map = new HashMap<>();

        map.put("discount", editText.getText().toString());

        Call<Order> call = retrofitInterface.setdiscount(map);

        call.enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {
                if(response.isSuccessful()){


                    Toast.makeText(vieworderlist.this, "Succes", Toast.LENGTH_SHORT).show();
                }else {

                    Toast.makeText(vieworderlist.this, "", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Order> call, Throwable t) {
                Toast.makeText(vieworderlist.this, "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }
}