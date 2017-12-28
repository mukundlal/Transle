package com.jackfruit.transle;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Succsess extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_succsess);
        TextView textView=(TextView)findViewById(R.id.showcase);
        Bundle bundle=getIntent().getExtras();
        textView.setText(bundle.getString("topic").toString());
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                Intent i = new Intent(Succsess.this, Login.class);
                startActivity(i);
                // close this activity
                finish();
            }
        }, 3000);

    }
}
