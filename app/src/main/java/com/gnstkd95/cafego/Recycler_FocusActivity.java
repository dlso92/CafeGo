package com.gnstkd95.cafego;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class Recycler_FocusActivity extends AppCompatActivity {

    WebView webView;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler__focus);

        webView = findViewById(R.id.webview);

        Intent intent = getIntent();
        if (intent != null){
            String s = intent.getStringExtra("Link");
            webView.setWebViewClient(new WebViewClient());
            webView.setWebChromeClient(new WebChromeClient());
            webView.getSettings().setJavaScriptEnabled(true);
            webView.loadUrl(s);
//            int x = intent.getIntExtra("X",0);
//            int y = intent.getIntExtra("Y",0);

//            tv = findViewById(R.id.tvtv);
//            tv.setText(x+","+y+","+s);

        }


    }
}
