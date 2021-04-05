package com.example.restaurant_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.restaurant_app.Retrofit.RetrofitClient;
import com.example.restaurant_app.Retrofit.RetrofitInterface;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AdminLogin extends AppCompatActivity {
    Button login_btn;
    EditText email, password;
    RetrofitInterface retrofitInterface;
    TextView forgot_password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_login);

        Retrofit retrofitClient = RetrofitClient.getInstance();
        retrofitInterface = retrofitClient.create(RetrofitInterface.class);


        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        login_btn =(Button) findViewById(R.id.login_btn);
        forgot_password = (TextView) findViewById(R.id.forgot_password);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (email.getText().toString().isEmpty()){
                    Toast.makeText(AdminLogin.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                }
                if (password.getText().toString().isEmpty()){
                    Toast.makeText(AdminLogin.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                }

                HashMap<String, String> map = new HashMap<>();

                map.put("email", email.getText().toString());
                map.put("password",password.getText().toString());

                Call<LoginResult> call = retrofitInterface.executeAdminLogin(map);

                call.enqueue(new Callback<LoginResult>() {
                    @Override
                    public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {

                        if (response.code() == 200) {

                            LoginResult result = response.body();

                            AlertDialog.Builder builder1 = new AlertDialog.Builder(AdminLogin.this);
                            builder1.setTitle(result.getEmail());
                            builder1.setMessage(result.getPassword());
                            builder1.show();
                            Toast.makeText(AdminLogin.this, "Login Success",
                                    Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(AdminLogin.this, AdminHome.class);
                            startActivity(intent);

                        } else if (response.code() == 401) {
                            Toast.makeText(AdminLogin.this, "Wrong Credentials",
                                    Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<LoginResult> call, Throwable t) {
                        Toast.makeText(AdminLogin.this, "Please! Check Network of Your Device",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent m  = new Intent(AdminLogin.this,AdminForgotPassword.class);
                startActivity(m);
            }
        });
    }
}
