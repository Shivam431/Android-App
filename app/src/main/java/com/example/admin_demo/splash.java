package com.example.admin_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Runnable r = new Runnable(){
            @Override
            public void run()
            {
                Intent home=new Intent(splash.this,MainActivity.class);
                startActivity(home);
                finish();
            }

        };
        new Handler().postDelayed(r,3000);

    }
}
