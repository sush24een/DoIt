package com.example.doit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
//        hides action bar
        getSupportActionBar().hide();

        final Intent i = new Intent(SplashActivity.this, MainActivity.class);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
//                passing intent in start activity
                startActivity(i);
                finish();
            }
//            splash activity splashes after 5 milliseconds for which handler is written
//           selected launcher activity while creating, reflected in manifest file
        }, 5000);
    }
}