package com.sarzar.canteenapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class splash_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Handler handler=new Handler();
        handler.postDelayed(() -> {
            Intent intent =new Intent(splash_screen.this,FaceActivity.class);
            finish();
            startActivity(intent);
        },2500);
    }
}