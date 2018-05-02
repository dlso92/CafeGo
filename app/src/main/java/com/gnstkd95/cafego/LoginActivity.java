package com.gnstkd95.cafego;


import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Base64;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;


public class LoginActivity extends AppCompatActivity {
    EditText ID;
    EditText PS;

    Button gest_Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ID = findViewById(R.id.login_ID);
        PS = findViewById(R.id.login_PS);
        gest_Btn = findViewById(R.id.gest_Btn);
        getHashKey();
//        try {
//            PackageInfo info = getPackageManager().getPackageInfo(this.getPackageName(),PackageManager.GET_SIGNATURES);
//            for (Signature signature : info.signatures){
//
//                MessageDigest messageDigest = MessageDigest.getInstance("SHA");
//                messageDigest.update(signature.toByteArray());
//                Log.d("aaaa", com.kakao.auth.helper.Base64.encodeBase64URLSafeString(messageDigest.digest()));
//
//            }
//        } catch (PackageManager.NameNotFoundException e) {
//            Log.d("error","Packgeinfo error is : "+e.toString());
//            e.printStackTrace();
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }


    }

    private void getHashKey(){
        try {
            PackageInfo info = getPackageManager().getPackageInfo("com.gnstkd95.cafego", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("con","key_hash="+Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

}
