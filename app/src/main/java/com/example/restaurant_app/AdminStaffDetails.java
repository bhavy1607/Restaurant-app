package com.example.restaurant_app;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class AdminStaffDetails extends AppCompatActivity {

    //Button backbtn;
//    GridView gridView;
//    Staffdetails staffdetails = new Staffdetails();
//    List<Person> people = new ArrayList<>();

    TabLayout tabLayout;
    ViewPager viewPager;
    TabItem item1, item2, item3;
    staffdetailadepter staffdetailadepter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_staff_details);

        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.viewpager);
        item1 = findViewById(R.id.tab1);
        item2 = findViewById(R.id.tab2);
        item3 = findViewById(R.id.tab3);


        staffdetailadepter = new staffdetailadepter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(staffdetailadepter);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

                if (tab.getPosition()==0 || tab.getPosition()==1 || tab.getPosition()==2)
                    staffdetailadepter.notifyDataSetChanged();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
//
//        gridView = (GridView)findViewById(R.id.gridview);
//
//        showstaffdetails();

//        backbtn = (Button)findViewById(R.id.btnback);
//        backbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(AdminStaffDetails.this, AdminHome.class);
//                startActivity(intent);
//            }
//        });
    }
}


//    private void showstaffdetails(){
//        Retrofit retrofit = RetrofitClient.getInstance();
//        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);
//
//        Call<Staffdetails> call = retrofitInterface.Getshowstaffdetails();
//
//        call.enqueue(new Callback<Staffdetails>() {
//            @Override
//            public void onResponse(Call<Staffdetails> call, Response<Staffdetails> response) {
//                if(response.isSuccessful()){
//
//                    staffdetails = response.body();
//                    people = staffdetails.getPersons();
//
//                    CustomAdepter customAdepter = new CustomAdepter(AdminStaffDetails.this,people);
//                    gridView.setAdapter(customAdepter);
//
//                    Toast.makeText(AdminStaffDetails.this, "Succes", Toast.LENGTH_SHORT).show();
//                }else {
//                    Toast.makeText(AdminStaffDetails.this, ""+response.message(), Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Staffdetails> call, Throwable t) {
//                Toast.makeText(getApplicationContext(), "Failure", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//    class CustomAdepter extends BaseAdapter {
//
//        List<Person> people;
//        Context context;
//
//        public CustomAdepter(AdminStaffDetails adminStaffDetails, List<Person> people) {
//            this.people = people;
//            this.context = adminStaffDetails;
//        }
//
//        @Override
//        public int getCount() {
//            return people.size();
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
//                convertView = LayoutInflater.from(context).inflate(R.layout.staffdetailslayout,parent,false);
//                LayoutInflater lInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
//                convertView = lInflater.inflate(R.layout.staffdetailslayout, null);
//            }
//
//            TextView tname = convertView.findViewById(R.id.tname);
//            TextView temail = convertView.findViewById(R.id.temail);
//            TextView tphone = convertView.findViewById(R.id.tphone);
//
//            tname.setText(people.get(position).getName());
//            temail.setText(people.get(position).getEmail());
//            tphone.setText(people.get(position).getPhone());
//
//            return convertView;
//        }
//    }
