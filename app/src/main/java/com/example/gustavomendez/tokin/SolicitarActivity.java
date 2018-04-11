package com.example.gustavomendez.tokin;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.Calendar;

public class SolicitarActivity extends Activity {
    private DatePicker datePicker;
    private Calendar calendar;
    private TextView dateView, timeViewB, nameBR;
    private Query queryPrueba;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private EditText duracionP;
    private int year, month, day;
    private boolean r=false;
    private boolean l=false;
    private boolean res=true;
    private  String id, tel, name, dir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitar);
        nameBR = (TextView) findViewById(R.id.textViewNameBR);
        dateView = (TextView) findViewById(R.id.textViewDiaS);
        timeViewB = (TextView) findViewById(R.id.textTimeInicioS);
        duracionP = (EditText) findViewById(R.id.editTimeDuracionS);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("eventos");
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);


        id = (String)getIntent().getSerializableExtra("id");
        tel = (String)getIntent().getSerializableExtra("tel");
        name = (String)getIntent().getSerializableExtra("name");
        dir = (String)getIntent().getSerializableExtra("address");
        nameBR.setText(name);
        //showDate(year, month+1, day);
    }
    public void onButtonClickedS(View v){
        l=true;
        DialogFragment newFragment = new DatePickerFragment1();
        newFragment.show(getFragmentManager(),"DatePicker");
    }

    public void onButtonClickedTimeS(View v){
        r=true;
        DialogFragment newFragment = new TimePickerFragment1();
        newFragment.show(getFragmentManager(),"TimePicker");
    }
    public void onClickAgregarS(View v){

        if(r==true&&l==true){
        String dateB=dateView.getText().toString();
        dateB=dateB+"T"+timeViewB.getText().toString()+":00.598Z";

        //String dateE=dateView.getText().toString();
        //dateE.concat("T"+timeViewB.getText().toString()+":00.598Z");


        String s =timeViewB.getText().toString().charAt(0)+""+timeViewB.getText().toString().charAt(1);
        String minutes = timeViewB.getText().toString().charAt(3)+""+timeViewB.getText().toString().charAt(4);
        String dateE;
        Integer m = Integer.parseInt(s);

        Integer dur=Integer.parseInt(duracionP.getText().toString());
        if((m+dur)>=24){
            dateE=dateView.getText().toString();
            dateE=dateE.substring(0,8);
            String dateAux = dateView.getText().toString();
            Integer newDay = Integer.parseInt(dateAux.substring(8,dateView.getText().length()));
            newDay+=1;
            int timeE=m+dur;
            if(timeE==24){
                timeE=0;
            }
            if(timeE==25){
                timeE=1;
            }
            if(timeE==26){
                timeE=2;
            }
            if(timeE==27){
                timeE=3;
            }
            if(timeE==28){
                timeE=4;
            }
            if(timeE==29){
                timeE=5;
            }
            dateE=dateE+newDay+"T0"+timeE+":"+minutes+":00.598Z";

        }else{
            dateE=dateView.getText().toString();
            int timeE=m+dur;
            dateE=dateE+"T"+timeE+":"+minutes+":00.598Z";

        }

        KeyUser keyUser = new KeyUser();
        keyUser.start();
            if(keyUser.typeref.equals("band")) {

                res=false;
            }else{


            }

            String nomSing=keyUser.Restaurant.toString();
            EventPojo chatPojo;
            if(res == true ) {
                chatPojo = new EventPojo(""+name, ""+nomSing, keyUser.KU, id, id, keyUser.KU, 1, dateB, dateE);
            }else{
                chatPojo = new EventPojo(""+nomSing, ""+name, id, keyUser.KU, keyUser.KU, id, 1, dateB, dateE);
            }
            databaseReference.push().setValue(chatPojo);
            duracionP.setText("");
            Toast.makeText(getApplicationContext(), "Evento Guardado",
                    Toast.LENGTH_SHORT)
                    .show();
        }else{
            Toast.makeText(getApplicationContext(), "Elige Dia y hora",
                    Toast.LENGTH_SHORT)
                    .show();
        }

    }
}
