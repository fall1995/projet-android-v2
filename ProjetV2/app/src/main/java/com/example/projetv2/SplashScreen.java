package com.example.projetv2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;


public class SplashScreen extends AppCompatActivity {

    private Thread mSplashThread;
    public static int SPLASH_TIME = 4000;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // The thread to wait for splash screen events
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                // Run next activity
                Intent intent = new Intent(SplashScreen.this,MainActivity.class);

                startActivity(intent);
                finish();
            }
        } ,SPLASH_TIME);

    }
}

