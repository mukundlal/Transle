package com.jackfruit.transle;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class Forget extends AppCompatActivity {
EditText phn,otp;
Button getotp,checkotp;
CardView cardView;
FirebaseAuth mAuth;
PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_forget);
        phn=(EditText)findViewById(R.id.phnfrgt);
        otp=(EditText)findViewById(R.id.otp);
        getotp=(Button)findViewById(R.id.getotp);
        checkotp=(Button)findViewById(R.id.confrmotp);
        cardView=(CardView)findViewById(R.id.otpcard);
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

            }

            @Override
            public void onVerificationFailed(FirebaseException e) {

            }

            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
            }
        };

        getotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(phn.getText().length()<10||phn.getText().length()>10) {
                    Toast.makeText(getApplicationContext(), "Invalid Mobile Number", Toast.LENGTH_SHORT);
                    phn.setError("Enter A Valid Number");
                }else {
                    PhoneAuthProvider.getInstance().verifyPhoneNumber(phn.getText().toString(),60,TimeUnit.SECONDS,this,mCallbacks);
                    cardView.setVisibility(View.VISIBLE);
                }
            }
        });
        checkotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Forget.this,Succsess.class).putExtra("topic","Password Changed :)"));

            }
        });


    }

    }
