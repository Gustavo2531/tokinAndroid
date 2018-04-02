package com.example.gustavomendez.tokin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class BandOrRestaurantActivity extends Activity {
    String USER_KEY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_band_or_restaurant);
        USER_KEY = getIntent().getStringExtra("USER_KEY");


    }

    public void clickBanda(View view) {
        Intent it = new Intent(BandOrRestaurantActivity.this, ProfileBanda.class);
        it.putExtra("USER_KEY", USER_KEY);
        startActivity(it);


    }

    public void clickRestaurante(View view) {
        Intent it = new Intent(BandOrRestaurantActivity.this, ProfileRestaurante.class);
        it.putExtra("USER_KEY", USER_KEY);
        startActivity(it);



    }
}
