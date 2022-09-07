package com.example.contact;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.VideoView;

public class SplashScreen extends AppCompatActivity {

    VideoView v;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        v = findViewById(R.id.video);
        handler = new Handler();
        v.setVideoPath("android.resource://" +getPackageName() + "/" + R.raw.contact_splash);
        v.start();
        handler.postDelayed(() ->
                {
                    Intent intent = new Intent(SplashScreen.this,HomeActivity.class);
                    startActivity(intent);
                    finish();
                }, 3360);
    }
}