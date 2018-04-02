package com.example.gustavomendez.tokin;


import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilFragment extends Fragment {
    private Context context;




    public PerfilFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        KeyUser keyUser = new KeyUser();
        keyUser.start();
        View rootView = inflater.inflate(R.layout.fragment_perfil, container, false);

        TextView one = (TextView) rootView.findViewById(R.id.one);
        TextView two = (TextView) rootView.findViewById(R.id.two);
        TextView three = (TextView)rootView.findViewById(R.id.three);
        TextView four = (TextView) rootView.findViewById(R.id.four);
        System.out.println("ref "+keyUser.typeref);
        if(keyUser.typeref.equals("band")) {
            one.setText(keyUser.userName);
            two.setText(keyUser.userContact);
            three.setText(keyUser.musicType);
            four.setText(keyUser.userType);
        }
        if(keyUser.typeref.equals("res")) {
            one.setText(keyUser.Restaurant);
            two.setText(keyUser.Address);
            three.setText(keyUser.City);
            four.setText(keyUser.Phone);
        }




        Button button = (Button) rootView.findViewById(R.id.buttonEditPerfil);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDetail();
            }
        });
        return rootView;
    }

    public void updateDetail() {
        KeyUser keyUser = new KeyUser();
        System.out.println("ref2 "+keyUser.typeref);

        Intent it = new Intent( getActivity() , EditPerfilActivity.class);
        startActivity(it);
    }


}
