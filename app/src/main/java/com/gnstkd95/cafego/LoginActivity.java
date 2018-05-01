package com.gnstkd95.cafego;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.common.SignInButton;

public class LoginActivity extends AppCompatActivity {
    EditText ID;
    EditText PS;
    SignInButton google_Btn;
    Button gest_Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ID = findViewById(R.id.login_ID);
        PS = findViewById(R.id.login_PS);
        google_Btn = findViewById(R.id.google_Btn);
        gest_Btn = findViewById(R.id.gest_Btn);


    }





}
