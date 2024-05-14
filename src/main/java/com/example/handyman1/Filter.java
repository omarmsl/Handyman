package com.example.handyman1;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

public class Filter extends AppCompatActivity {
TextView tv;
Context con;
    EditText from, to;
    Spinner typeof, location;
    Button apply, cancel;
    JSONArray data;
    CustomAdapter customAdapter2;

    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_filter);

        from=findViewById(R.id.from);
        to = findViewById(R.id.to);
        typeof = findViewById(R.id.typeofservice);
        location = findViewById(R.id.location);
        apply = findViewById(R.id.apply);
        cancel = findViewById(R.id.cancel);
        list = findViewById(R.id.listv);
        Intent Result = new Intent();

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

             String url  = getString(R.string.url)+"filter.php?from="+from.getText()+"&to="+to.getText()+"&type="+typeof.getSelectedItem().toString()+"&city="+location.getSelectedItemId();

                RequestQueue queue = Volley.newRequestQueue(Filter.this);
                JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                    data = response;
                        Client.filtered = true;
                        Filteredjson.setJson(data);

                        Intent vvv = new Intent(Filter.this, Client.class);
                    startActivity(vvv);





                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();


                    }
                });
                queue.add(request);
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(Filter.this, Client.class));
            }
        });



    }
}