package com.example.booknow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.booknow.Database.SharedPrefs;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (SharedPrefs.checkFirst(getApplicationContext())) {
                    startActivity(new Intent(getApplicationContext(), Login.class));
                } else {
                    startActivity(new Intent(getApplicationContext(), LandingPage.class));

                }
                finish();
            }
        }, 750);


    }

}