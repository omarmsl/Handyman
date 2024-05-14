package com.example.handyman1;





import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class userprofile extends AppCompatActivity {

    EditText first, last , number , emmail;
    Spinner cityy;
    FloatingActionButton Updateuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_userprofile);

Updateuser = findViewById(R.id.updateuser);
        first = findViewById(R.id.first);
        last = findViewById(R.id.last);
        number = findViewById(R.id.number);
        emmail = findViewById(R.id.emmail);
        cityy = findViewById(R.id.cityy);
        ArrayAdapter<CharSequence> cities = ArrayAdapter.createFromResource(this,
                R.array.address, android.R.layout.simple_spinner_item);
        cities.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cityy.setAdapter(cities);

        Updateuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = getString(R.string.url)+"updateprofile.php?id="+SavedID.getId()+"&f_name="+first.getText()+"&l_name="+last.getText()+"&phone="+number.getText()+"&email="+emmail.getText()+"&cityid="+cityy.getSelectedItemId();
                RequestQueue Queue = Volley.newRequestQueue(userprofile.this);
                StringRequest request1 = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("Your profile information has been updated")){
                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();}
                        else   {
                            Toast.makeText(getApplicationContext(), "Try again ..."+SavedID.getId(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Connection problem with database occured", Toast.LENGTH_SHORT).show();
                    }
                });
                Queue.add(request1);
            }
        });
    }
}