package com.example.restaurant_app;

import android.content.Context;
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

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ViewCook extends AppCompatActivity {

    private Button backbtn;
    RecyclerView recycle;
//    TextView viewcook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cook);

//        viewcook = (TextView)findViewById(R.id.view_cook_list);
        recycle = (RecyclerView)findViewById(R.id.recycle);
        recycle.setHasFixedSize(true);
        recycle.setLayoutManager(new LinearLayoutManager(this));

        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        recycle.setLayoutManager(manager);

        listingdata();

        backbtn = (Button)findViewById(R.id.btnback);
            backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewCook.this, ManagerHome.class);
                startActivity(intent);
            }
        });

//        viewcook.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//            }
//        });
    }

    private void listingdata(){
        Retrofit retrofitclient = RetrofitClient.getInstance();
        RetrofitInterface retrofitInterface =  retrofitclient.create(RetrofitInterface.class);

        Call<List<cookdetails>> listingdata = retrofitInterface.Getdata();

        listingdata.enqueue(new Callback<List<cookdetails>>() {
            @Override
            public void onResponse(Call<List<cookdetails>> call, Response<List<cookdetails>> response) {
                if(response.code() == 200){

                    List<cookdetails> list = response.body();
                    recycleadapter recycleadapter = new recycleadapter(ViewCook.this, list);
                    recycle.setAdapter(recycleadapter);
                }
            }

            @Override
            public void onFailure(Call<List<cookdetails>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Failure",Toast.LENGTH_SHORT).show();
            }
        });
    }

    class recycleadapter extends RecyclerView.Adapter<recycleadapter.MyViewHolder>{

        List<cookdetails>  list;
        Context context;

        public recycleadapter(Context context, List<cookdetails> list){
            this.list = list;
            this.context = context;
        }

        @NonNull
        @Override
        public recycleadapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardlayout,parent,false);
            recycleadapter.MyViewHolder viewHolder = new recycleadapter.MyViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull recycleadapter.MyViewHolder holder, int position) {
            holder.tname.setText(list.get(position).getName());
            holder.tid.setText(list.get(position).getId());
            holder.tdec.setText(list.get(position).getDesc());
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
        class MyViewHolder extends RecyclerView.ViewHolder{

            TextView tname,tid,tdec;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);

                tname = itemView.findViewById(R.id.tname);
                tid = itemView.findViewById(R.id.tid);
                tdec = itemView.findViewById(R.id.tdec);
            }
        }
    }
}