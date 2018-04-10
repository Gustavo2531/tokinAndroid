package com.example.gustavomendez.tokin;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class RequestsAdapter extends ArrayAdapter<Solicitudes> {
    private Context context;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    public RequestsAdapter(@NonNull Context context, int resource, @NonNull List<Solicitudes> objects) {
        super(context, resource, objects);
        this.context=context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = null;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.requests_layout, parent, false);
        }
        final Solicitudes solicitudes = getItem(position);
        TextView namer = (TextView) convertView.findViewById(R.id.textViewNameSolicitud);
        TextView dateB = (TextView) convertView.findViewById(R.id.textViewBSolicitud);
        TextView dateE = (TextView) convertView.findViewById(R.id.textViewESolicitud);
       ImageButton bIn = (ImageButton) convertView.findViewById(R.id.imageButtonAccept);
        ImageButton bOut = (ImageButton) convertView.findViewById(R.id.imageButtonReject);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("eventos");

        namer.setText(solicitudes.name);

        dateB.setText(solicitudes.dayB);
        dateE.setText(solicitudes.dayE);

        bIn.setOnClickListener(new View.OnClickListener() {
                                              @Override
                                              public void onClick(View view) {
                                                  databaseReference.child(solicitudes.id).child("eventStatus").setValue(2);

                                              }
                                          }

        );

        bOut.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View view) {
                                       databaseReference.child(solicitudes.id).child("eventStatus").setValue(3);

                                   }
                               }

        );
        //phone.setText(solicitudes.phone.toString());
        convertView.setBackgroundResource(R.color.colorAccent);

        return convertView;
    }
}