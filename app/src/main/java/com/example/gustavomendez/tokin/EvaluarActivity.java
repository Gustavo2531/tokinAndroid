package com.example.gustavomendez.tokin;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class EvaluarActivity extends Activity {
     RatingBar ratingBar;
     private EditText evaluarEdit;
     private TextView textView, textView2;
     private Button buttonSubmit;
    private  String id,name, nameId;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private Query queryPrueba;

    private boolean isAlready = false;
    private boolean res = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluar);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        evaluarEdit = (EditText) findViewById(R.id.editTextEvaluar);
        textView = (TextView) findViewById(R.id.textViewNameEval);
        textView2 = (TextView) findViewById(R.id.rate_me);
        buttonSubmit = (Button) findViewById(R.id.submit);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("evaluaciones");

        id = (String)getIntent().getSerializableExtra("id");
        nameId = (String)getIntent().getSerializableExtra("idName");
        name = (String)getIntent().getSerializableExtra("name");


        textView.setText(name);
        KeyUser keyUser = new KeyUser();
        keyUser.start();
        String nomSing="";
        if(keyUser.typeref.equals("band")) {
            nomSing = keyUser.userName.toString();
            //res=false;
        }else{
            nomSing=keyUser.Restaurant.toString();
        }

        queryPrueba = databaseReference.orderByChild("userIdEvaluador").equalTo(keyUser.KU);

        queryPrueba.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // dataSnapshot is the "issue" node with all children with id 0
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        if(issue.child("userIdEvaluado").getValue().toString().equals(nameId)){
                            if(issue.child("idEvent").getValue().toString().equals(id)){
                                isAlready = true;
                                //Toast.makeText(getApplicationContext(),
                                  //      String.valueOf("Ya lo calificaste con "+issue.child("rating").getValue().toString()+" de puntaje"),
                                     //   Toast.LENGTH_LONG).show();
                                buttonSubmit.setVisibility(View.GONE);
                                evaluarEdit.setVisibility(View.GONE);
                                textView2.setText("Ya lo calificaste con "+issue.child("rating").getValue().toString()+" de puntaje");

                            }
                        }
                    }

                }
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        buttonSubmit.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submited();
            }
        });


    }

    public void submited(){
        KeyUser keyUser = new KeyUser();
        keyUser.start();
        databaseReference = firebaseDatabase.getReference().child("evaluaciones");
        String nomSing="";
        if(keyUser.typeref.equals("band")) {
            nomSing = keyUser.userName.toString();
            res=false;
        }else{
            nomSing=keyUser.Restaurant.toString();
        }
        EvalPojo evalPojo;
        if(res == true ) {
            evalPojo = new EvalPojo(""+name, ""+nomSing, keyUser.KU, nameId, nameId, keyUser.KU, id, evaluarEdit.getText().toString(), ratingBar.getRating());
        }else{
            evalPojo = new EvalPojo(""+nomSing, ""+name, keyUser.KU, nameId, ""+keyUser.KU, nameId, id,evaluarEdit.getText().toString(), ratingBar.getRating());
        }
        databaseReference.push().setValue(evalPojo);
        evaluarEdit.setText("");
        buttonSubmit.setVisibility(View.GONE);
        evaluarEdit.setVisibility(View.GONE);
        textView2.setText("Ya lo calificaste con "+ratingBar.getRating()+" de puntaje");

        Toast.makeText(getApplicationContext(), "Evento Guardado",
                Toast.LENGTH_SHORT)
                .show();

    }
    public void rateMe(View view){

        Toast.makeText(getApplicationContext(),
                String.valueOf(ratingBar.getRating()), Toast.LENGTH_LONG).show();
    }
}
