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

public class ProfileBanda extends AppCompatActivity {

    EditText userName, userContact, userType, musicType;
    Button saveBtn;

    DatabaseReference mDataRef;

    String keyUser;

    String userNameString, userContactString, userTypeString, musicTypeString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilebanda);

        //GET USER KEY FROM INTENT
        keyUser = getIntent().getStringExtra("USER_KEY");



        //FIREBASE DATABASE REFERENCE
        mDataRef = FirebaseDatabase.getInstance().getReference().child("Users").child(keyUser);

        //ASSIGN ID'S
        userName = (EditText) findViewById(R.id.userNameEditText);
        userContact = (EditText) findViewById(R.id.userPhnoEditText);
        saveBtn = (Button) findViewById(R.id.userProfileBtn);
        userType = (EditText) findViewById(R.id.userTypeText);
        musicType = (EditText) findViewById(R.id.musicType);



        //SAVE BTN LOGIC
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userNameString = userName.getText().toString();
                userContactString = userContact.getText().toString();
                userTypeString = userType.getText().toString();
                musicTypeString = musicType.getText().toString();



                if(!TextUtils.isEmpty(userNameString) && !TextUtils.isEmpty(userContactString)
                        && !TextUtils.isEmpty(userTypeString)
                        && !TextUtils.isEmpty(musicTypeString))
                {

                    mDataRef.child("userName").setValue(userNameString);
                    mDataRef.child("userContact").setValue(userContactString);
                    mDataRef.child("userType").setValue(userTypeString);
                    mDataRef.child("musicType").setValue(musicTypeString);
                    mDataRef.child("type").setValue("band");



                    mDataRef.child("isVerified").setValue("verfied");
                    KeyUser keyUser = new KeyUser();
                    keyUser.start();

                    Toast.makeText(ProfileBanda.this, "User profile added", Toast.LENGTH_LONG).show();

                    startActivity(new Intent(ProfileBanda.this, MainActivity2.class));

                }

            }
        });



    }
}
