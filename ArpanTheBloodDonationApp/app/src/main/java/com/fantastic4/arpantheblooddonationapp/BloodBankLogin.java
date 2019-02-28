package com.fantastic4.arpantheblooddonationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

public class BloodBankLogin extends AppCompatActivity {

    private Toolbar mToolbar;

    private EditText mLoginEmail;
    private EditText mLoginPassword;
    private Button mLogin_btn;
    private SignInButton mGoogleBtn;

    private ProgressDialog mLoginProgress;

    private FirebaseAuth mAuth;
    private FirebaseAuth .AuthStateListener mAuthStateListener;
    private DatabaseReference mUserDatabase;

    public void signUp(View view){
        Intent intent = new Intent(BloodBankLogin.this, BloodBankSignUp.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_bank_login);

        mAuth = FirebaseAuth.getInstance();
        mAuthStateListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

            }
        };
        // mGoogleBtn=(SignInButton)findViewById(R.id.googlebtn);

    /*    mToolbar=(Toolbar)findViewById(R.id.login_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("LOGIN");*/
        mUserDatabase= FirebaseDatabase.getInstance().getReference().child("Blood Banks and Hospitals");
        mLoginProgress=new ProgressDialog(this);
        mLoginEmail=(EditText)findViewById(R.id.emailEditText);
        mLoginPassword=(EditText)findViewById(R.id.passwordEditText);
        mLogin_btn=(Button)findViewById(R.id.logIn);

        mLogin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=mLoginEmail.getText().toString();
                String password=mLoginPassword.getText().toString();
                if(!TextUtils.isEmpty(email)||!TextUtils.isEmpty(password))
                {
                    mLoginProgress.setTitle("Logging In");
                    mLoginProgress.setMessage("Please Wait...");
                    mLoginProgress.setCanceledOnTouchOutside(false);
                    mLoginProgress.show();
                    logIn(email,password);
                }
            }
        });


    }
    private void logIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    mLoginProgress.dismiss();
                    String current_user_id=mAuth.getCurrentUser().getUid();
                    String deviceToken= FirebaseInstanceId.getInstance().getToken();
                            Intent mainIntent=new Intent(BloodBankLogin.this,MainActivity.class);
                            mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(mainIntent);
                            finish();

                }
                else {
                    mLoginProgress.hide();
                    Toast.makeText(BloodBankLogin.this, "Authentication failed", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
