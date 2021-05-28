package com.tw.mobile_app_test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener{

    MyRecyclerViewAdapter adapter;
    ArrayList<DataModel> formList = new ArrayList<DataModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        readJsonFile();
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getApplicationContext().getAssets().open("data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public void readJsonFile(){
        try {
            JSONArray jsonArray = new JSONArray(loadJSONFromAsset());
            HashMap<String, String> m_li;

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jo_inside = jsonArray.getJSONObject(i);
                Log.d("Details-->", jo_inside.getString("id"));

                DataModel model = new DataModel();
                model.setId(jo_inside.getString("id"));
                model.setFirstName(jo_inside.getString("firstName"));
                model.setLastName(jo_inside.getString("lastName"));
                if(jo_inside.has("email")){
                    model.setEmail(jo_inside.getString("email"));
                }
                if (jo_inside.has("phone")){
                    model.setPhone(jo_inside.getString("phone"));
                }

                formList.add(model);
            }

            Log.e("TAG","my mesagge " + formList.toString());

            // set up the RecyclerView
            RecyclerView recyclerView = findViewById(R.id.rv_list);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            adapter = new MyRecyclerViewAdapter(this, formList);
            adapter.setClickListener(this);
            recyclerView.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent i = new Intent(this,Screen2Activity.class);
        i.putExtra("fName" , formList.get(position).getFirstName());
        i.putExtra("lName" , formList.get(position).getLastName());
        i.putExtra("email" , formList.get(position).getEmail());
        i.putExtra("phone" , formList.get(position).getPhone());
        startActivity(i);
    }

}