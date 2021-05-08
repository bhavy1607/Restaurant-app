package com.example.restaurant_app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.restaurant_app.Retrofit.RetrofitClient;
import com.example.restaurant_app.Retrofit.RetrofitInterface;
import com.example.restaurant_app.modelmanager.feedback.Feedback;
import com.example.restaurant_app.modelmanager.feedback.Feedbackdetails;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AdminShowFeedback extends AppCompatActivity {

    GridView gridView;
    Button  btn;

    Feedbackdetails feedbackdetails = new Feedbackdetails();
    List<Feedback> feedbacks = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_show_feedback);

        gridView = (GridView)findViewById(R.id.gridview);
        btn = (Button)findViewById(R.id.btnavarageRating);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),RatingAvarage.class);
                startActivity(intent);
            }
        });

        listingdata();

    }
    private void listingdata(){
        Retrofit retrofitclient = RetrofitClient.getInstance();
        RetrofitInterface retrofitInterface =  retrofitclient.create(RetrofitInterface.class);

        Call<Feedbackdetails> listing = retrofitInterface.Getfeedback();

        listing.enqueue(new Callback<Feedbackdetails>() {
            @Override
            public void onResponse(Call<Feedbackdetails> call, Response<Feedbackdetails> response) {
                if(response.isSuccessful()) {

                    feedbackdetails = response.body();
                    feedbacks = feedbackdetails.getFeedbacks();

                    CustomAdepter customAdepter = new CustomAdepter(feedbacks, AdminShowFeedback.this);
                    gridView.setAdapter(customAdepter);

                    Toast.makeText(AdminShowFeedback.this, "Success", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(AdminShowFeedback.this, ""+response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Feedbackdetails> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Failure",Toast.LENGTH_SHORT).show();
            }
        });
    }

    class CustomAdepter extends BaseAdapter {

        List<Feedback> feedbacks;
        Context context;


        public CustomAdepter(List<Feedback> feedbacks, AdminShowFeedback adminShowFeedback) {
            this.feedbacks = feedbacks;
            this.context = adminShowFeedback;
        }

        @Override
        public int getCount() {
            return feedbacks.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.feedbacklayout,parent,false);
                LayoutInflater lInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
                convertView = lInflater.inflate(R.layout.feedbacklayout, null);
            }

            TextView tid = convertView.findViewById(R.id.tid);
            TextView ttitle = convertView.findViewById(R.id.ttitle);
            TextView tmessage = convertView.findViewById(R.id.tmessage);

            tid.setText(feedbacks.get(position).getId());
            tmessage.setText(feedbacks.get(position).getMessage());
            ttitle.setText(feedbacks.get(position).getTitle());

            return convertView;
        }
    }
    }
