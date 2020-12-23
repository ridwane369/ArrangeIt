package com.andsofts.ridwane.arrangeit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import javax.annotation.Nullable;

public class profile extends AppCompatActivity {

    private FirebaseAuth mAuth;
    FirebaseFirestore fbfs;
    DocumentReference docRef;
    String name;
    boolean org;
    TextView nameTextView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        mAuth=FirebaseAuth.getInstance();
        fbfs=FirebaseFirestore.getInstance();
        docRef=fbfs.collection("All_Users").document(mAuth.getCurrentUser().getUid());
        nameTextView=findViewById(R.id.nameTextView);
        getData();
        this.setTitle("Welcome!");



    }

    private void getData() {

        docRef.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                name=documentSnapshot.getString("Name");
                nameTextView.setText("Hello "+name+"!");
                org=documentSnapshot.getBoolean("Org");
                invalidateOptionsMenu();

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(name!=null){

       if(org) {
           getMenuInflater().inflate(R.menu.menu_layout, menu);


       }else if(!org){
           getMenuInflater().inflate(R.menu.user_menu, menu);


       }
        }
        return super.onCreateOptionsMenu(menu);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.logOut){
            mAuth.signOut();
            finish();
            Intent i=new Intent(getApplicationContext(),MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
        }
        else if(item.getItemId()==R.id.viewOrg){
            Intent i=new Intent(getApplicationContext(),ShowOrgs.class);
            startActivity(i);

        }
        else if(item.getItemId()==R.id.createEvent){
            Intent i=new Intent(getApplicationContext(),createPost.class);
            startActivity(i);

        }
        else if(item.getItemId()==R.id.upcomingEvents){
            Intent i=new Intent(getApplicationContext(),ShowEvents.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }


}
