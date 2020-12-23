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
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class userReg extends AppCompatActivity {

    EditText uName, uPhn, uAdd, uEmail, uPass;
    private FirebaseAuth mAuth;
    String email,password,name,phone,address;
    FirebaseFirestore fbfs;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_reg);
        this.setTitle("User Registration");
        mAuth = FirebaseAuth.getInstance();
        fbfs=FirebaseFirestore.getInstance();
        uName=findViewById(R.id.userName);
        uPhn=findViewById(R.id.userPhn);
        uAdd=findViewById(R.id.userAdd);
        uEmail=findViewById(R.id.userEmail);
        uPass=findViewById(R.id.userPass);
    }

    public void userRegister(View view) {

     email=uEmail.getText().toString();
     password=uPass.getText().toString();
     name=uName.getText().toString();
     phone=uPhn.getText().toString();
     address=uAdd.getText().toString();


        if(name.isEmpty())
        {
            uName.setError("This Field Can't be Empty");
            uName.requestFocus();
            return;
        }
        if(phone.isEmpty())
        {
            uPhn.setError("This Field Can't be Empty");
            uPhn.requestFocus();
            return;
        }
        if(phone.length()!=11){
            uPhn.setError("Length should be 11!");
            uPhn.requestFocus();
            return;
        }
        if(address.isEmpty())
        {
            uAdd.setError("This Field Can't be Empty");
            uAdd.requestFocus();
            return;
        }
        if(email.isEmpty())
        {
            uEmail.setError("Enter an email address");
            uEmail.requestFocus();
            return;
        }

        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            uEmail.setError("Enter a valid email address");
            uEmail.requestFocus();
            return;
        }
        if(password.isEmpty())
        {
            uPass.setError("Enter a password");
            uPass.requestFocus();
            return;
        }
        if(password.length()<6){
            uPass.setError("Minimum length is 6!");
            uPass.requestFocus();
            return;
        }
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            importData(mAuth.getCurrentUser().getUid());
                            Toast.makeText(getApplicationContext(),"Bingo ! Success", Toast.LENGTH_SHORT).show();

                            Intent i=new Intent(getApplicationContext(),MainActivity.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(i);

                        } else if(task.getException()instanceof FirebaseAuthUserCollisionException){
                            Toast.makeText(getApplicationContext(),"Oops! Email already registered", Toast.LENGTH_SHORT).show();

                        }
                        else{
                            Toast.makeText(getApplicationContext(),""+task.getException(), Toast.LENGTH_LONG).show();
                        }


                    }
                });

    }
    public void importData(String userId){
        DocumentReference docRef=fbfs.collection("All_Users").document(userId);
        Map<String,Object> data=new HashMap<>();
        data.put("Org",false);
        data.put("Name",name);
        data.put("Email",email);
        data.put("Password",password);
        data.put("Address",address);
        data.put("Phone",phone);
        docRef.set(data);

    }

}
