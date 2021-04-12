package com.example.restaurant_app;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class ViewCook extends AppCompatActivity {

    private Button backbtn,btnshowdata;
    RecyclerView recycle;
    TextView textView;

//    private static final String SERVER = "http://192.168.0.3:8080/cook/getcooks";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cook);

//        textView = (TextView)findViewById(R.id.textview);
        btnshowdata = (Button)findViewById(R.id.btnshowdata);

//        recycle = (RecyclerView)findViewById(R.id.recycle);
//        recycle.setHasFixedSize(true);
//        recycle.setLayoutManager(new LinearLayoutManager(this));

//        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
//        recycle.setLayoutManager(manager);

//        listingdata();

        backbtn = (Button)findViewById(R.id.btnback);
            backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewCook.this, ManagerHome.class);
                startActivity(intent);
            }
        });
        btnshowdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                HttpGetRequest request = new HttpGetRequest();
//                request.execute();
                new ParseTask().execute();
            }
        });
    }

//    private void listingdata(){
//        Retrofit retrofitclient = RetrofitClient.getInstance();
//        RetrofitInterface retrofitInterface =  retrofitclient.create(RetrofitInterface.class);
//
//        Call<List<cookdetails>> listing = retrofitInterface.Getdata();
//
//        listing.enqueue(new Callback<List<cookdetails>>() {
//            @Override
//            public void onResponse(Call<List<cookdetails>> call, Response<List<cookdetails>> response) {
//                if(response.isSuccessful()){
//                    Toast.makeText(getApplicationContext(),response.code(),Toast.LENGTH_SHORT).show();
//                    return;
////                    List<cookdetails> list = response.body();
////                    recycleAdapter recycleadapter = new recycleAdapter(ViewCook.this, list);
////                    recycle.setAdapter(recycleadapter);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<cookdetails>> call, Throwable t) {
//                Toast.makeText(getApplicationContext(),"Failure",Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

//    class recycleAdapter extends RecyclerView.Adapter<recycleAdapter.MyViewHolder>{
//
//        List<cookdetails>  list;
//        Context context;
//
//        public recycleAdapter(Context context, List<cookdetails> list){
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

//    @SuppressLint("StaticFieldLeak")
//    public class HttpGetRequest extends AsyncTask<Void, Void, String> {
//
//        static final String REQUEST_METHOD = "GET";
//        static final int READ_TIMEOUT = 15000;
//        static final int CONNECTION_TIMEOUT = 15000;
//
//        @Override
//        protected String doInBackground(Void... params){
//            String result;
//            String inputLine;
//
//            try {
//                // connect to the server
//                URL myUrl = new URL(SERVER);
//                HttpURLConnection connection =(HttpURLConnection) myUrl.openConnection();
//                connection.setRequestMethod(REQUEST_METHOD);
//                connection.setReadTimeout(READ_TIMEOUT);
//                connection.setConnectTimeout(CONNECTION_TIMEOUT);
//                connection.connect();
//
//                // get the string from the input stream
//                InputStreamReader streamReader = new InputStreamReader(connection.getInputStream());
//                BufferedReader reader = new BufferedReader(streamReader);
//                StringBuilder stringBuilder = new StringBuilder();
//                while((inputLine = reader.readLine()) != null){
//                    stringBuilder.append(inputLine);
//                }
//                reader.close();
//                streamReader.close();
//                result = stringBuilder.toString();
//
//            } catch(IOException e) {
//                e.printStackTrace();
//                result = "error";
//            }
//
//            return result;
//        }

    @SuppressLint("StaticFieldLeak")
    private class ParseTask extends AsyncTask<Void, Void, String> {

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String resultJson = "";

        @Override
        protected String doInBackground(Void... params) {
            try {
                String $url_json = "http://192.168.0.3:8080/cook/getcooks";
                URL url = new URL($url_json);

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();

                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                resultJson = buffer.toString();
                Log.d("FOR_LOG", resultJson);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return resultJson;
        }

        protected void onPostExecute(String result){
            super.onPostExecute(result);
            final ListView lView = (ListView) findViewById(R.id.lvMain);

            String[] from = {"name_item"};
            int[] to = {R.id.name_item};
            ArrayList<HashMap<String, String>> arrayList = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> hashmap;

            try {
                JSONObject json = new JSONObject(result);
                JSONArray jArray = json.getJSONArray("platform");

                for (int i = 0; i < jArray.length(); i++) {
                    JSONObject friend = jArray.getJSONObject(i);

                    String nameOS = friend.getString("name");
                    Log.d("FOR_LOG", nameOS);

                    hashmap = new HashMap<String, String>();
                    hashmap.put("name_item", "" + nameOS);
                    arrayList.add(hashmap);
                }

                final SimpleAdapter adapter = new SimpleAdapter(ViewCook.this, arrayList, R.layout.list_item, from, to);
                lView.setAdapter(adapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }
//            textView.setText(result);
        }
    }
}