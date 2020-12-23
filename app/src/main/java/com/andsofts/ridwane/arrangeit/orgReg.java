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

public class orgReg extends AppCompatActivity {
    EditText oName, oPhn, oAdd, oEmail, oPass;
    private FirebaseAuth mAuth;
    String email,password,name,phone,address;
    FirebaseFirestore fstore;
    String userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_org_reg);
        this.setTitle("Organiser Registration");
        mAuth = FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();
        oName=findViewById(R.id.orgName);
        oPhn=findViewById(R.id.orgPhn);
        oAdd=findViewById(R.id.orgAdd);
        oEmail=findViewById(R.id.orgEmail);
        oPass=findViewById(R.id.orgPass);

    }

    public void orgRegister(View view) {
         email=oEmail.getText().toString();
         password=oPass.getText().toString();
         name=oName.getText().toString();
         phone=oPhn.getText().toString();
         address=oAdd.getText().toString();


        if(name.isEmpty())
        {
            oName.setError("This Field Can't be Empty");
            oName.requestFocus();
            return;
        }
        if(phone.isEmpty())
        {
            oPhn.setError("This Field Can't be Empty");
            oPhn.requestFocus();
            return;
        }
        if(phone.length()!=11){
            oPhn.setError("Length should be 11!");
            oPhn.requestFocus();
            return;
        }
        if(address.isEmpty())
        {
            oAdd.setError("This Field Can't be Empty");
            oAdd.requestFocus();
            return;
        }
        if(email.isEmpty())
        {
            oEmail.setError("Enter an email address");
            oEmail.requestFocus();
            return;
        }

        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            oEmail.setError("Enter a valid email address");
            oEmail.requestFocus();
            return;
        }
        if(password.isEmpty())
        {
            oPass.setError("Enter a password");
            oPass.requestFocus();
            return;
        }
        if(password.length()<6){
            oPass.setError("Minimum length is 6!");
            oPass.requestFocus();
            return;
        }
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            importData(mAuth.getCurrentUser().getUid());
                            Toast.makeText(getApplicationContext(),"Bingo ! Success", Toast.LENGTH_SHORT).show();
                            userId=mAuth.getCurrentUser().getUid();
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
        DocumentReference docRef=fstore.collection("All_Users").document(userId);
        Map<String,Object> data=new HashMap<>();
        data.put("Org",true);
        data.put("Name",name);
        data.put("Email",email);
        data.put("Password",password);
        data.put("Address",address);
        data.put("Phone",phone);
        docRef.set(data);

    }

}
