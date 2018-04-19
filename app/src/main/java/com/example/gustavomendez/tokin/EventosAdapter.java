package com.example.gustavomendez.tokin;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;



/**
 * Created by gustavomendez on 04/03/18.
 */

public class EventosAdapter extends ArrayAdapter<Eventos> {
    private Context context;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    public EventosAdapter(@NonNull Context context, int resource, @NonNull List<Eventos> objects) {
        super(context, resource, objects);
        this.context=context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = null;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.calendar_eventos, parent, false);
        }
        final Eventos event = getItem(position);
        TextView namerest = (TextView) convertView.findViewById(R.id.textViewDC);
        TextView phone  = (TextView) convertView.findViewById(R.id.textViewDDuracion);
        TextView address= (TextView) convertView.findViewById(R.id.textViewDHorario);
        ImageButton bIn = (ImageButton) convertView.findViewById(R.id.imageButtonRejectEvent);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("eventos");
        namerest.setText(event.contratado);
        address.setText(event.horario);
        phone.setText(""+event.day);



        bIn.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View view) {
                                       databaseReference.child(event.id).child("eventStatus").setValue(3);
                                       context.startActivity(new Intent(context, MainActivity2.class));

                                   }
                               }

        );

        convertView.setBackgroundResource(R.color.colorAccent);

        return convertView;
    }

}




