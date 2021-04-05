package com.example.restaurant_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.restaurant_app.Retrofit.RetrofitClient;
import com.example.restaurant_app.Retrofit.RetrofitInterface;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AdminForgotPassword extends AppCompatActivity {
    EditText email;
    Button send_email;
    RetrofitInterface retrofitInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_forgot_password);
        Retrofit retrofitClient = RetrofitClient.getInstance();
        retrofitInterface = retrofitClient.create(RetrofitInterface.class);

        email = (EditText) findViewById(R.id.email);
        send_email = (Button) findViewById(R.id.send_email);

        send_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HashMap<String, String> map = new HashMap<>();
                map.put("email", email.getText().toString());

                Call<ForgotResult> call = retrofitInterface.executeforgotpass(map);
                call.enqueue(new Callback<ForgotResult>() {
                    @Override
                    public void onResponse(Call<ForgotResult> call, Response<ForgotResult> response) {
                        if (response.code() == 200) {
                            Toast.makeText(AdminForgotPassword.this,
                                    "Check Your Email for Reset Password", Toast.LENGTH_LONG).show();

                            startActivity(new Intent(AdminForgotPassword.this, UserLogin.class));

                        } else if (response.code() == 401) {
                            Toast.makeText(AdminForgotPassword.this,
                                    "Email is not found", Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<ForgotResult> call, Throwable t) {
                        Toast.makeText(AdminForgotPassword.this, "Please! Check Network of Your Device",
                                Toast.LENGTH_LONG).show();

                    }
                });
            }
        });
    }
}
