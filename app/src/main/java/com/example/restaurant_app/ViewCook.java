package com.example.restaurant_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurant_app.Retrofit.RetrofitClient;
import com.example.restaurant_app.Retrofit.RetrofitInterface;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ViewCook extends AppCompatActivity {

    private Button backbtn;
    RecyclerView recycle;
    TextView viewcook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cook);

        viewcook = (TextView)findViewById(R.id.view_cook_list);
        recycle = (RecyclerView)findViewById(R.id.recycle);

        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        recycle.setLayoutManager(manager);

        backbtn = (Button)findViewById(R.id.btnback);
            backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewCook.this, ManagerHome.class);
                startActivity(intent);
            }
        });

        viewcook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listingdata();
            }
        });
    }

    private void listingdata(){
        Retrofit retrofitClient = RetrofitClient.getInstance();
        RetrofitInterface retrofitInterface = retrofitClient.create(RetrofitInterface.class);

        HashMap<String, String> map = new HashMap<>();

        Call<RegisterResult> listingdata = retrofitInterface.Getdata();
        listingdata.enqueue(new Callback<RegisterResult>() {
            @Override
            public void onResponse(Call<RegisterResult> call, Response<RegisterResult> response) {
                if(response.isSuccessful()){
//                    recycleadapter recycleadapter = new recycleadapter((List<RegisterResult>) retrofitInterface.Getdata());
//                    recycle.setAdapter(recycleadapter);

                    RegisterResult list = response.body();
                    recycleadapter recycleadapter = new recycleadapter(ViewCook.this, Collections.singletonList(list));
                    recycle.setAdapter(recycleadapter);

                }
            }

            @Override
            public void onFailure(Call<RegisterResult> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Failure",Toast.LENGTH_SHORT).show();
            }
        });
    }

    class recycleadapter extends RecyclerView.Adapter<recycleadapter.MyViewHolder>{

        List<RegisterResult>  list;

        public recycleadapter(ViewCook viewCook, List<RegisterResult> list){
            this.list = list;
        }


        @NonNull
        @Override
        public recycleadapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardlayout,null);
            recycleadapter.MyViewHolder viewHolder = new recycleadapter.MyViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull recycleadapter.MyViewHolder holder, int position) {
            holder.tname.setText(list.get(position).getName());
            holder.temail.setText(list.get(position).getEmail());
            holder.tphone.setText(list.get(position).getPhone());
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
        class MyViewHolder extends RecyclerView.ViewHolder{

            TextView tname,temail,tphone;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);

                tname = itemView.findViewById(R.id.tname);
                temail = itemView.findViewById(R.id.temail);
                tphone = itemView.findViewById(R.id.tphone);
            }
        }
    }
}