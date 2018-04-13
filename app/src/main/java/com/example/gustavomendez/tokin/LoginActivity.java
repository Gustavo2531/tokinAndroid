package com.example.gustavomendez.tokin;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;

public class LoginActivity extends AppCompatActivity {

    Button loginBtn;
    EditText userEmailEdit, userPasswordEdit;
    Button forgotPassword;
    //String Fields
    String userEmailString, userPasswordString;

    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListener;
    DatabaseReference mDatabaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginBtn = (Button) findViewById(R.id.loginBtn);
        userEmailEdit = (EditText) findViewById(R.id.loginEmailEditText);
        userPasswordEdit = (EditText) findViewById(R.id.loginPassWordEditText);
        forgotPassword = (Button) findViewById(R.id.forgot);


        mDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Users");

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null)
                {

                    final String emailForVer = user.getEmail();

                    mDatabaseRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            checkUserValidation(dataSnapshot, emailForVer);

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }else
                {



                }


            }
        };



        //ON CLICK LISTENER
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in = new Intent( LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(in);

            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Perform login operation
                userEmailString = userEmailEdit.getText().toString().trim();
                userPasswordString = userPasswordEdit.getText().toString().trim();

                if(!TextUtils.isEmpty(userEmailString) && !TextUtils.isEmpty(userPasswordString))
                {

                    mAuth.signInWithEmailAndPassword(userEmailString, userPasswordString).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful())
                            {

                                mDatabaseRef.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {

                                        checkUserValidation(dataSnapshot, userEmailString);

                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });

                            }
                            else
                            {
                                Toast.makeText(LoginActivity.this, "User Login Failed" , Toast.LENGTH_LONG).show();
                            }

                        }
                    });

                }


            }
        });



    }

    private void checkUserValidation(DataSnapshot dataSnapshot, String emailForVer) {

        Iterator iterator = dataSnapshot.getChildren().iterator();

        while (iterator.hasNext())
        {

            DataSnapshot dataUser = (DataSnapshot) iterator.next();

            if( dataUser.child("emailUser").getValue().toString().equals(emailForVer))
            {

                if (dataUser.child("isVerified").getValue().toString().equals("unverified"))
                {
                    KeyUser keyUser = new KeyUser();
                    keyUser.KU = dataUser.child("userKey").getValue().toString();
                    Intent in = new Intent( LoginActivity.this, BandOrRestaurantActivity.class);
                    in.putExtra("USER_KEY" , dataUser.child("userKey").getValue().toString());

                    startActivity(in);

                }else
                {
                    KeyUser keyUser = new KeyUser();

                    keyUser.KU = dataUser.child("userKey").getValue().toString();
                    keyUser.start();

                    startActivity(new Intent(LoginActivity.this, MainActivity2.class));
                }

            }

        }


    }


    @Override
    protected void onStart() {
        super.onStart();

        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();

        mAuth.removeAuthStateListener(mAuthListener);
    }
}
