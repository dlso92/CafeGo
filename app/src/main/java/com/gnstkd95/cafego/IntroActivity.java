package com.gnstkd95.cafego;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class IntroActivity extends AppCompatActivity {

    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        iv = findViewById(R.id.iv);

        Animation ani = AnimationUtils.loadAnimation(this,R.anim.intro_logo);
        iv.startAnimation(ani);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent next_MainActivity = new Intent(IntroActivity.this,LoginActivity.class);
                startActivity(next_MainActivity);
                finish();
            }
        },4000);

    }
}
