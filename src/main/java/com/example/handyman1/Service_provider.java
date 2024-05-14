package com.example.handyman1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;

public class Service_provider extends AppCompatActivity {
    ListView listView;
    JSONArray data ;
   TextView textview13;
    CustomAdapter2 customAdapter;
    FloatingActionButton addService, userProfile;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_service_provider);
        textview13 = findViewById(R.id.textView13);
        addService = findViewById(R.id.addservice);
        userProfile = findViewById(R.id.editprofile);

        Intent iv = getIntent();

       textview13.setText(iv.getStringExtra("seat1"));


        listView = findViewById(R.id.listvv);
getdatafromdb();
addService.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent add = new Intent(Service_provider.this, addservice.class);
        startActivity(add);
    }
});
userProfile.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent prof = new Intent(Service_provider.this, userprofile.class);

        startActivity(prof);
    }
});


    }
    public void onResume() {
        getdatafromdb();
        super.onResume();
    }
    public void getdatafromdb(){
        String userid = SavedID.getId();
        String url = getString(R.string.url)+"myservices.php?userid="+userid;
        RequestQueue queue = Volley.newRequestQueue(Service_provider.this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                data = response;

                customAdapter = new CustomAdapter2(Service_provider.this, data);
                listView.setAdapter(customAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error:" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(jsonArrayRequest);



    }
    }
