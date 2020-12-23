package com.andsofts.ridwane.arrangeit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

public class ShowEvents extends AppCompatActivity {
    RecyclerView list;
    FirebaseFirestore fbfs;
    List<Events> eventList;
    EventListAdapter eventAdapter;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_events);
        this.setTitle("Upcoming Events");
        eventList=new ArrayList<>();
        list=findViewById(R.id.event_list);
        list.setHasFixedSize(true);
        list.setLayoutManager(new LinearLayoutManager(this));
        //   mainList.setAdapter(userAdapter);
        mAuth = FirebaseAuth.getInstance();

        fbfs= FirebaseFirestore.getInstance();
        eventAdapter=new EventListAdapter(eventList);
        fbfs.collection("Events").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                try{
                    for(DocumentChange doc: queryDocumentSnapshots.getDocumentChanges()){
                        if(doc.getType()==DocumentChange.Type.ADDED){

                                //All the events
                                System.out.println(doc.getDocument().getString("Event_type"));
                                Events events=doc.getDocument().toObject(Events.class);
                                eventList.add(events);
                                eventAdapter.notifyDataSetChanged();


                        }
                    }
                }catch(Exception ex){

                }

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        list.setAdapter(eventAdapter);
        try{
            if(mAuth.getCurrentUser()==null){
                finish();
            }
        }catch(Exception e){
            finish();
        }
    }
}
