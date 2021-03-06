package com.example.gustavomendez.tokin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

public class SplashActivity extends Activity {
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.tokin);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent it = new Intent( SplashActivity.this, MainActivity.class);
                startActivity(it);
                finish();
            }
        }, 4000);
    }
}
