package com.example.restaurant_app;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class deleteCook extends AppCompatActivity {

    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_cook);

        id = getIntent().getStringExtra("_id");

    }
}