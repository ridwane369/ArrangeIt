package com.andsofts.ridwane.arrangeit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

public class MainActivity extends AppCompatActivity {

    EditText em;
    EditText pass;
    String email,password;
    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

        em=findViewById(R.id.email);
        pass=findViewById(R.id.pass);


    }

    public void userRegPage(View view) {
        Intent i=new Intent(this,userReg.class);
        startActivity(i);
    }



    public void orgRegPage(View view) {
        Intent i=new Intent(this,orgReg.class);
        startActivity(i);
    }

    @Override
    protected void onStart() {
        super.onStart();
        try{
        if(mAuth.getCurrentUser()!=null){
            finish();
            Intent i=new Intent(getApplicationContext(), profile.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            startActivity(i);
        }}catch(Exception e){

        }
    }

    public void login(View view) {
        email=em.getText().toString();
        password=pass.getText().toString();
        if(email.isEmpty())
        {
            em.setError("Enter an email address");
            em.requestFocus();
            return;
        }

        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            em.setError("Enter a valid email address");
            em.requestFocus();
            return;
        }
        if(password.isEmpty())
        {
            pass.setError("Enter a password");
            pass.requestFocus();
            return;
        }
        if(password.length()<6){
            pass.setError("Minimum length is 6!");
            pass.requestFocus();
            return;
        }
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    finish();
                    Intent i=new Intent(getApplicationContext(), profile.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    startActivity(i);
                }else if(task.getException()instanceof FirebaseAuthInvalidUserException){
                    em.setError("Email Not Registered");
                    em.requestFocus();

                }else if(task.getException()instanceof FirebaseAuthInvalidCredentialsException){
                    pass.setError("Wrong Password");
                    pass.requestFocus();
                }else{
                    Toast.makeText(getApplicationContext(),""+task.getException(), Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}