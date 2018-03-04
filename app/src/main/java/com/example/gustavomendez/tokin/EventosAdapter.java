package com.example.gustavomendez.tokin;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.List;



/**
 * Created by gustavomendez on 04/03/18.
 */

public class EventosAdapter extends ArrayAdapter<Eventos> {
    private Context context;
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
        Eventos event = getItem(position);
        TextView namerest = (TextView) convertView.findViewById(R.id.textViewDC);
        TextView phone  = (TextView) convertView.findViewById(R.id.textViewDDuracion);
        TextView address= (TextView) convertView.findViewById(R.id.textViewDHorario);
        namerest.setText(event.contratado);
        address.setText(event.horario);
        phone.setText(""+event.duracion);
        convertView.setBackgroundResource(R.color.colorAccent);

        return convertView;
    }

}




