package com.jackfruit.transle;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Splash extends AppCompatActivity {
    ImageView im;
    TextView tm,fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        im=(ImageView)findViewById(R.id.imageView);
        tm=(TextView)findViewById(R.id.Usrdtls);
        fm=(TextView)findViewById(R.id.textView2);
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                if(isNetworkAvailable()){
                Intent i = new Intent(Splash.this, Login.class);
                startActivity(i);
                finish();}
                else {
                    Intent i = new Intent(Splash.this, Nonetwork.class);
                    startActivity(i);

                }

            }
        }, 3000);
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    }

