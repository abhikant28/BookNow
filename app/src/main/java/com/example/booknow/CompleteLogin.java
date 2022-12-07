package com.example.booknow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.booknow.Database.SharedPrefs;

public class CompleteLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_login);

        EditText et=findViewById(R.id.completeLogin_et_otp);

        new Handler().postDelayed(() -> et.setText("1234556"), 3000);


        String num=getIntent().getExtras().getString("NUM");
        String name=getIntent().getExtras().getString("NAME");

        SharedPrefs.saveUserData(this, name, num);
        SharedPrefs.firstTime(this);

        Button b=findViewById(R.id.Submit_button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),LandingPage.class));
            }
        });
        b.performClick();

    }
}