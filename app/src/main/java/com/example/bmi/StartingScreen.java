package com.example.bmi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class StartingScreen extends AppCompatActivity {

    private static int SPLASH_SCREEN_TIME_OUT = 2000; // 2 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent menuIntent = new Intent(StartingScreen.this, MenuActivity.class);
                startActivity(menuIntent);
                finish();
            }
        }, SPLASH_SCREEN_TIME_OUT);
    }
}