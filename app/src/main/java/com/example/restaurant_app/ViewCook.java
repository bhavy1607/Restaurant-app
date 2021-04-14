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
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurant_app.Retrofit.RetrofitClient;
import com.example.restaurant_app.Retrofit.RetrofitInterface;
import com.example.restaurant_app.model.Cook;
import com.example.restaurant_app.model.Cookdetails;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ViewCook extends AppCompatActivity {

    private Button backbtn;
    RecyclerView recycle;
    GridView gridView;

    Cookdetails cookdetails = new Cookdetails();
    List<Cook> cooks = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cook);

        gridView = (GridView)findViewById(R.id.gridview);
//        recycle = (RecyclerView) findViewById(R.id.recycle);
//        recycle.setHasFixedSize(true);
//        recycle.setLayoutManager(new LinearLayoutManager(this));

//        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
//        gridView.setLayoutManager(manager);

        listingdata();

        backbtn = (Button) findViewById(R.id.btnback);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewCook.this, ManagerHome.class);
                startActivity(intent);
            }
        });
    }

    private void listingdata() {
        Retrofit retrofitclient = RetrofitClient.getInstance();
        RetrofitInterface retrofitInterface = retrofitclient.create(RetrofitInterface.class);

        Call<Cookdetails> listing = retrofitInterface.Getcook();

        listing.enqueue(new Callback<Cookdetails>() {

            @Override
            public void onResponse(Call<Cookdetails> call, Response<Cookdetails> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(ViewCook.this, "Success", Toast.LENGTH_SHORT).show();

                    cookdetails = response.body();
                    cooks = cookdetails.getCooks();

                    CustomAdepter customAdepter = new CustomAdepter(cooks,ViewCook.this);
                    gridView.setAdapter(customAdepter);

                } else {
                    Toast.makeText(ViewCook.this, "" + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Cookdetails> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }

//        class recycleAdapter extends RecyclerView.Adapter<recycleAdapter.MyViewHolder>{
//
//        List<Cook> list;
//        Context context;
//
//        public recycleAdapter(Context context, Cook list){
//            this.list = list;
//            this.context = context;
//        }
//
//        @NonNull
//        @Override
//        public recycleAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//            View view = LayoutInflater.from(context).inflate(R.layout.cardlayout,parent,false);
//            recycleAdapter.MyViewHolder viewHolder = new recycleAdapter.MyViewHolder(view);
//            return viewHolder;
//        }
//
//        @Override
//        public void onBindViewHolder(@NonNull recycleAdapter.MyViewHolder holder, int position) {
//
//            holder.tname.setText(list.get(position).getName());
//            holder.temail.setText(list.get(position).getEmail());
//            holder.tphone.setText(list.get(position).getPhone());
//        }
//
//        @Override
//        public int getItemCount() {
//            return list.size();
//        }
//        class MyViewHolder extends RecyclerView.ViewHolder{
//
//            TextView tname,temail,tphone;
//
//            public MyViewHolder(@NonNull View itemView) {
//                super(itemView);
//
//                tname = itemView.findViewById(R.id.tname);
//                temail = itemView.findViewById(R.id.temail);
//                tphone = itemView.findViewById(R.id.tphone);
//
//            }
//        }
//    }
static class CustomAdepter extends BaseAdapter {

        List<Cook> cookList;
        private Context context;

    public CustomAdepter(List<Cook> cooks, ViewCook viewCook) {
        this.context = context;
        this.cookList = cookList;
    }

    @Override
        public int getCount() {
            return cookList.size();
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
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                LayoutInflater lInflater = (LayoutInflater) context.getSystemService(
                        Activity.LAYOUT_INFLATER_SERVICE);
                view = lInflater.inflate(R.layout.cardlayout, null);
            }

            TextView tname = view.findViewById(R.id.tname);
            TextView temail = view.findViewById(R.id.temail);
            TextView tphone = view.findViewById(R.id.tphone);
            tname.setText(cookList.get(i).getName());
            temail.setText(cookList.get(i).getEmail());
            tphone.setText(cookList.get(i).getPhone());

            return view;
        }
    }
}
