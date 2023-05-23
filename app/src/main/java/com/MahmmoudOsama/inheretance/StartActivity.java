package com.MahmmoudOsama.inheretance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class StartActivity extends AppCompatActivity {
private Thread waitThread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
     waitThread=new Thread(new Runnable() {
         @Override
         public void run() {
             try {
                 Thread.sleep(1500);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
             runOnUiThread(new Runnable() {
                 @Override
                 public void run() {
                     Intent in =new Intent(getBaseContext(),MainActivity.class);
                     startActivity(in);
                 }
             });
         }
     });
waitThread.start();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (waitThread != null) {
            waitThread.interrupt(); // interrupt the thread when the activity is destroyed
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
        if (waitThread != null) {
            waitThread.interrupt(); // interrupt the thread when the activity is paused
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (waitThread != null) {
            waitThread.interrupt(); // interrupt the thread when the back button is pressed
        }
    }
}