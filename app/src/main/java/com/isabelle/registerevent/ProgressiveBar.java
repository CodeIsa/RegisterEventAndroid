package com.isabelle.registerevent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

import java.util.Timer;
import java.util.TimerTask;

public class ProgressiveBar extends AppCompatActivity {

    //@Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_progressive_bar);
//
//
//    }

    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 2000;
    MediaPlayer mp;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        mp = MediaPlayer.create(this, R.raw.s7_notification);

        super.onCreate(icicle);
        setContentView(R.layout.activity_progressive_bar);

        new Thread(new Runnable() {
            public void run() {
                try {
                    mp.start();
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent mainIntent = new Intent(ProgressiveBar.this, MainActivity.class);
                ProgressiveBar.this.startActivity(mainIntent);
                ProgressiveBar.this.finish();
                finish();
            }
        }).start();

        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(ProgressiveBar.this, MainActivity.class);
                ProgressiveBar.this.startActivity(mainIntent);
                ProgressiveBar.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }



}
