package com.example.todo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.SignInMethodQueryResult;

public class login extends AppCompatActivity {
EditText ed1,ed2;
Button btn;
    FirebaseAuth mauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ed1 = findViewById(R.id.vermail);
        ed2 = findViewById(R.id.verpw);
        btn = findViewById(R.id.verbtn);
        mauth = FirebaseAuth.getInstance();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText ed1 = findViewById(R.id.vermail);
                String s1 = ed1.getText().toString();
                EditText ed2 = findViewById(R.id.verpw);
                String s2 = ed2.getText().toString();
                if (TextUtils.isEmpty(s1)) {
                    ed1.setError("Email cannot be empty");
                    ed1.requestFocus();
                } else if (TextUtils.isEmpty(s2)) {
                    ed2.setError("Password cannot be empty");
                    ed2.requestFocus();
                } else {
                    FirebaseUser mfirebase = mauth.getCurrentUser();
                    mauth.fetchSignInMethodsForEmail(ed1.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                                @Override
                                public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                                    if (task.isSuccessful()) {

                                        boolean check = !task.getResult().getSignInMethods().isEmpty();
                                        if (!check) {
                                            Toast.makeText(login.this,"Register your mail ID",Toast.LENGTH_LONG).show();
                                        }
                                        else
                                        {
                                            Intent intent = new Intent(login.this, MainActivity.class);
                                            intent.putExtra("email", ed1.getText().toString());
                                            startActivity(intent);

                                        }


                                    }
                                }
                            });

                }}
                            });
                }}