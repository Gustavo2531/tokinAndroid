package com.example.gustavomendez.tokin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by gustavomendez on 28/02/18.
 */

public class LoginActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    public void clickEntrar(View view){
        Intent it = new Intent( LoginActivity.this, LoginActivity.class);
        startActivity(it);
    }
}
