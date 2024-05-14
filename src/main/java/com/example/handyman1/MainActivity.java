package com.example.handyman1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

import android.widget.ImageButton;
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
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    String id;
    EditText username;
    EditText password;
    FloatingActionButton login;
    TextView signup;
    JSONArray data;
    ImageButton facebook, instagram , whatsapp;

    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        signup = findViewById(R.id.signup);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        facebook = findViewById(R.id.facebook);
        instagram = findViewById(R.id.instagram);
        whatsapp = findViewById(R.id.whatsapp);





        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this , SignUp.class);
                startActivity(i);
                //onPause();

            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String usr = username.getText().toString();
                String pass = password.getText().toString();
                String sub = getString(R.string.url) + "login.php?username=" + usr + "&password=" + pass;
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,
                        sub,
                        null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {

                                if(response.isNull(0))
                                    Toast.makeText(getApplicationContext(), "Username or Password are not correct", Toast.LENGTH_SHORT).show();
                                else{


                                    data = response;
                                    JSONObject obj = data.optJSONObject(0);

                                    try {
                                        id = obj.getString("id");

                                        SavedID.setId(id);

                                        if (obj.getString("user_type").equals("Client")){
                                            String Nameofuser = obj.getString("f_name")+" "+obj.getString("l_name");
                                            Toast.makeText(getApplicationContext(), "Welcome "+Nameofuser, Toast.LENGTH_SHORT).show();
                                            Toast.makeText(getApplicationContext(), "Welcome", Toast.LENGTH_SHORT).show();

                                            Intent h = new Intent(MainActivity.this, Client.class);
                                            h.putExtra("Name", Nameofuser);
                                            startActivity(h);}
                                        else if (obj.getString("user_type").equals("Service provider")){
                                            String Nameofuser = obj.getString("f_name")+" "+obj.getString("l_name");
                                            Toast.makeText(getApplicationContext(), "Welcome "+Nameofuser, Toast.LENGTH_SHORT).show();
                                            Intent h = new Intent(MainActivity.this, Service_provider.class);
                                            h.putExtra("seat1", Nameofuser);
                                            startActivity(h);
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }


                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getApplicationContext(), "request error", Toast.LENGTH_SHORT).show();
                            }
                        });
                queue.add(request);


            }
        });
    }
}
