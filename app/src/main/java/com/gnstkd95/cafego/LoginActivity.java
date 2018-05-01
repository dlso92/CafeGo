package com.gnstkd95.cafego;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.nhn.android.naverlogin.OAuthLogin;
import com.nhn.android.naverlogin.OAuthLoginHandler;
import com.nhn.android.naverlogin.ui.view.OAuthLoginButton;

public class LoginActivity extends AppCompatActivity {

    Context context;
    private String ClientID = "9Xe8GEPqliKUfH8io6so";
    private String ClientSecret = "xKnikvcb8K";
    private String ClientNM = "이상훈";
    public static OAuthLogin mOAuthLoginModule;

    OAuthLoginButton mOAuthLoginButton;

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
        setNaver();


    }//oncreate...

    private  void setNaver(){
        mOAuthLoginModule = OAuthLogin.getInstance();
        mOAuthLoginModule.init(this,ClientID,ClientSecret,ClientNM);
        mOAuthLoginButton = findViewById(R.id.button_naverlogin);
        mOAuthLoginButton.setOAuthLoginHandler(mOAuthLoginHandler);
        mOAuthLoginModule.startOauthLoginActivity(this,mOAuthLoginHandler);
    }

    private OAuthLoginHandler mOAuthLoginHandler = new OAuthLoginHandler() {
        @Override
        public void run(boolean success) {

        if (success){
            String accessToken = mOAuthLoginModule.getAccessToken(context);
            String refreshToken = mOAuthLoginModule.getRefreshToken(context);
            long expiresAt = mOAuthLoginModule.getExpiresAt(context);
            String tokenType = mOAuthLoginModule.getTokenType(context);
            Log.i("member","accessToken : "+accessToken+"refreshToken : "+refreshToken+"expiresAt : "+expiresAt+"tokenType : "+tokenType);
        }else{
            String errorCode = mOAuthLoginModule.getLastErrorCode(context).getCode();
            String errorDesc = mOAuthLoginModule.getLastErrorDesc(context);
            Toast.makeText(context, "errorCode: "+errorCode+" , "+errorDesc, Toast.LENGTH_SHORT).show();
        }

        }
    };



}//LoginActivity...
