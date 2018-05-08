package com.example.gustavomendez.tokin;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class DatePickerFragment extends DialogFragment implements
            DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it

            return new DatePickerDialog(getActivity(), android.R.style.Theme_Holo_Light_Dialog_NoActionBar,this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // do some stuff for example write on log and update TextField on activity

            TextView f=(TextView) getActivity().findViewById(R.id.textViewDateEventP);
            if(month+1<10){
                f.setText(""+year+"-0"+(month+1)+"-"+day);
            }else{
                f.setText(""+year+"-"+(month+1)+"-"+day);
            }
        }
    }
