package com.example.gustavomendez.tokin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DetailActivity extends Activity {
    private TextView nameText, textAddress, textTel;
    private  String id, tel, name, dir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        nameText = (TextView)findViewById(R.id.textNameDetail);
       textAddress = (TextView)findViewById(R.id.one);
        textTel = (TextView)findViewById(R.id.ttwo);
        id = (String)getIntent().getSerializableExtra("id");
        tel = (String)getIntent().getSerializableExtra("tel");
        name = (String)getIntent().getSerializableExtra("name");
        dir = (String)getIntent().getSerializableExtra("address");

        nameText.setText(" "+name);
       textTel.setText(""+tel);
        textAddress.setText(dir);


    }
    public void clickSolicitarEvento(View view){
        Intent it = new Intent( DetailActivity.this, SolicitarActivity.class);
        it.putExtra("id", id);
        it.putExtra("name", name);
        it.putExtra("address",dir);
        it.putExtra("tel",tel);
        startActivity(it);
    }
    public void clickEvaluar(View view){
        Intent it = new Intent( DetailActivity.this, EvaluarActivity.class);
        it.putExtra("id", id);
        it.putExtra("name", name);
        it.putExtra("address",dir);
        it.putExtra("tel",tel);
        startActivity(it);
    }

    public void clickVerEvalauciones(View view){
        Intent it = new Intent( DetailActivity.this, VerEvaluacionesActivity.class);
        startActivity(it);
    }
    public void clickChater(View view){
        Intent it = new Intent( DetailActivity.this, ChatActivity.class);
        startActivity(it);
    }

}
