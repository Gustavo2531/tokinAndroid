package com.example.gustavomendez.tokin;


import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;




public class RestaurantsListFragment extends ListFragment {


    public RestaurantsListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_restaurant_list, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        final RestaurantAdapter adapter = getAdapter();
        setListAdapter(adapter);
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Restaurant nd = adapter.getItem(i);
                Intent intent = new Intent( getActivity(),   DetailActivity.class);
                intent.putExtra("id", nd.id);
                intent.putExtra("name", nd.name);
                intent.putExtra("address",nd.address);
                intent.putExtra("tel",nd.phone);

                startActivity(intent);
                //Toast.makeText(getApplicationContext(),nd.id, Toast.LENGTH_LONG).show();
            }
        });
    }

    private RestaurantAdapter getAdapter(){
        RestaurantAdapter adapter = new RestaurantAdapter(
                getActivity(), R.layout.restaurants_layout,
                new ArrayList<Restaurant>());
        try {
            JSONObject jsonObject = new JSONObject(getJSON());
            JSONArray jsonArray = jsonObject.getJSONArray("mxcity");
            for (int i = 0; i< jsonArray.length(); i++){
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                JSONArray matches = jsonObject1.getJSONArray("places");
                for (int j = 0; j< matches.length(); j++){
                    JSONObject rest = matches.getJSONObject(j);
                    Restaurant m = new Restaurant();
                    m.name = rest.getString("name");
                    m.address = rest.getString("address");
                    m.phone = rest.getString("phone");
                    m.id = rest.getInt("id");
                    adapter.add(m);

                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return adapter;


    }


    private String getJSON() {
        try {
            InputStream inputStream = getActivity().getAssets().open("restaurant.json");
            int s = inputStream.available();
            byte[] archivo = new byte[s];
            inputStream.read(archivo);
            inputStream.close();
            return new String(archivo);
        } catch(IOException e) {
            e.printStackTrace();
        }
        return "";

    }



}
