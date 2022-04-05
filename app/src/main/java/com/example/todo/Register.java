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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    EditText email;
    EditText password,phn;
    Button btnlogin;
    Button reg;
    FirebaseAuth mauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);

        btnlogin = findViewById(R.id.btnlogin);
        password = findViewById(R.id.passwd);
        email = findViewById(R.id.email_id);
        reg = findViewById(R.id.register);

        mauth = FirebaseAuth.getInstance();

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();
            }
        });
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this,login.class));
            }
        });
    }
    private void createUser()
    {
        EditText ed=findViewById(R.id.email_id);
        String s=ed.getText().toString();
        EditText pw=findViewById(R.id.passwd);
        String s1=pw.getText().toString();


        if(TextUtils.isEmpty(s))
        {
            email.setError("Email cannot be empty");
            email.requestFocus();
        }
        else if(TextUtils.isEmpty(s1))
        {
            password.setError("Password cannot be empty");
            password.requestFocus();
        }

        else
        {
            mauth.createUserWithEmailAndPassword (s,s1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(Register.this,"Registered successfully",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(Register.this,MainActivity.class));
                    }
                    else
                    {
                        Toast.makeText(Register.this,"Registration Error "+task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
}