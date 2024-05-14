package com.example.handyman1;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

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

public class service_desc extends AppCompatActivity {

    ImageView image;
int SMSGranted;
int SENDSMS = 100;

    TextView desc , price , name , address , title;
    FloatingActionButton call, msg, mail;
    JSONArray data ;
    RatingBar rate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_service_desc);
        rate = findViewById(R.id.rating);
        desc = findViewById(R.id.ddesc);

        call = findViewById(R.id.call);
        msg = findViewById(R.id.message);
        mail = findViewById(R.id.mail);
        rate = findViewById(R.id.rating);

        Intent flow = getIntent();
        String userid = flow.getStringExtra("userid");
        String serviceid = flow.getStringExtra("serviceid");
        String url = getString(R.string.url) + "service_desc.php?userid=" + userid +"&serviceid="+ serviceid;
        RequestQueue queue = Volley.newRequestQueue(service_desc.this);
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(JSONArray response) {
                data = response;

                JSONObject userinfo = data.optJSONObject(0);


                try {
                    String n = userinfo.getString("f_name");
                    String l = userinfo.getString("l_name");
                    int add = userinfo.getInt("city_id");



                    String[] addr = getResources().getStringArray(R.array.address);


                    desc.setText("\nName:\n "+userinfo.getString("f_name").toUpperCase()+" "
                            +userinfo.getString("l_name").toUpperCase()+"\n\n"+"Title:\n "+userinfo.getString("title")+"\n\n Address: "+addr[add]+
                            "\n\n Price: "+userinfo.getString("price")+"\n\n Description:\n"+userinfo.getString("description"));
                    rate.setRating(Float.parseFloat(userinfo.getString("rate")));
                    String phon = userinfo.getString("phone");
                    String email = userinfo.getString("email");
call.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:"+phon));
        startActivity(intent);
    }
});
msg.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        if (SMSGranted == 1){
            Intent sendIntent = new Intent(Intent.ACTION_VIEW);
            sendIntent.setData(Uri.parse("sms:"+phon));
            startActivity(sendIntent);
        }
        else {
            askForPermissions();
        }
            }




});
mail.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent sendEmail = getPackageManager().getLaunchIntentForPackage("com.google.android.gm");
        if(sendEmail!= null){
            startActivity(sendEmail);
        }

    }
});

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(request);


    }
    public void askForPermissions(){
        //check if the user has granted permission
        if (ContextCompat.checkSelfPermission(service_desc.this, Manifest.permission.SEND_SMS)!= PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(service_desc.this, new String[]{Manifest.permission.CALL_PHONE}, SENDSMS);
        }
        else {
            // Permission has already been granted
            SMSGranted = 1;
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == SENDSMS) {
            // If request is cancelled, the result arrays are empty.
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // permission was granted, yay! Do the call-related task you need to do.
                SMSGranted = 1;
            } else {
                // permission denied, boo! Disable the functionality that depends on this permission.
                SMSGranted = 0;
            }
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}