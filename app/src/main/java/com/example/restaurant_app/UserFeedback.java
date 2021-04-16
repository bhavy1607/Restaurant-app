package com.example.restaurant_app;

import android.os.Bundle;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.restaurant_app.model.Feedback;
import com.example.restaurant_app.model.Feedbackdetails;

import java.util.ArrayList;
import java.util.List;

public class UserFeedback extends AppCompatActivity {

//    Button backbtn;
    GridView gridView;

    Feedbackdetails feedbackdetails = new Feedbackdetails();
    List<Feedback> feedbacks = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_feedback);

        gridView = (GridView)findViewById(R.id.gridview);

//        backbtn = (Button)findViewById(R.id.btnback);
//        backbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(UserFeedback.this, UserHome.class);
//                startActivity(intent);
//            }
//        });
    }

//    private void listingdata(){
//
//        Retrofit retrofit = RetrofitClient.getInstance();
//        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);
//
//        Call<Orderdetails> listing = retrofitInterface.Getorder();
//
//        listing.enqueue(new Callback<Orderdetails>() {
//            @Override
//            public void onResponse(Call<Orderdetails> call, Response<Orderdetails> response) {
//
//                feedbackdetails = response.body();
//                feedbacks = feedbackdetails.getFeedbacks();
//
//                ViewOrder.CustomAdepter customAdepter = new ViewOrder.CustomAdepter(feedbacks,ViewOrder.this);
//                gridView.setAdapter(customAdepter);
//
//            }
//
//            @Override
//            public void onFailure(Call<Orderdetails> call, Throwable t) {
//
//            }
//        });
//    }
}