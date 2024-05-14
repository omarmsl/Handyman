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
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class addservice extends AppCompatActivity {
EditText TTitle, PPrice, dddesc;
FloatingActionButton Done;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_addservice);
        TTitle = findViewById(R.id.TTile);
        PPrice = findViewById(R.id.PPrice);
        dddesc = findViewById(R.id.dddesc);
        Done = findViewById(R.id.done);

        Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if( TTitle.getText().equals("") || PPrice.getText().equals("") || dddesc.getText().equals("")){
                Toast.makeText(getApplicationContext(), "All fields are required, please fill them with the required information", Toast.LENGTH_SHORT).show();

            }
            else{
                String url = getString(R.string.url)+"addservice.php?Sid="+SavedID.getId()+"&price="+PPrice.getText()+"&title="+TTitle.getText()+"&description="+dddesc.getText();
                RequestQueue Queue = Volley.newRequestQueue(addservice.this);
                StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("Serice added successfuly")){
                            Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                            Intent s = new Intent(addservice.this, Service_provider.class);
                            startActivity(s);


                            }
                        else   {
                            Toast.makeText(getApplicationContext(), "Try again...", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                Queue.add(request);

            }
            }
        });




    }
}