package com.example.handyman1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.os.SystemClock;
import android.provider.Settings;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class launcher extends AppCompatActivity {
ImageView imageView;
TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_launcher);

        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.textView);



                Intent x = new Intent(launcher.this, MainActivity.class);
                startActivity(x);




    }
}