package com.fantastic4.arpantheblooddonationapp;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    public static FirebaseDatabase database;

    public  void donateBlood(View view){
        Button donateBlood = findViewById(R.id.donateBlood);
        Intent donorIntent = new Intent(MainActivity.this, DonorLogin.class);
        startActivity(donorIntent);
    }

    public void requestBlood(View view){
        Button requestBlood = findViewById(R.id.requestBlood);
        Intent requestIntent = new Intent(MainActivity.this, RequestBlood.class);
        startActivity(requestIntent);
    }

    public void bloodBankLogin(View view){
        Button requestBlood = findViewById(R.id.bloodBankLogin);
        Intent requestIntent = new Intent(MainActivity.this, BloodBankLogin.class);
        startActivity(requestIntent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(this);
       // mAuth = FirebaseAuth.getInstance();

    }

}
