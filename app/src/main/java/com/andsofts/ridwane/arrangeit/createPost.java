package com.andsofts.ridwane.arrangeit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class createPost extends AppCompatActivity {
    EditText etype,location,date,contact;
    private FirebaseAuth mAuth;
    String et,loc,dt,cntct;
    FirebaseFirestore fstore;
    String userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);
        etype=findViewById(R.id.eventType);
        location=findViewById(R.id.eventLoc);
        date=findViewById(R.id.eventDate);
        contact=findViewById(R.id.contactNum);
        fstore=FirebaseFirestore.getInstance();
        mAuth=FirebaseAuth.getInstance();
    }
    public void createEvent(View view){
        et=etype.getText().toString();
        loc=location.getText().toString();
        dt=date.getText().toString();
        cntct=contact.getText().toString();
        String createdBy=mAuth.getCurrentUser().getUid();
        uploadData(createdBy);
        Toast.makeText(getApplicationContext(),"Bingo ! Event Created", Toast.LENGTH_SHORT).show();
        Intent i=new Intent(getApplicationContext(),profile.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        finish();
        startActivity(i);


    }
    public void uploadData(String createdBy){
        DocumentReference docRef=fstore.collection("Events").document();
        Map<String,Object> data=new HashMap<>();
        data.put("Event_Type",et);
        data.put("Location",loc);
        data.put("Date",dt);
        data.put("Contact_Details",cntct);
        data.put("Created_By",createdBy);
        docRef.set(data);
    }
}
