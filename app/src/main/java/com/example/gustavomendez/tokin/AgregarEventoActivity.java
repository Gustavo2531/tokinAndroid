package com.example.gustavomendez.tokin;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class AgregarEventoActivity extends Activity {
    private DatePicker datePicker;
    private Calendar calendar;
    private TextView dateView, timeViewB;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private EditText duracionP;
    private int year, month, day;
    private boolean r=false;
    private boolean l=false;
    private String dateInicio, medId, medLastName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_evento);
        dateView = (TextView) findViewById(R.id.textViewDateEventP);
        timeViewB = (TextView) findViewById(R.id.textTimeInicioP);
        duracionP = (EditText) findViewById(R.id.editTextDuracionP);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("eventos");
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        //showDate(year, month+1, day);
    }

    public void onButtonClicked(View v){
        l=true;
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(),"DatePicker");
    }

    public void onButtonClickedTime(View v){
        r=true;
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getFragmentManager(),"TimePicker");
    }
    public void onClickAgregar(View v){

        if(r==true&&l==true){
            String dateB=dateView.getText().toString();
            dateB=dateB+"T"+timeViewB.getText().toString()+":00.598Z";

            //String dateE=dateView.getText().toString();
            //dateE.concat("T"+timeViewB.getText().toString()+":00.598Z");


            String s =timeViewB.getText().toString().charAt(0)+""+timeViewB.getText().toString().charAt(1);
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

                dateE=dateE+newDay+"T"+timeE+":00.598Z";
            }else{
                dateE=dateView.getText().toString();
                int timeE=m+dur;
                dateE=dateE+"T"+timeE+":00.598Z";
            }

            KeyUser keyUser = new KeyUser();
            keyUser.start();
            EventPojo chatPojo = new EventPojo("Evento Privado","Evento Privado", keyUser.KU,keyUser.KU,keyUser.KU,keyUser.KU,2,dateB,dateE);
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
    /**
    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        l=true;
        showDialog(999);
        Toast.makeText(getApplicationContext(), "ca",
                Toast.LENGTH_SHORT)
                .show();
    }
    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {

            return new DatePickerDialog(this,
                    myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day

                    showDate(arg1, arg2+1, arg3);
                }
            };
    private void showDate(int year, int month, int day) {

        dateView.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
        dateInicio=""+year+"-"+month+"-"+day+"T";
        System.out.println(dateInicio);
    }**/
}
