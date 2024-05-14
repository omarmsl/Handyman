package com.example.handyman1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class SignUp extends AppCompatActivity {
    EditText fname,lname,username,password,password2 ,email,phone;
    FloatingActionButton register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_sign_up);
        fname = findViewById(R.id.fnane);
        lname =findViewById(R.id.lname);
        username = findViewById(R.id.usern);
        password = findViewById(R.id.userp);
        email = findViewById(R.id.useremail);
        password2 = findViewById(R.id.userp2);
        phone = findViewById(R.id.userphone);
        Spinner spinner = findViewById(R.id.spinner);
        Spinner city = findViewById(R.id.city);
        register = findViewById(R.id.register);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinnerentries, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        ArrayAdapter<CharSequence> cities = ArrayAdapter.createFromResource(this,
                R.array.address, android.R.layout.simple_spinner_item);
        cities.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        city.setAdapter(cities);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userna = username.getText().toString();
                String userpa = password.getText().toString();
                String userpa2 = password2.getText().toString();
                String fn = fname.getText().toString();
                String ln = lname.getText().toString();
                String useremail = email.getText().toString();
                String Phon = phone.getText().toString();
                String type = (String) spinner.getSelectedItem();

                if (userna.equals("") || userpa.equals("") || fn.equals("") ||
                        lname.equals("") || useremail.equals("")){
                    Toast.makeText(getApplicationContext(), "Please fill all the required fields",
                            Toast.LENGTH_SHORT).show();
                }
                else if (type.equals("Select one")) {

                    Toast.makeText(getApplicationContext(), "Please choose whether Client or Service provider",
                            Toast.LENGTH_SHORT).show();
                }else if(city.getSelectedItemId()== 0){
                    Toast.makeText(getApplicationContext(), "Select your city", Toast.LENGTH_SHORT).show();


                }

                 if (userpa.equals(userpa2) ) {

                     int  upChars = 0, lowChars = 0;
                     int special = 0, digits = 0;
                     for (int i = 0; i < userpa.length(); i++) {

                         Character ch = userpa.charAt(i);
                         if (Character.isUpperCase(ch))
                             upChars = 1;
                         else if (Character.isLowerCase(ch))
                             lowChars = 1;
                         else if (Character.isDigit(ch))
                             digits = 1;
                         else
                             special = 1;

                     }


                     if (upChars == 0 || lowChars == 0 || digits == 0 || special == 0) {
                         Toast.makeText(getApplicationContext(), "Passwords is too weak", Toast.LENGTH_SHORT).show();
                     }


                 else  {

                    String url = getString(R.string.url)+"register.php?username="+userna+"&password="+
                            userpa+"&f_name="+fn+"&l_name="+ln+"&phone="+Phon+"&email="+useremail
                            +"&type="+type+"&city_id="+city.getSelectedItemId();
                    RequestQueue queue = Volley.newRequestQueue(SignUp.this);
                    StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();

                        }
                    });
                    queue.add(request);



                }}
                 else if(userpa != userpa2){  Toast.makeText(getApplicationContext(), "Passwords not matched", Toast.LENGTH_SHORT).show();}


            }

        });

    }
}
