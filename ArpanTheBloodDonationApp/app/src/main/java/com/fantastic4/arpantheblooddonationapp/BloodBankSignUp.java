package com.fantastic4.arpantheblooddonationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BloodBankSignUp extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner mTypeSpinner;
    Spinner mCitySpinner;
    private EditText mEmailEditText;
    private EditText mPasswordEditText;
    private EditText mNameEditText;
    private EditText mPhoneEditText;
    private EditText mAddressEditText;
    private Button mRegisterBloodBank;
    private ProgressDialog mRegProgressDialog;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    String name;
    String email;
    String contact;
    String password;
    String city;
    String address;
    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_bank_sign_up);

        // Spinner element
        mTypeSpinner = findViewById(R.id.typeSpinner);

        // Spinner click listener
        mTypeSpinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        final List<String> typeList = new ArrayList<String>();
        typeList.add("Blood Bank");
        typeList.add("Hospital");

        // Creating adapter for spinner
        ArrayAdapter<String> typeDataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, typeList);

        // Drop down layout style - list view with radio button
        typeDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mTypeSpinner.setAdapter(typeDataAdapter);

        mCitySpinner = findViewById(R.id.citySpinner);
        mCitySpinner.setOnItemSelectedListener(this);
        String[] citis = new String[]{"Mumbai","Delhi", "Hyderabad", "Kolkata","Chennai", "Jaipur", "Bangalore", "Pune"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, citis);
        mCitySpinner.setAdapter(adapter);

        mRegProgressDialog = new ProgressDialog(this);


        FirebaseApp.initializeApp(this);
        //initializing firebase auth object
        mAuth = FirebaseAuth.getInstance();

        mNameEditText = findViewById(R.id.nameEditText);
        mEmailEditText = findViewById(R.id.emailEditText);
        mCitySpinner = findViewById(R.id.citySpinner);
        mTypeSpinner = findViewById(R.id.typeSpinner);
        mPhoneEditText = findViewById(R.id.contact);
        mAddressEditText = (EditText)findViewById(R.id.addressEditText);
        mPasswordEditText = findViewById(R.id.passwordEditText);

        mRegisterBloodBank = findViewById(R.id.registerBloodBank);

        mRegisterBloodBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name=mNameEditText.getText().toString();
                email=mEmailEditText.getText().toString();
                password=mPasswordEditText.getText().toString();
                contact=mPhoneEditText.getText().toString();
                address = mAddressEditText.getText().toString();
                city = mCitySpinner.getSelectedItem().toString();
                type = mTypeSpinner.getSelectedItem().toString();

                if(!TextUtils.isEmpty(name)||!TextUtils.isEmpty(email)||!TextUtils.isEmpty(password)||
                        !TextUtils.isEmpty(type)||!TextUtils.isEmpty(contact)||!TextUtils.isEmpty(city)
                        || !TextUtils.isEmpty(address)){
                    mRegProgressDialog.setTitle("Registering User");
                    mRegProgressDialog.setMessage("Please wait");
                    mRegProgressDialog.setCanceledOnTouchOutside(false);
                    mRegProgressDialog.show();

                    register_bloodBank(name,email,password);
                }

            }
        });


    }

    private void register_bloodBank(final String name, final String email, final String password) {
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {

                    FirebaseUser current_user= FirebaseAuth.getInstance().getCurrentUser();
                    String uid=current_user.getUid();
                    FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();

                    //user.sendEmailVerification();
                    //mEmailverification.setTitle("Check your email and verify it");
                    //mEmailverification.setMessage("Verifying...");
                    // mEmailverification.show();
                    Boolean emailVerfied=user.isEmailVerified();
                    Log.e("Success", String.valueOf(emailVerfied));




                    String device_token = FirebaseInstanceId.getInstance().getToken();

                    HashMap<String, String> userMap = new HashMap<>();
                    userMap.put("name", name);
                    userMap.put("email", email);
                    userMap.put("password",password);
                    userMap.put("type",type);
                    userMap.put("phone",contact);
                    userMap.put("place",city);
                    userMap.put("device_token", device_token);
                    Toast.makeText(getApplicationContext(),"Registered Successfully..!",Toast.LENGTH_LONG).show();
                    mRegProgressDialog.dismiss();
                    // mDatabase.child(bloodGroupString).child(city).child(uid).setValue(userMap).
                    BloodBank bloodBank = new BloodBank(name,contact,type,city,address, password);
                    mDatabase = FirebaseDatabase.getInstance().getReference().child("Blood Banks and Hospitals");
//                    mDatabase = database.getReference("donors");
                    mDatabase.child(city).child(uid).setValue(bloodBank).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {


                            //  Toast.makeText(getApplicationContext(),"Incorrect Details ",Toast.LENGTH_LONG).show();



                            //  mRegProgress.dismiss();
                            Intent mainIntent = new Intent(BloodBankSignUp.this, BloodBankLogin.class);
                            startActivity(mainIntent);
                            finish();

                        }

                    });

                }
                else{
                    mRegProgressDialog.hide();
                    Toast.makeText(BloodBankSignUp.this,"Authentication failed",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
