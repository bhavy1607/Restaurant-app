package com.example.restaurant_app;

import android.os.Bundle;
import android.widget.Button;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

public class Adminshowrevenue extends AppCompatActivity {

    GridView gridView;
    Button btnshow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminshowrevenue);

        gridView = (GridView)findViewById(R.id.gridview);
    }
}