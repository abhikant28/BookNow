package com.example.booknow;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    EditText et_name;
    EditText et_num;
    Button b_verify;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_name = findViewById(R.id.editText);
        et_num = findViewById(R.id.editText2);
        b_verify = findViewById(R.id.button);

        b_verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (pass()) {
                    String num = et_num.getText().toString().trim();
                    String name = et_name.getText().toString().trim();
                    Intent intent = new Intent(getApplicationContext(), CompleteLogin.class);
                    intent.putExtra("NAME", name);
                    intent.putExtra("NUM", num);
                    startActivity(intent);
                }
            }
        });


    }

    private boolean pass() {

        if (et_num.getText().toString().trim().length() != 14) {
            Log.i("INCORRECT", String.valueOf(et_num.toString().trim().length()));
            et_num.setError("Incorrect Number");
            et_num.setText("+91 ");
            return false;
        }
        if (et_name.getText().toString().trim().length() == 0) {
            et_name.setError("Missing");
            return false;
        }
        return true;
    }
}