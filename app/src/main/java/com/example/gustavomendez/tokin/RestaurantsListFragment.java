package com.example.gustavomendez.tokin;


import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;


public class RestaurantsListFragment extends ListFragment {

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
    private Restaurant adapter;
    private Button logout;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;

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
        final RestaurantAdapter adapter = new RestaurantAdapter(
                getActivity(), R.layout.restaurants_layout,
                new ArrayList<Restaurant>());

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("Users");
        KeyUser keyUser = new KeyUser();
        keyUser.start();
        if(keyUser.typeref.equals("band")) {
            queryPrueba = databaseReference.orderByChild("type").equalTo("res");

        }else{
            r=false;
            queryPrueba = databaseReference.orderByChild("type").equalTo("band");
        }
        queryPrueba.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // dataSnapshot is the "issue" node with all children with id 0
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        if(r==false) {
                            Restaurant m = new Restaurant();
                            m.name = issue.child("userName").getValue().toString();
                            m.address = issue.child("musicType").getValue().toString();
                            m.phone = issue.child("userContact").getValue().toString();
                            m.id =  issue.child("userKey").getValue().toString();
                            adapter.add(m);

                            //System.out.println(issue.child("musicType").toString());
                        }else{
                            Restaurant m = new Restaurant();
                            m.name = issue.child("Restaurant").getValue().toString();
                            m.address = issue.child("Address").getValue().toString();
                            m.phone = issue.child("Phone").getValue().toString();
                            m.id =  issue.child("userKey").getValue().toString();
                            adapter.add(m);
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
        setListAdapter(adapter);
    }
/**
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
**/

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
    @Override
    public void onPause(){
        super.onPause();
    }
    @Override
    public void onResume(){
        super.onResume();
        if(firebaseAuth!=null){
            firebaseAuth.addAuthStateListener(stateListener);
        }
    }
    private void clean(){
        name="";
        //adapter.clear();
        if(childEventListener != null){
            databaseReference.removeEventListener(childEventListener);
            childEventListener = null;
        }
    }


}
