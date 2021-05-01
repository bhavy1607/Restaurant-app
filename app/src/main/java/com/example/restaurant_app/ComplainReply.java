package com.example.restaurant_app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ComplainReply extends AppCompatActivity {

    EditText editText;
    Button btnreply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complain_reply);

        editText = (EditText)findViewById(R.id.etreply);
        btnreply = (Button)findViewById(R.id.btnreply);

        btnreply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ComplainReply.this, "succesfully sent to user...", Toast.LENGTH_SHORT).show();
            }
        });
    }
}