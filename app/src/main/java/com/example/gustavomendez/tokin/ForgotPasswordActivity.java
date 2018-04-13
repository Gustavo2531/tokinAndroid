package com.example.gustavomendez.tokin;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends Activity {
    Button forgotPassword;
    FirebaseAuth mAuth;
    EditText userEmailEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        forgotPassword = (Button) findViewById(R.id.forgot);
        userEmailEdit = (EditText) findViewById(R.id.loginEmailEditText);
        mAuth = FirebaseAuth.getInstance();

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailAddress =  userEmailEdit.getText().toString();


                mAuth.sendPasswordResetEmail(emailAddress)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(ForgotPasswordActivity.this, "Email Sent", Toast.LENGTH_LONG).show();

                                }
                            }
                        });


            }
        });

    }
}
