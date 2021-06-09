package com.example.restaurant_app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.restaurant_app.Retrofit.RetrofitClient;
import com.example.restaurant_app.Retrofit.RetrofitInterface;
import com.example.restaurant_app.modelmanager.managecomplain.Complaint;
import com.example.restaurant_app.modelmanager.managecomplain.Getcomplate;
import com.example.restaurant_app.modelmanager.replaycomplain.BodyreplayComplain;
import com.example.restaurant_app.modelmanager.replaycomplain.Replaycomplain;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class  ComplainReply extends AppCompatActivity {

    EditText editText;
    Button btnreply;
    String id;

    Getcomplate getcomplate = new Getcomplate();
    List<Complaint> complaints = new ArrayList<>();
    private int position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complain_reply);

        editText = (EditText)findViewById(R.id.etreply);
        btnreply = (Button)findViewById(R.id.btnreply);
        id = getIntent().getStringExtra("_id");


        btnreply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getreplaycomplain();

            }
        });
    }

    private void getreplaycomplain(){
        Retrofit retrofit = RetrofitClient.getInstance();
        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);


        String message = editText.getText().toString();

        BodyreplayComplain bodyreplayComplain = new BodyreplayComplain();
        bodyreplayComplain.setMessage(message);

        Call<Replaycomplain> call = retrofitInterface.GetReplay(id,bodyreplayComplain);

        call.enqueue(new Callback<Replaycomplain>() {
            @Override
            public void onResponse(Call<Replaycomplain> call, Response<Replaycomplain> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Success Sent to User...", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(), ""+response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Replaycomplain> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }

}