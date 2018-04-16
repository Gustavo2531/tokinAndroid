package com.example.gustavomendez.tokin;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class EvaluacionesAdapter extends ArrayAdapter<Evaluaciones> {
    private Context context;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    public EvaluacionesAdapter(@NonNull Context context, int resource, @NonNull List<Evaluaciones> objects) {
        super(context, resource, objects);
        this.context=context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = null;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.evaluaciones_layout, parent, false);
        }
        final Evaluaciones evaluaciones = getItem(position);
        TextView namer = (TextView) convertView.findViewById(R.id.textViewNameEvaluador);
        RatingBar ratingBar = (RatingBar) convertView.findViewById(R.id.ratingBar2);
        TextView comentario = (TextView) convertView.findViewById(R.id.textViewComenEval);

        ratingBar.setRating(evaluaciones.rating);
        namer.setText(evaluaciones.evaluador);
        comentario.setText(evaluaciones.comentario);

        convertView.setBackgroundResource(R.color.colorAccent);

        return convertView;
    }
}