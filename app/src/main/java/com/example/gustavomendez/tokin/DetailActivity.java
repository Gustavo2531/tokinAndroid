package com.example.gustavomendez.tokin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DetailActivity extends Activity {
    private TextView nameText, textAddress, textTel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        nameText = (TextView)findViewById(R.id.textNameDetail);
       textAddress = (TextView)findViewById(R.id.textDireccionCDetail);
        textTel = (TextView)findViewById(R.id.textTelDetail);
        Integer id = (Integer)getIntent().getSerializableExtra("id");
        String tel = (String)getIntent().getSerializableExtra("tel");
        String name = (String)getIntent().getSerializableExtra("name");
        String dir = (String)getIntent().getSerializableExtra("address");

        nameText.setText(" "+name);
       textTel.setText(""+tel);
        textAddress.setText(dir);


    }
    public void clickSolicitarEvento(View view){
        Intent it = new Intent( DetailActivity.this, SolicitarActivity.class);
        startActivity(it);
    }

}
