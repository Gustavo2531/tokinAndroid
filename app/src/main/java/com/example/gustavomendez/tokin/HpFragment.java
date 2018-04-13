package com.example.gustavomendez.tokin;


import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class HpFragment extends Fragment {
    private Context context;




    public HpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_help, container, false);


        Button button = (Button) rootView.findViewById(R.id.buttonEditPerfil);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent( getActivity() , ChatBotActivity.class);
                startActivity(it);
            }
        });
        return rootView;
    }




}
