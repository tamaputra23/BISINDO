package com.example.bisindo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class BisindoActivity extends AppCompatActivity {
    LinearLayout perkenalan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bisindo);
    }
    //button untuk masuk menu perkenalan
    public void perkenalan(View view){
        Intent perkenalan = new Intent(this, PerkenalanActivity.class);
        startActivity(perkenalan);
    }
    //button untuk masuk menu level1
    public void level1(View view){
        Intent level1 = new Intent(this, Level1Activity.class);
        startActivity(level1);
    }
    //button untuk masuk menu level2
    public void level2(View view){
        Intent level2 = new Intent(this, Level2Activity.class);
        startActivity(level2);
    }
    //button untuk masuk menu level3
    public void level3(View view){
        Intent level3 = new Intent(this, Level3Activity.class);
        startActivity(level3);
    }
}