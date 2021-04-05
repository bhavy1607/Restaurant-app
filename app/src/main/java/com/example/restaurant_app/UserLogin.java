package com.example.restaurant_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
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

public class UserLogin extends AppCompatActivity {

    ImageView logo_image;
    EditText email, password;
    TextView forgot_password, admin_panel_link, manager_panel_link, cook_panel_link, waiter_panel_link;
    Button sign_up, login_btn;
    private CheckBox rememberMe;
    RetrofitInterface retrofitInterface;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_login);

        //Init Services

        Retrofit retrofitClient = RetrofitClient.getInstance();
        retrofitInterface = retrofitClient.create(RetrofitInterface.class);

        logo_image = (ImageView) findViewById(R.id.logo_image);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        forgot_password = (TextView) findViewById(R.id.forgot_password);
        admin_panel_link = (TextView) findViewById(R.id.admin_panel_link);
        cook_panel_link = (TextView) findViewById(R.id.cook_panel_link);
        manager_panel_link = (TextView) findViewById(R.id.manager_panel_link);
        waiter_panel_link = (TextView) findViewById(R.id.waiter_panel_link);
        sign_up = (Button) findViewById(R.id.sign_up);
        login_btn = (Button) findViewById(R.id.login_btn);
        rememberMe = (CheckBox) findViewById(R.id.rememberMe);

        SharedPreferences preferences = getSharedPreferences("checked",MODE_PRIVATE);
        String checkbox = preferences.getString("remember","");
        if (checkbox.equals("true")) {
            Intent intent = new Intent(UserLogin.this, UserHome.class);
            startActivity(intent);
        }else if(checkbox.equals("false")){
            Toast.makeText(UserLogin.this, "Please Sign in...", Toast.LENGTH_SHORT).show();

        }

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UserLogin.this, UserRegister.class);
                startActivity(i);
            }
        });

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email.getText().toString().isEmpty()) {
                    Toast.makeText(UserLogin.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                }
                if (password.getText().toString().isEmpty()) {
                    Toast.makeText(UserLogin.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                }

                HashMap<String, String> map = new HashMap<>();

                map.put("email", email.getText().toString());
                map.put("password", password.getText().toString());

                Call<LoginResult> call = retrofitInterface.executeLogin(map);

                call.enqueue(new Callback<LoginResult>() {
                    @Override
                    public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {

                        if (response.code() == 200) {

                            LoginResult result = response.body();

                            AlertDialog.Builder builder1 = new AlertDialog.Builder(UserLogin.this);
                            builder1.setTitle(result.getEmail());
                            builder1.setMessage(result.getPassword());
                            builder1.show();
                            Toast.makeText(UserLogin.this, "Login Success",
                                    Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(UserLogin.this, UserHome.class);
                            startActivity(intent);

                        } else if (response.code() == 401) {
                            Toast.makeText(UserLogin.this, "Wrong Credentials",
                                    Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<LoginResult> call, Throwable t) {
                        Toast.makeText(UserLogin.this, "Please! Check Network of Your Device",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        rememberMe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (compoundButton.isChecked()){
                    SharedPreferences preferences = getSharedPreferences("checked",MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember","true");
                    editor.apply();

                }else if (!compoundButton.isChecked()){
                    SharedPreferences preferences = getSharedPreferences("checked",MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember","false");
                    editor.apply();
                }
            }
        });

        admin_panel_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UserLogin.this, AdminLogin.class);
                startActivity(i);
            }
        });

        manager_panel_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(UserLogin.this, ManagerLogin.class);
                startActivity(j);
            }
        });

        cook_panel_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k = new Intent(UserLogin.this, CookLogin.class);
                startActivity(k);
            }
        });

        waiter_panel_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent l = new Intent(UserLogin.this, WaiterLogin.class);
                startActivity(l);
            }
        });

        forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent m = new Intent(UserLogin.this, UserForgetPassword.class);
                startActivity(m);
            }
        });
    }
}