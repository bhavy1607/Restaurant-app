package com.example.restaurant_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ViewOrder extends AppCompatActivity {

    private Button backbtn;
    RecyclerView recycle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order);
        backbtn = (Button)findViewById(R.id.btnback);

        recycle = (RecyclerView)findViewById(R.id.recycle);
        recycle.setHasFixedSize(true);
        recycle.setLayoutManager(new LinearLayoutManager(this));

        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        recycle.setLayoutManager(manager);

        //listingdata();

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewOrder.this, ManagerHome.class);
                startActivity(intent);
            }
        });
    }
//    private void listingdata(){
//        Retrofit retrofitclient = RetrofitClient.getInstance();
//        RetrofitInterface retrofitInterface =  retrofitclient.create(RetrofitInterface.class);
//
//        Call<cookdetails> listing = retrofitInterface.Getorder();
//
//        listing.enqueue(new Callback<cookdetails>() {
//            @Override
//            public void onResponse(Call<cookdetails> call, Response<cookdetails> response) {
//                if(response.isSuccessful()){
//
////                    cookdetails Cookdetails = new cookdetails();
////                    Cookdetails = response.body();
//                    Toast.makeText(ViewOrder.this, "Success", Toast.LENGTH_SHORT).show();
//
//
//
//                }else{
//                    Toast.makeText(ViewOrder.this, ""+response.message(), Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<cookdetails> call, Throwable t) {
//                Toast.makeText(getApplicationContext(),"Failure",Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//    class recycleAdapter extends RecyclerView.Adapter<recycleAdapter.MyViewHolder>{
//
//        List<Cook> list;
//        Context context;
//
//        public recycleAdapter(Context context, List<Cook> list){
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
//            }
//        }
//    }
}