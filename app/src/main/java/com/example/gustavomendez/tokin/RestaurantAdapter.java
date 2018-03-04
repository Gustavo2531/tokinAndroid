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
public class RestaurantAdapter extends ArrayAdapter<Restaurant> {
    private Context context;
    public RestaurantAdapter(@NonNull Context context, int resource, @NonNull List<Restaurant> objects) {
        super(context, resource, objects);
        this.context=context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = null;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.restaurants_layout, parent, false);
        }
        Restaurant restaurant = getItem(position);
        TextView namerest = (TextView) convertView.findViewById(R.id.namerest);
        TextView address = (TextView) convertView.findViewById(R.id.address);
        TextView phone = (TextView) convertView.findViewById(R.id.phone);
        namerest.setText(restaurant.name);
        address.setText(restaurant.address);
        phone.setText(restaurant.phone.toString());
        convertView.setBackgroundResource(R.color.colorAccent);
        
        return convertView;
    }
}
