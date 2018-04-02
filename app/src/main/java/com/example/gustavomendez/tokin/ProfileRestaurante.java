package com.example.gustavomendez.tokin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileRestaurante extends AppCompatActivity {

    EditText resName, resContact, resAddress, resCity;
    Button saveBtn;

    DatabaseReference mDataRef;

    String keyUser;

    String resNameString, resContactString, resAddressString, resCityString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_restaurante);

        //GET USER KEY FROM INTENT
        keyUser = getIntent().getStringExtra("USER_KEY");



        //FIREBASE DATABASE REFERENCE
        mDataRef = FirebaseDatabase.getInstance().getReference().child("Users").child(keyUser);

        //ASSIGN ID'S
        resName = (EditText) findViewById(R.id.userNameEditText);
        resContact = (EditText) findViewById(R.id.userPhnoEditText);
        saveBtn = (Button) findViewById(R.id.userProfileBtn);
        resAddress = (EditText) findViewById(R.id.userAddress);
        resCity = (EditText) findViewById(R.id.userCity);



        //SAVE BTN LOGIC
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                resNameString = resName.getText().toString();
                resContactString = resContact.getText().toString();
                resAddressString = resAddress.getText().toString();
                resCityString = resCity.getText().toString();



                if(!TextUtils.isEmpty(resNameString) && !TextUtils.isEmpty(resContactString)
                        && !TextUtils.isEmpty(resAddressString)
                        && !TextUtils.isEmpty(resCityString))
                {

                    mDataRef.child("Restaurant").setValue(resNameString);
                    mDataRef.child("Phone").setValue(resContactString);
                    mDataRef.child("Address").setValue(resAddressString);
                    mDataRef.child("City").setValue(resCityString);
                    mDataRef.child("type").setValue("res");




                    mDataRef.child("isVerified").setValue("verfied");
                    KeyUser keyUser = new KeyUser();
                    keyUser.start();

                    Toast.makeText(ProfileRestaurante.this, "User profile added", Toast.LENGTH_LONG).show();

                    startActivity(new Intent(ProfileRestaurante.this, MainActivity2.class));

                }

            }
        });



    }
}