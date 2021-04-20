package com.example.restaurant_app;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.restaurant_app.Retrofit.RetrofitClient;
import com.example.restaurant_app.Retrofit.RetrofitInterface;
import com.example.restaurant_app.modeladmin.Person;
import com.example.restaurant_app.modeladmin.Staffdetails;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AdminStaffDetails extends AppCompatActivity {

    //Button backbtn;
    GridView gridView;
    Staffdetails staffdetails = new Staffdetails();
    List<Person> people = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_staff_details);

        gridView = (GridView)findViewById(R.id.gridview);

        showstaffdetails();

//        backbtn = (Button)findViewById(R.id.btnback);
//        backbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(AdminStaffDetails.this, AdminHome.class);
//                startActivity(intent);
//            }
//        });
    }

    private void showstaffdetails(){
        Retrofit retrofit = RetrofitClient.getInstance();
        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);

        Call<Staffdetails> call = retrofitInterface.Getshowstaffdetails();

        call.enqueue(new Callback<Staffdetails>() {
            @Override
            public void onResponse(Call<Staffdetails> call, Response<Staffdetails> response) {
                if(response.isSuccessful()){

                    staffdetails = response.body();
                    people = staffdetails.getPersons();

                    CustomAdepter customAdepter = new CustomAdepter(AdminStaffDetails.this,people);
                    gridView.setAdapter(customAdepter);

                    Toast.makeText(AdminStaffDetails.this, "Succes", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(AdminStaffDetails.this, ""+response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Staffdetails> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }
    class CustomAdepter extends BaseAdapter {

        List<Person> people;
        Context context;

        public CustomAdepter(AdminStaffDetails adminStaffDetails, List<Person> people) {
            this.people = people;
            this.context = adminStaffDetails;
        }

        @Override
        public int getCount() {
            return people.size();
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
                convertView = LayoutInflater.from(context).inflate(R.layout.staffdetailslayout,parent,false);
                LayoutInflater lInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
                convertView = lInflater.inflate(R.layout.staffdetailslayout, null);
            }

            TextView tname = convertView.findViewById(R.id.tname);
            TextView temail = convertView.findViewById(R.id.temail);
            TextView tphone = convertView.findViewById(R.id.tphone);

            tname.setText(people.get(position).getName());
            temail.setText(people.get(position).getEmail());
            tphone.setText(people.get(position).getPhone());

            return convertView;
        }
    }
}