package com.example.gustavomendez.tokin;


import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class RequestsFragment extends Fragment {
    private Button button;
    private FirebaseDatabase firebaseDatabase;
    private Query queryPrueba;
    private DatabaseReference databaseReference;
    private EditText editText;
    private ChildEventListener childEventListener;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener stateListener;
    private final int LOGIN =123;
    private String name;
    // private ImageButton imageButton;
    private boolean r=true;
    private ListView listView;

    private Button solicitudesButton, rejectedButton;
    private boolean isRejectedView=false;

    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;


    public RequestsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_requests, container, false);
        return rootView;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        listView = (ListView) view.findViewById(R.id.listViewSolictudes);
        solicitudesButton = (Button) view.findViewById(R.id.buttonSolicitudes);
        rejectedButton = (Button) view.findViewById(R.id.buttonRechazados);
        solicitudesButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    isRejectedView = false;
                                    clean();
                                    loadListView();
                                }
                            }

        );

        rejectedButton.setOnClickListener(new View.OnClickListener() {
                                                 @Override
                                                 public void onClick(View view) {
                                                     isRejectedView = true;
                                                     System.out.println("Entre");
                                                     System.out.println(isRejectedView);
                                                     clean();
                                                     loadListView();
                                                 }
                                             }

        );

    }

    private void clean(){
        listView.setAdapter(null);
    }
    private void loadListView(){
        final RequestsAdapter adapter = new RequestsAdapter(
                getActivity(), R.layout.requests_layout,
                new ArrayList<Solicitudes>());

        final RejectedAdapter adapter2 = new RejectedAdapter(
                getActivity(), R.layout.requests_layout,
                new ArrayList<Solicitudes>());


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
        // queryPrueba = queryPrueba.orderByChild("eventStatus").equalTo(1);
        queryPrueba.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // dataSnapshot is the "issue" node with all children with id 0
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        if(r==false) {
                            Solicitudes m = new Solicitudes();

                            m.name = issue.child("nameRes").getValue().toString();
                            m.dayB = issue.child("dateB").getValue().toString();
                            m.dayE = issue.child("dateEnd").getValue().toString();
                            m.id =  issue.getKey().toString();
                            m.name2 = issue.child("nameBand").getValue().toString();
                            m.userIdBand = issue.child("userIdBand").getValue().toString();
                            m.userIdRes = issue.child("userIdRes").getValue().toString();
                            m.userIds =  issue.child("userIds").getValue().toString();
                            m.userIdr = issue.child("userIdr").getValue().toString();
                            m.stat = false;
                            if(isRejectedView==false) {
                                if (issue.child("eventStatus").getValue().toString().equals("1")) {
                                    adapter.add(m);
                                }
                            }else{
                                if (issue.child("eventStatus").getValue().toString().equals("3")) {
                                    adapter2.add(m);
                                }
                            }

                            //System.out.println(issue.child("musicType").toString());
                        }else{
                            Solicitudes m = new Solicitudes();

                            m.name = issue.child("nameBand").getValue().toString();
                            m.dayB = issue.child("dateB").getValue().toString();
                            m.dayE = issue.child("dateEnd").getValue().toString();
                            m.id =  issue.getKey().toString();
                            m.name2 = issue.child("nameRes").getValue().toString();
                            m.userIdBand = issue.child("userIdBand").getValue().toString();
                            m.userIdRes = issue.child("userIdRes").getValue().toString();
                            m.userIds =  issue.child("userIds").getValue().toString();
                            m.userIdr = issue.child("userIdr").getValue().toString();


                            m.stat = true;
                            if(isRejectedView==false) {
                                if (issue.child("eventStatus").getValue().toString().equals("1")) {
                                    adapter.add(m);
                                }
                            }else{
                                if (issue.child("eventStatus").getValue().toString().equals("3")) {
                                    adapter2.add(m);
                                }
                            }


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

                if(isRejectedView==false) {
                    Solicitudes nd = adapter.getItem(i);
                    //Intent intent = new Intent( getActivity(),   DetailActivity.class);
                    if(nd.stat==false){
                        //  intent.putExtra("id",nd.userIdRes);
                    }else{
                        //intent.putExtra("id",nd.userIdBand);
                    }

                    //intent.putExtra("name", nd.name);

                    //startActivity(intent);
                }else{
                    Solicitudes nd = adapter2.getItem(i);
                    //Intent intent = new Intent( getActivity(),   DetailActivity.class);
                    if(nd.stat==false){
                        //  intent.putExtra("id",nd.userIdRes);
                    }else{
                        //intent.putExtra("id",nd.userIdBand);
                    }

                    //intent.putExtra("name", nd.name);

                    //startActivity(intent);
                }



                //Toast.makeText(getApplicationContext(),nd.id, Toast.LENGTH_LONG).show();
            }
        });
        if(isRejectedView ==false) {
            listView.setAdapter(adapter);
        }else{
            listView.setAdapter(adapter2);
        }

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

            final RequestsAdapter adapter = new RequestsAdapter(
                    getActivity(), R.layout.requests_layout,
                    new ArrayList<Solicitudes>());

            final RejectedAdapter adapter2 = new RejectedAdapter(
                    getActivity(), R.layout.requests_layout,
                    new ArrayList<Solicitudes>());


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
       // queryPrueba = queryPrueba.orderByChild("eventStatus").equalTo(1);
        queryPrueba.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // dataSnapshot is the "issue" node with all children with id 0
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        if(r==false) {
                            Solicitudes m = new Solicitudes();

                            m.name = issue.child("nameRes").getValue().toString();
                            m.dayB = issue.child("dateB").getValue().toString();
                            m.dayE = issue.child("dateEnd").getValue().toString();
                            m.id =  issue.getKey().toString();
                            m.name2 = issue.child("nameBand").getValue().toString();
                            m.userIdBand = issue.child("userIdBand").getValue().toString();
                            m.userIdRes = issue.child("userIdRes").getValue().toString();
                            m.userIds =  issue.child("userIds").getValue().toString();
                            m.userIdr = issue.child("userIdr").getValue().toString();
                            m.stat = false;
                            if(isRejectedView==false) {
                                if (issue.child("eventStatus").getValue().toString().equals("1")) {
                                    adapter.add(m);
                                }
                            }else{
                                if (issue.child("eventStatus").getValue().toString().equals("3")) {
                                    adapter2.add(m);
                                }
                            }

                            //System.out.println(issue.child("musicType").toString());
                        }else{
                            Solicitudes m = new Solicitudes();

                            m.name = issue.child("nameBand").getValue().toString();
                            m.dayB = issue.child("dateB").getValue().toString();
                            m.dayE = issue.child("dateEnd").getValue().toString();
                            m.id =  issue.getKey().toString();
                            m.name2 = issue.child("nameRes").getValue().toString();
                            m.userIdBand = issue.child("userIdBand").getValue().toString();
                            m.userIdRes = issue.child("userIdRes").getValue().toString();
                            m.userIds =  issue.child("userIds").getValue().toString();
                            m.userIdr = issue.child("userIdr").getValue().toString();


                            m.stat = true;
                            if(isRejectedView==false) {
                                if (issue.child("eventStatus").getValue().toString().equals("1")) {
                                    adapter.add(m);
                                }
                            }else{
                                if (issue.child("eventStatus").getValue().toString().equals("3")) {
                                    adapter2.add(m);
                                }
                            }


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

              if(isRejectedView==false) {
                  Solicitudes nd = adapter.getItem(i);
                  //Intent intent = new Intent( getActivity(),   DetailActivity.class);
                  if(nd.stat==false){
                      //  intent.putExtra("id",nd.userIdRes);
                  }else{
                      //intent.putExtra("id",nd.userIdBand);
                  }

                  //intent.putExtra("name", nd.name);

                  //startActivity(intent);
                }else{
                  Solicitudes nd = adapter2.getItem(i);
                  //Intent intent = new Intent( getActivity(),   DetailActivity.class);
                  if(nd.stat==false){
                      //  intent.putExtra("id",nd.userIdRes);
                  }else{
                      //intent.putExtra("id",nd.userIdBand);
                  }

                  //intent.putExtra("name", nd.name);

                  //startActivity(intent);
                }



                //Toast.makeText(getApplicationContext(),nd.id, Toast.LENGTH_LONG).show();
            }
        });
        if(isRejectedView ==false) {
            listView.setAdapter(adapter);
        }else{
            listView.setAdapter(adapter2);
        }

    }



}
