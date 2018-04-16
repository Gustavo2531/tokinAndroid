package com.example.gustavomendez.tokin;


import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class CalendarFragment extends Fragment {
    private FirebaseDatabase firebaseDatabase;
    private Query queryPrueba;
    private DatabaseReference databaseReference;
    private Button button1;
    private Button button2;
    private boolean r=true;
    private boolean isDone = false;

    private  ListView listView;
    public CalendarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View rootView = inflater.inflate(R.layout.fragment_calendar, container, false);
        Button button = (Button) rootView.findViewById(R.id.buttonAgregarEvento);
         button1 = (Button) rootView.findViewById(R.id.buttonEventos);
         button2 = (Button) rootView.findViewById(R.id.buttonPasados);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDetail();
            }
        });
        button1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        isDone = false;
                        clean();
                        EventosAdapter adapter = getAdapter();
                        listView.setAdapter(adapter);
                    }
                });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isDone = true;
                clean();
                EventosPasadosAdapter adapter = getAdapterPasados();
                listView.setAdapter(adapter);
            }
        });
        return rootView;
    }

    public void updateDetail() {
        Intent it = new Intent( getActivity() , AgregarEventoActivity.class);
        startActivity(it);
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        listView = (ListView) getView().findViewById(R.id.listViewEventos);
        button1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        isDone = false;
                        clean();
                        EventosAdapter adapter = getAdapter();
                        listView.setAdapter(adapter);
                    }
                });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isDone = true;
                clean();
                EventosPasadosAdapter adapter = getAdapterPasados();
                listView.setAdapter(adapter);
            }
        });
        if(isDone==false) {
            EventosAdapter adapter = getAdapter();
            listView.setAdapter(adapter);
        }else{
            EventosPasadosAdapter adapter = getAdapterPasados();
            listView.setAdapter(adapter);
        }


    }
    private void clean(){
        listView.setAdapter(null);
    }

    private EventosPasadosAdapter getAdapterPasados(){
        final EventosPasadosAdapter adapter = new EventosPasadosAdapter(
                getActivity(), R.layout.calendar_eventos,
                new ArrayList<Eventos>());
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("eventos");
        KeyUser keyUser = new KeyUser();
        keyUser.start();
        if(keyUser.typeref.equals("band")) {
            queryPrueba = databaseReference.orderByChild("userIdBand").equalTo(keyUser.KU);

            r=false;
        }else{

            queryPrueba = databaseReference.orderByChild("userIdRes").equalTo(keyUser.KU);
        }

        queryPrueba = databaseReference.orderByChild("userIdr").equalTo(keyUser.KU);

        queryPrueba.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // dataSnapshot is the "issue" node with all children with id 0
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        if(r==false) {
                            Eventos m = new Eventos();
                            m.contratado= issue.child("nameRes").getValue().toString();
                            m.contratadoId = issue.child("userIdRes").getValue().toString();
                            String dayB = issue.child("dateB").getValue().toString();
                            Date dateToday = new Date();

                            String day= dayB.substring(0,11);
                            String timeB = dayB.substring(11,16);
                            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            String dayE = issue.child("dateEnd").getValue().toString().substring(11,16);
                            m.horario = ""+timeB+" - "+dayE;
                            Date date;
                            try {
                                date = dateFormat.parse(day);
                                if(date.before(dateToday)){
                                    databaseReference.child(issue.getKey().toString()).child("eventStatus").setValue(0);
                                }
                                m.day=date.toString().substring(0,11); // Wed Dec 04 00:00:00 CST 2013

                            }
                            catch (ParseException e) {
                                m.day="Any";
                            }


                            m.id =  issue.getKey().toString();

                            if(isDone==false) {
                                if (issue.child("eventStatus").getValue().toString().equals("2")) {
                                    adapter.add(m);
                                }
                            }else{
                                if (issue.child("eventStatus").getValue().toString().equals("0")) {
                                    adapter.add(m);
                                }
                            }

                            //System.out.println(issue.child("musicType").toString());
                        }else{
                            Eventos m = new Eventos();

                            m.contratado = issue.child("nameBand").getValue().toString();
                            m.contratadoId = issue.child("userIdBand").getValue().toString();
                            String dayB = issue.child("dateB").getValue().toString();
                            Date dateToday = new Date();

                            String day= dayB.substring(0,11);
                            String timeB = dayB.substring(11,16);
                            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            String dayE = issue.child("dateEnd").getValue().toString().substring(11,16);
                            m.horario = ""+timeB+" - "+dayE;
                            Date date;
                            try {
                                date = dateFormat.parse(day);
                                if(date.before(dateToday)){
                                    databaseReference.child(issue.getKey().toString()).child("eventStatus").setValue(0);
                                }
                                m.day=date.toString().substring(0,11); // Wed Dec 04 00:00:00 CST 2013

                            }
                            catch (ParseException e) {
                                m.day="Any";
                            }


                            m.id =  issue.getKey().toString();

                            if(isDone==false) {
                                if (issue.child("eventStatus").getValue().toString().equals("2")) {
                                    adapter.add(m);
                                }
                            }else{
                                if (issue.child("eventStatus").getValue().toString().equals("0")) {
                                    adapter.add(m);
                                }
                            }
                            //System.out.println(issue.child("musicType").toString());


                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //firebaseStorage = FirebaseStorage.getInstance();
        //storageReference = firebaseStorage.getReference().child("pics");


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                Eventos nd = adapter.getItem(i);
                Intent intent = new Intent( getActivity(),   EvaluarActivity.class);
                intent.putExtra("name",nd.contratado);
                intent.putExtra("idName", nd.contratadoId);
                intent.putExtra("id",nd.id);
                startActivity(intent);



                //if(nd.stat==false){
                //  intent.putExtra("id",nd.userIdRes);
                //}else{
                //intent.putExtra("id",nd.userIdBand);
                //}

                //intent.putExtra("name", nd.name);

                //startActivity(intent);
               /* }else{
                    Solicitudes nd = adapter2.getItem(i);
                    //Intent intent = new Intent( getActivity(),   DetailActivity.class);
                    if(nd.stat==false){
                        //  intent.putExtra("id",nd.userIdRes);
                    }else{
                        //intent.putExtra("id",nd.userIdBand);
                    }

                    //intent.putExtra("name", nd.name);

                    //startActivity(intent);
                }*/



                //Toast.makeText(getApplicationContext(),nd.id, Toast.LENGTH_LONG).show();
            }
        });
        //if(isRejectedView ==false) {
        //listView.setAdapter(adapter);
        //}else{
        //  listView.setAdapter(adapter2);
        //}
        return adapter;

    }
    private EventosAdapter getAdapter(){
        final EventosAdapter adapter = new  EventosAdapter(
                getActivity(), R.layout.calendar_eventos,
                new ArrayList<Eventos>());
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("eventos");
        KeyUser keyUser = new KeyUser();
        keyUser.start();
        if(keyUser.typeref.equals("band")) {
            queryPrueba = databaseReference.orderByChild("userIdBand").equalTo(keyUser.KU);

            r=false;
        }else{

            queryPrueba = databaseReference.orderByChild("userIdRes").equalTo(keyUser.KU);
        }

        queryPrueba = databaseReference.orderByChild("userIdr").equalTo(keyUser.KU);

        queryPrueba.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // dataSnapshot is the "issue" node with all children with id 0
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        if(r==false) {
                            Eventos m = new Eventos();
                             m.contratado= issue.child("nameRes").getValue().toString();
                            m.contratadoId = issue.child("userIdRes").getValue().toString();
                            String dayB = issue.child("dateB").getValue().toString();
                            Date dateToday = new Date();

                            String day= dayB.substring(0,11);
                            String timeB = dayB.substring(11,16);
                            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            String dayE = issue.child("dateEnd").getValue().toString().substring(11,16);
                            m.horario = ""+timeB+" - "+dayE;
                            Date date;
                            try {
                                date = dateFormat.parse(day);
                                if(date.before(dateToday)){
                                    databaseReference.child(issue.getKey().toString()).child("eventStatus").setValue(0);
                                }
                                m.day=date.toString().substring(0,11); // Wed Dec 04 00:00:00 CST 2013

                            }
                            catch (ParseException e) {
                                m.day="Any";
                            }


                            m.id =  issue.getKey().toString();

                           if(isDone==false) {
                                if (issue.child("eventStatus").getValue().toString().equals("2")) {
                                    adapter.add(m);
                                }
                           }else{
                                if (issue.child("eventStatus").getValue().toString().equals("0")) {
                                   adapter.add(m);
                                }
                            }

                            //System.out.println(issue.child("musicType").toString());
                        }else{
                            Eventos m = new Eventos();

                            m.contratado = issue.child("nameBand").getValue().toString();
                            m.contratadoId = issue.child("userIdBand").getValue().toString();
                            String dayB = issue.child("dateB").getValue().toString();
                            Date dateToday = new Date();

                            String day= dayB.substring(0,11);
                            String timeB = dayB.substring(11,16);
                            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            String dayE = issue.child("dateEnd").getValue().toString().substring(11,16);
                            m.horario = ""+timeB+" - "+dayE;
                            Date date;
                            try {
                                date = dateFormat.parse(day);
                                if(date.before(dateToday)){
                                    databaseReference.child(issue.getKey().toString()).child("eventStatus").setValue(0);
                                }
                                m.day=date.toString().substring(0,11); // Wed Dec 04 00:00:00 CST 2013

                            }
                            catch (ParseException e) {
                                m.day="Any";
                            }


                            m.id =  issue.getKey().toString();

                            if(isDone==false) {
                                if (issue.child("eventStatus").getValue().toString().equals("2")) {
                                    adapter.add(m);
                                }
                            }else{
                                if (issue.child("eventStatus").getValue().toString().equals("0")) {
                                    adapter.add(m);
                                }
                            }
                            //System.out.println(issue.child("musicType").toString());


                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //firebaseStorage = FirebaseStorage.getInstance();
        //storageReference = firebaseStorage.getReference().child("pics");


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

               // if(isRejectedView==false) {
                    //Solicitudes nd = adapter.getItem(i);
                    //Intent intent = new Intent( getActivity(),   DetailActivity.class);
                    //if(nd.stat==false){
                        //  intent.putExtra("id",nd.userIdRes);
                    //}else{
                        //intent.putExtra("id",nd.userIdBand);
                    //}

                    //intent.putExtra("name", nd.name);

                    //startActivity(intent);
               /* }else{
                    Solicitudes nd = adapter2.getItem(i);
                    //Intent intent = new Intent( getActivity(),   DetailActivity.class);
                    if(nd.stat==false){
                        //  intent.putExtra("id",nd.userIdRes);
                    }else{
                        //intent.putExtra("id",nd.userIdBand);
                    }

                    //intent.putExtra("name", nd.name);

                    //startActivity(intent);
                }*/



                //Toast.makeText(getApplicationContext(),nd.id, Toast.LENGTH_LONG).show();
            }
        });
        //if(isRejectedView ==false) {
            //listView.setAdapter(adapter);
        //}else{
          //  listView.setAdapter(adapter2);
        //}
        return adapter;

    }
}
