package com.example.gustavomendez.tokin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DetailActivity extends Activity {
    private TextView nameText, textAddress, textTel;
    private  String id, tel, name, dir;
    private Button verEv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        nameText = (TextView)findViewById(R.id.textNameDetail);
       textAddress = (TextView)findViewById(R.id.one);
        textTel = (TextView)findViewById(R.id.ttwo);
        verEv = (Button)findViewById(R.id.buttonVerEvaluaciones);
        id = (String)getIntent().getSerializableExtra("id");
        tel = (String)getIntent().getSerializableExtra("tel");
        name = (String)getIntent().getSerializableExtra("name");
        dir = (String)getIntent().getSerializableExtra("address");

        nameText.setText(" "+name);
       textTel.setText(""+tel);
        textAddress.setText(dir);
        verEv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent( DetailActivity.this, EvaluacionesActivity.class);

                it.putExtra("id", id);
                it.putExtra("name", name);
                it.putExtra("address",dir);
                it.putExtra("tel",tel);

                    Toast.makeText(DetailActivity.this, "Loaded", Toast.LENGTH_LONG).show();

                    startActivity(it);

                }

            });

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
        Intent it = new Intent( DetailActivity.this, EvaluacionesActivity.class);
        it.putExtra("id", id);
        it.putExtra("name", name);
        it.putExtra("address",dir);
        it.putExtra("tel",tel);
        startActivity(it);
    }

    public void clickVerEvalauciones(View view){
        Intent it = new Intent( DetailActivity.this, EvaluacionesActivity.class);
        it.putExtra("id", id);
        it.putExtra("name", name);
        it.putExtra("address",dir);
        it.putExtra("tel",tel);
        startActivity(it);
    }
    public void clickChater(View view){

    }

}
