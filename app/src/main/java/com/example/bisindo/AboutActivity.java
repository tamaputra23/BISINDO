package com.example.bisindo;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;

public class AboutActivity extends AppCompatActivity {
    MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        mp = MediaPlayer.create(this, R.raw.soundabou);
        mp.start();
    }
    @Override
    protected void onPause() {
        super.onPause();
        mp.stop();
    }
    @Override
    protected void onStop() {
        super.onStop();
        mp.stop();
    }
}