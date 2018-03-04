package com.example.gustavomendez.tokin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SignUpActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }
    public void clickEntrarSign(View view){
        Intent it = new Intent( SignUpActivity.this, MainActivity2.class);
        startActivity(it);
    }
}
