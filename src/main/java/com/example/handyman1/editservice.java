package com.example.handyman1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class editservice extends AppCompatActivity {
EditText TTitle2, PPrice2, ddddesc2;
FloatingActionButton done2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_editservice);
        TTitle2 = findViewById(R.id.TTile2);
        PPrice2 = findViewById(R.id.PPrice2);
        ddddesc2 = findViewById(R.id.dddesc2);
        done2 = findViewById(R.id.done2);
        Intent recei = getIntent();
       String Sid = recei.getStringExtra("Sid");

        done2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = getString(R.string.url)+"editService.php?Sid="+Sid+"&price="+PPrice2.getText()+"&title="+TTitle2.getText()+"&description="+ddddesc2.getText();
                RequestQueue queue = Volley.newRequestQueue(editservice.this);
                StringRequest str = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Try again...", Toast.LENGTH_SHORT).show();
                    }
                });
                queue.add(str);
            }
        });


    }
}