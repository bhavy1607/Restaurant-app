package com.example.restaurant_app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.example.restaurant_app.Retrofit.RetrofitClient;
import com.example.restaurant_app.Retrofit.RetrofitInterface;
import com.example.restaurant_app.modelmanager.booktable.BookTable;
import com.example.restaurant_app.modelmanager.booktable.Table;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Book_table extends AppCompatActivity {

    GridView gridView;
    Toolbar toolbar;
    public static String id;
    Button btnback;

    BookTable bookTable = new BookTable();
    List<Table> tables = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_table);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        gridView = (GridView)findViewById(R.id.gridview);
        btnback = (Button) findViewById(R.id.btnback);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Book_table.this,ManagerHome.class);
                startActivity(intent);
            }
        });

        booktable();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.parsal,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.parsal:
                Intent intent = new Intent(Book_table.this, ViewParsal.class);
                startActivity(intent);
                break;
        }
        return true;
    }

    private void booktable(){
        Retrofit retrofit = RetrofitClient.getInstance();
        RetrofitInterface  retrofitInterface = retrofit.create(RetrofitInterface.class);

        Call<BookTable> call = retrofitInterface.showtable();

        call.enqueue(new Callback<BookTable>() {
            @Override
            public void onResponse(Call<BookTable> call, Response<BookTable> response) {
                if(response.isSuccessful()){

                    bookTable = response.body();
                    tables = bookTable.getTables();

                    CustomAdepter customAdepter = new CustomAdepter(Book_table.this,tables);
                    gridView.setAdapter(customAdepter);

                    Toast.makeText(Book_table.this, "Succes", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(Book_table.this, ""+response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BookTable> call, Throwable t) {
                Toast.makeText(Book_table.this, "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }

     class CustomAdepter extends BaseAdapter {

        List<Table> tables;
        Context context;

         public CustomAdepter(Book_table book_table, List<Table> tables) {
             this.context = book_table;
             this.tables = tables;
         }

         @Override
        public int getCount() {
            return   tables.size();
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
                view = LayoutInflater.from(context).inflate(R.layout.booktablelayout,viewGroup,false);
                LayoutInflater lInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
                view = lInflater.inflate(R.layout.booktablelayout, null);
            }

           TextView ttable = view.findViewById(R.id.ttable);
            TextView tname = view.findViewById(R.id.tname);
            TextView tphone = view.findViewById(R.id.tphone);
            TextView tstatus = view.findViewById(R.id.tstatus);
            //Button btn = view.findViewById(R.id.btn);
            CardView cardView = view.findViewById(R.id.cardview);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String get = tables.get(i).getId();
                    Intent intent = new Intent(Book_table.this, TableWiseOrder.class);
                    intent.putExtra("_id",tables.get(i).getId());
                    startActivity(intent);
                }
            });

            ttable.setText(tables.get(i).getTable().toString());
            tname.setText(tables.get(i).getUserName());
            tstatus.setText(tables.get(i).getStatus());
            tphone.setText(tables.get(i).getPhone());

            return view;
        }
    }

}