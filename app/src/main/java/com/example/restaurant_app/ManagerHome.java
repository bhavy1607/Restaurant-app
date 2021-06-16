package com.example.restaurant_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class ManagerHome extends AppCompatActivity {
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private ActionBarDrawerToggle toggle;
   // Button btn,btn1,btn2,btn3,btn4,btn5;
    ScrollView scrollView;
    CardView card1,card2,card3,card4,card5,card6;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager_home);

        scrollView = (ScrollView)findViewById(R.id.scrollView);
//        btn = (Button)findViewById(R.id.btn);
//        btn1 = (Button)findViewById(R.id.btn1);
//        btn2 = (Button)findViewById(R.id.btn2);
//        btn3 = (Button)findViewById(R.id.btn3);
//        btn4 = (Button)findViewById(R.id.btn4);
//        btn5 = (Button)findViewById(R.id.btn5);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(ManagerHome.this,Categories.class);
//                startActivity(intent);
//            }
//        });
//        btn1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(ManagerHome.this,Viewfeedback.class);
//                startActivity(intent);
//            }
//        });
//        btn2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(ManagerHome.this,ViewCook.class);
//                startActivity(intent);
//            }
//        });
//        btn3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(ManagerHome.this,Viewrevenue.class);
//                startActivity(intent);
//            }
//        });
//        btn4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(ManagerHome.this,Book_table.class);
//                startActivity(intent);
//            }
//        });
//        btn5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(ManagerHome.this,ViewWaiter.class);
//                startActivity(intent);
//            }
//        });
        card1 = (CardView)findViewById(R.id.card1);
        card2 = (CardView)findViewById(R.id.card2);
        card3 = (CardView)findViewById(R.id.card3);
        card4 = (CardView)findViewById(R.id.card4);
        card5 = (CardView)findViewById(R.id.card5);
        card6 = (CardView)findViewById(R.id.card6);
        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManagerHome.this,Categories.class);
                startActivity(intent);
            }
        });
        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManagerHome.this,Book_table.class);
                startActivity(intent);
            }
        });
        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManagerHome.this,ViewCook.class);
                startActivity(intent);
            }
        });
        card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManagerHome.this,ViewWaiter.class);
                startActivity(intent);
            }
        });
        card5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManagerHome.this,Viewrevenue.class);
                startActivity(intent);
            }
        });
        card6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManagerHome.this,Viewfeedback.class);
                startActivity(intent);
            }
        });

        drawer = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigation);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.open, R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawer.closeDrawer(GravityCompat.START);
                switch (item.getItemId()) {
                    case R.id.home:
                        break;
                    case R.id.add_cook:
                        Intent add_cook = new Intent(ManagerHome.this, AddCook.class);
                        startActivity(add_cook);
                        break;
//                    case R.id.view_cook:
//                        Intent view_cook = new Intent(ManagerHome.this, ViewCook.class);
//                        startActivity(view_cook);
//                        break;
                    case R.id.add_waiter:
                        Intent add_waiter = new Intent(ManagerHome.this, AddWaiter.class);
                        startActivity(add_waiter);
                        break;
//                    case R.id.view_waiter:
//                        Intent view_waiter = new Intent(ManagerHome.this, ViewWaiter.class);
//                        startActivity(view_waiter);
//                        break;
//                    case R.id.view_order:
//                        Intent view_order = new Intent(ManagerHome.this,ViewOrder.class);
//                        startActivity(view_order);
//                        break;
//                    case R.id.view_revenue:
//                        Intent intent = new Intent(ManagerHome.this,Viewrevenue.class);
//                        startActivity(intent);
//                        break;
                    case R.id.manage_menu:
                        Intent manage_menu = new Intent(ManagerHome.this,Manage_menu.class);
                        startActivity(manage_menu);
                        break;
//                    case R.id.categories:
//                        Intent intent1 = new Intent(ManagerHome.this,Categories.class);
//                        startActivity(intent1);
//                        break;
                    case R.id.orderhistory:
                        Intent view_payment = new Intent(ManagerHome.this, ViewOrderHistory.class);
                        startActivity(view_payment);
                        break;
//                    case R.id.booktable:
//                        Intent booktable = new Intent(ManagerHome.this,Book_table.class);
//                        startActivity(booktable);
//                        break;
                    case R.id.manage_complate:
                        Intent manage_complate = new Intent(ManagerHome.this, Manage_complain.class);
                        startActivity(manage_complate);
                        break;
                    case R.id.logout:
                        SharedPreferences preferences = getSharedPreferences("checked",MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("remember","false");
                        editor.apply();
                        finish();
                        break;

                }
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

//    private void showcategories(){
//        Retrofit retrofit = RetrofitClient.getInstance();
//        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);
//
//        Call<ShowCategories> call = retrofitInterface.showCategories();
//
//        call.enqueue(new Callback<ShowCategories>() {
//            @Override
//            public void onResponse(Call<ShowCategories> call, Response<ShowCategories> response) {
//
//                if(response.isSuccessful()){
//
//                    showCategories = response.body();
//                    categoryposts = showCategories.getCategoryposts();
//
//                    CustomAdepter customAdepter = new CustomAdepter(ManagerHome.this,categoryposts);
//                    gridView.setAdapter(customAdepter);
//
//
//                    Toast.makeText(ManagerHome.this, "Succes", Toast.LENGTH_SHORT).show();
//                }else {
//                    Toast.makeText(ManagerHome.this, ""+response.message(), Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ShowCategories> call, Throwable t) {
//                Toast.makeText(ManagerHome.this, "Failure", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//    class CustomAdepter extends BaseAdapter {
//
//        List<Categorypost> categoryposts;
//        Context context;
//
//
//
//        public CustomAdepter(ManagerHome managerHome, List<Categorypost> categoryposts) {
//            this.context = managerHome;
//            this.categoryposts = categoryposts;
//        }
//
//
//        @Override
//        public int getCount() {
//            return categoryposts.size();
//        }
//
//        @Override
//        public Object getItem(int position) {
//            return null;
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return 0;
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//
//            if (convertView == null) {
//                convertView = LayoutInflater.from(context).inflate(R.layout.ingrediantlayout,parent,false);
//                LayoutInflater lInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
//                convertView = lInflater.inflate(R.layout.ingrediantlayout, null);
//            }
//
//            TextView t1 = convertView.findViewById(R.id.catename);
//            ImageView imageView = convertView.findViewById(R.id.cateimage);
//
//
//
//            return convertView;
//        }
//    }
}

