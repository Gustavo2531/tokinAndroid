package com.example.gustavomendez.tokin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;



public class MainActivity extends Activity {
    private Button button;
    private Button button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        button.bringToFront();
        button2 = (Button) findViewById(R.id.button2);
        button2.bringToFront();
    }
    public void clickLogin(View view){
        Intent it = new Intent( MainActivity.this, LoginActivity.class);
        startActivity(it);
    }
    public void clickSignUp(View view){
        Intent it = new Intent( MainActivity.this, SignUpActivity.class);
        startActivity(it);
    }


}
