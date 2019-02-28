package com.fantastic4.arpantheblooddonationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import static com.fantastic4.arpantheblooddonationapp.MainActivity.database;
import static com.google.firebase.database.FirebaseDatabase.getInstance;

public class DonorList extends AppCompatActivity {

    String city;
    String group;
    ArrayList<String> donorList;
    ListView listView;
    ArrayAdapter<String> arrayAdapter;
    public static ArrayList<Donor> donorInfo;

       /* FirebaseUser current_user= FirebaseAuth.getInstance().getCurrentUser();
        String uid=current_user.getUid();
        FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();*/


    private DatabaseReference myRef = getInstance().getReference("Donors");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_list);

        Bundle extras = getIntent().getExtras();
        city = extras.getString("city");
        group = extras.getString("group");
        Log.i("NAME",city);
        Log.i("NAME",group);

        donorList = new ArrayList<>();
        donorInfo = new ArrayList<>();
        listView = findViewById(R.id.donorListView);
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, donorList);
        listView.setAdapter(arrayAdapter);


        myRef.child(city).child(group).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Donor donor = dataSnapshot.getValue(Donor.class);
                donorInfo.add(donor);
                String donorInfo = donor.name + "   \n" + donor.contactNumber;
                donorList.add(donorInfo);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
