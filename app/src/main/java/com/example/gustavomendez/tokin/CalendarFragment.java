package com.example.gustavomendez.tokin;


import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class CalendarFragment extends Fragment {

    private  ListView listView;
    public CalendarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_calendar, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        listView = (ListView) getView().findViewById(R.id.listViewEventos);
        EventosAdapter adapter = getAdapter();
        listView.setAdapter(adapter);

    }
    private EventosAdapter getAdapter(){
        EventosAdapter adapter = new  EventosAdapter(
                getActivity(), R.layout.calendar_eventos,
                new ArrayList<Eventos>());
        try {

                    Eventos m = new Eventos();
                    m.contratado = "Bar de Ahi";
                    m.horario = "20:00 - 22:00";
                    m.duracion = 2;
                    adapter.add(m);



        } catch (Exception e) {
            e.printStackTrace();
        }
        return adapter;


    }
}
