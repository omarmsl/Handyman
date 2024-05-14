package com.example.handyman1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

public class mylog extends AppCompatActivity {
    JSONArray datah;
    CustomAdapter3 customAdapter3;

    ListView listt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mylog);

        listt = findViewById(R.id.listtt);

        String url = getString(R.string.url)+"myservicesclient.php?userid="+SavedID.getId();
        RequestQueue queue = Volley.newRequestQueue(mylog.this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
            datah = response;
            customAdapter3 = new CustomAdapter3(mylog.this, datah);
            listt.setAdapter(customAdapter3);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(jsonArrayRequest);
    }
}