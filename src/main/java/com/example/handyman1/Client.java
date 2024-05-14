package com.example.handyman1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;

public class Client extends AppCompatActivity {
public static ListView lv;
JSONArray datav, dat;
static boolean filtered = false;

CustomAdapter customAdapter, customAdapter2;

FloatingActionButton filter, editcuser, mylog;
SearchView searchView;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_client);
        lv = findViewById(R.id.listv);
        mylog= findViewById(R.id.mylog) ;
        filter = findViewById(R.id.filter1);
    editcuser = findViewById(R.id.editcuser);
    mylog.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(Client.this, mylog.class));
        }
    });

filter.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(Client.this, Filter.class);
        startActivity(intent);

    }
});

        editcuser.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent setuser = new Intent(Client.this, userprofile.class);


        startActivity(setuser);
    }
        });
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(Client.this, Filter.class));

            }
        });

if (!filtered) {
    getdatafromdb();


}
else {
    Toast.makeText(getApplicationContext(), Filteredjson.getJson().toString(), Toast.LENGTH_SHORT).show();
    dat = Filteredjson.getJson();
    customAdapter2 = new CustomAdapter(Client.this, dat);
    lv.setAdapter(customAdapter2);
}
    }




    public JSONArray getdatafromdb(){

        String url = getString(R.string.url)+"allservices.php";
        RequestQueue queue = Volley.newRequestQueue(Client.this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                datav = response;
               customAdapter = new CustomAdapter(Client.this, datav);
                lv.setAdapter(customAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error:" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(jsonArrayRequest);


return datav;
    }

}