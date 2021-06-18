package com.example.restaurant_app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.restaurant_app.Retrofit.RetrofitClient;
import com.example.restaurant_app.Retrofit.RetrofitInterface;
import com.example.restaurant_app.modelmanager.updatewaiter.Bodywaiterupdate;
import com.example.restaurant_app.modelmanager.updatewaiter.Waiterupdate;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class WaiterUpdateDetails extends AppCompatActivity {

    String id;
    EditText etname,etphone,etemail;
    Button btnupdate;
    RetrofitInterface retrofitInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiter_update_details);

        id = getIntent().getStringExtra("_id");
        etname = (EditText)findViewById(R.id.etname);
        etemail = (EditText)findViewById(R.id.email);
        etphone = (EditText)findViewById(R.id.phone);
        btnupdate = (Button)findViewById(R.id.btnupdate);
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatedetails();
            }
        });

    }

    private void updatedetails() {

        Retrofit retrofitClient = RetrofitClient.getInstance();
        retrofitInterface = retrofitClient.create(RetrofitInterface.class);

        String s = etname.getText().toString();
        String s1 = etphone.getText().toString()+"";
        String s2 = etemail.getText().toString();

        Bodywaiterupdate bodywaiterupdate = new Bodywaiterupdate();
        bodywaiterupdate.setName(s);
        bodywaiterupdate.setEmail(s2);
        bodywaiterupdate.setPhone(s1);

        Call<Waiterupdate> call = retrofitInterface.updatedetails(id,bodywaiterupdate);

        call.enqueue(new Callback<Waiterupdate>() {
            @Override
            public void onResponse(Call<Waiterupdate> call, Response<Waiterupdate> response) {

                if(response.isSuccessful()){

                    Toast.makeText(WaiterUpdateDetails.this, "Update Succesfully..", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(WaiterUpdateDetails.this,ManagerHome.class);
//                    startActivity(intent);
                }else {
                    Toast.makeText(WaiterUpdateDetails.this, ""+response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Waiterupdate> call, Throwable t) {
                Toast.makeText(WaiterUpdateDetails.this, "Failure", Toast.LENGTH_SHORT).show();
            }
        });

    }

}