package com.example.bisindo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

public class MainActivity extends AppCompatActivity {
    //deklarasi untuk carousel pada tampilan menu utama
    CarouselView carouselView;
    int[] sampleImages = {R.drawable.event_01, R.drawable.class_01};

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
        carouselView = findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener(imageListener);
        //methode untuk handle saat pengguna klik pada gambar dalam carousel untuk berpindah halaman
        carouselView.setImageClickListener(new ImageClickListener() {
            @Override
            public void onClick(final int position) {
                    if(position == 0){
                        Intent intent = new Intent (getApplicationContext(), Level3Activity.class);
                        startActivity(intent);
                    }
                else if(position == 1){
                    Intent intent = new Intent (getApplicationContext(), PerkenalanActivity.class);
                    startActivity(intent);
                }
                if(position == 2){
                    Intent intent = new Intent (getApplicationContext(), Level1Activity.class);
                    startActivity(intent);
                }
                }
            });
        }
    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };
    //button untuk masuk menu bisindo
    public void Bisindo (View view){
        Intent bisindo = new Intent(this, BisindoActivity.class);
        startActivity(bisindo);
    }
    //button untuk masuk menu pengetahuan
    public void Pengetahuan (View view){
        Intent pengetahuan = new Intent(this, PengetahuanActivity.class);
        startActivity(pengetahuan);
    }
    //button untuk masuk menu speechtotext
    public void Speech (View view){
        Intent speech = new Intent(this, SpeechActivity.class);
        startActivity(speech);
    }
    //button untuk masuk menu about
    public void About (View view){
        Intent about = new Intent(this, AboutActivity.class);
        startActivity(about);
    }
}
