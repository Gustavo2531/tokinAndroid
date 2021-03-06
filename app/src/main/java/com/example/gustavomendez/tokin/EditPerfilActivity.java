package com.example.gustavomendez.tokin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.content.ContentValues.TAG;

public class EditPerfilActivity extends Activity {
    DatabaseReference mDataRef;
    Button saveBtn;
    FirebaseAuth mAuth;

    TextView tone, ttwo, tthree, tfour, tfive;
    EditText eone, etwo, ethree, efour, efive;
    String userNameString, userContactString, userTypeString, musicTypeString, pwd;
    String resNameString, resContactString, resAddressString, resCityString, emailString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_perfil);
        final KeyUser keyUser = new KeyUser();
        mDataRef = FirebaseDatabase.getInstance().getReference().child("Users").child(keyUser.KU);
        mAuth = FirebaseAuth.getInstance();

        TextView tone = (TextView)findViewById(R.id.tone);
        TextView ttwo = (TextView)findViewById(R.id.ttwo);
        TextView tthree = (TextView)findViewById(R.id.tthree);
        TextView tfour = (TextView)findViewById(R.id.tfour);
        TextView tfive = (TextView)findViewById(R.id.tfive);
        Button  saveBtn = (Button)findViewById(R.id.saveBtn);
      final   EditText eone = (EditText)findViewById(R.id.eone);
        final EditText etwo = (EditText)findViewById(R.id.etwo);
        final EditText ethree = (EditText)findViewById(R.id.ethree);
        final EditText efour = (EditText)findViewById(R.id.efour);
        final EditText efive = (EditText)findViewById(R.id.efive);

        if(keyUser.typeref.equals("band")) {
            tone.setText("Nombre de la banda");
            ttwo.setText("Contacto");
            tthree.setText("Correo");
            tfour.setText("Tipo de Musica");
            tfive.setText("Contraseña");

            eone.setHint(KeyUser.userName);
            etwo.setHint(KeyUser.userContact);
            ethree.setHint(KeyUser.emailAddress);
            efour.setHint(KeyUser.musicType);
            efive.setHint("****");




            System.out.println(userNameString);

        }
        if(keyUser.typeref.equals("res")) {
            tone.setText("Nombre del restaurante");
            ttwo.setText("Telefono");
            tthree.setText("Direccion");
            tfour.setText("Correo");
            tfive.setText("Password");

            eone.setHint(KeyUser.Restaurant);
            etwo.setHint(KeyUser.Phone);
            ethree.setHint(KeyUser.Address);
            efour.setHint(KeyUser.emailAddress);
            efive.setHint("****");

        }


            saveBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                    pwd = efive.getText().toString();

                    if(keyUser.typeref.equals("band")) {
                        userNameString = eone.getText().toString();
                        userContactString = etwo.getText().toString();
                        emailString = ethree.getText().toString();
                        musicTypeString = efour.getText().toString();

                        if (!userNameString.matches("")) {
                            mDataRef.child("userName").setValue(userNameString);

                        }

                        if (!userContactString.matches("")) {
                            mDataRef.child("userContact").setValue(userContactString);

                        }

                        if (!musicTypeString.matches("")) {
                            mDataRef.child("musicType").setValue(musicTypeString);
                        }



                        if (!emailString.matches("")) {
                            mDataRef.child("emailUser").setValue(emailString);
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                            user.updateEmail(emailString)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Log.d(TAG, "User email address updated.");
                                            }
                                        }
                                    });

                        }
                        if(!pwd.matches("")){
                            mDataRef.child("passWordUser").setValue(pwd);
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                            user.updatePassword(pwd)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Log.d(TAG, "User password updated.");
                                            }
                                        }
                                    });
                        }
                    }
                    if(keyUser.typeref.equals("res")) {
                        resNameString = eone.getText().toString();
                        resContactString = etwo.getText().toString();
                        resAddressString = ethree.getText().toString();
                        emailString = efour.getText().toString();


                        if(!resNameString.matches("")){
                            mDataRef.child("Restaurant").setValue(resNameString);

                        }


                        if(!resContactString.matches("")){
                            mDataRef.child("Phone").setValue(resContactString);

                        }
                        if(!resAddressString.matches("")){
                            mDataRef.child("Address").setValue(resAddressString);
                        }
                        if(!emailString.matches("")){
                            mDataRef.child("emailUser").setValue(emailString);
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                            user.updateEmail(emailString)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Log.d(TAG, "User email address updated.");
                                            }
                                        }
                                    });

                        }
                        if(!pwd.matches("")){
                            mDataRef.child("passWordUser").setValue(pwd);
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                            user.updatePassword(pwd)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Log.d(TAG, "User password updated.");
                                            }
                                        }
                                    });
                        }


                    }


                       Toast.makeText(EditPerfilActivity.this, "Informacion actualizada", Toast.LENGTH_LONG).show();
                    mAuth.signOut();
                    finish();
                        startActivity(new Intent(EditPerfilActivity.this, LoginActivity.class));

                    }

                });

    }

}
