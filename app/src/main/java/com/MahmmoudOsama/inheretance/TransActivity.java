package com.MahmmoudOsama.inheretance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
public class TransActivity extends AppCompatActivity {
    private Thread waitThread;
    Intent in;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trans);
        Intent in = new Intent(getBaseContext(), ResultsActivity.class);
        Intent i=getIntent();
        in.putExtra("reclc",i.getBooleanExtra("reclc",false));
        in.putExtra("inc",i.getBooleanExtra("inc",false));
        waitThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return;
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        startActivity(in);
                        finish(); // finish the TransActivity after starting ResultsActivity
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
