package com.example.gustavomendez.tokin;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class EvaluacionesUserActivity extends Activity {
    private Context context;
    private ListView listView;
    private TextView textViewName, textViewPuntaje;
    private int contador=0;
    private float prom=0;
    private float promedio=0;
    private FirebaseDatabase firebaseDatabase;
    private Query queryPrueba;
    private DatabaseReference databaseReference;
    private String id, name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluacionesuser);
        listView = (ListView) findViewById(R.id.listViewEvaluaciones);
        textViewName = (TextView) findViewById(R.id.textViewNameEvalLista);
        textViewPuntaje = (TextView) findViewById(R.id.textViewPuntajeLista);

        final EvaluacionesAdapter adapter = new EvaluacionesAdapter(this, R.layout.evaluaciones_layout,
                new ArrayList<Evaluaciones>());
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("evaluaciones");
        KeyUser keyUser = new KeyUser();
        textViewName.setText("Mis calificaciones");
        queryPrueba = databaseReference.orderByChild("userIdEvaluado").equalTo(keyUser.KU);

        queryPrueba.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // dataSnapshot is the "issue" node with all children with id 0
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        Evaluaciones m = new Evaluaciones();
                        m.evaluador = issue.child("nameRes").getValue().toString();
                        m.comentario = issue.child("comentario").getValue().toString();
                        m.rating = Float.parseFloat(issue.child("rating").getValue().toString());
                        contador=contador+1;
                        prom=prom+Float.parseFloat(issue.child("rating").getValue().toString());

                        adapter.add(m);
                    }

                }
                System.out.println(+prom);
                System.out.println(+contador);
                promedio = prom/contador;
                textViewPuntaje.setText(" "+promedio);
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });





       listView.setAdapter(adapter);

    }
}
