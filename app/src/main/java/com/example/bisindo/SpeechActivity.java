package com.example.bisindo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class SpeechActivity extends AppCompatActivity implements
        RecognitionListener {
    //request code untuk basis saat memeriksa permission akses microphone
    public static final Integer RecordAudioRequestCode = 1;
    String speechString = "";
    private String LOG_TAG = "SpeechActivity";
    //deklarasi penggunaan fitur speech recognizer
    private SpeechRecognizer speech = null;
    TextView editText;
    Intent mSpeechRecognizerIntent;
    Intent recognizerIntent;
    private AudioManager manager;

    //methode untuk mengulang sesi penerimaan suara yang akan diterjemahkan kedalam teks
    private void resetSpeechRecognizer() {

        if(speech != null)
            speech.destroy();
        speech = SpeechRecognizer.createSpeechRecognizer(this);
        Log.i(LOG_TAG, "isRecognitionAvailable: " + SpeechRecognizer.isRecognitionAvailable(this));
        if(SpeechRecognizer.isRecognitionAvailable(this))
            speech.setRecognitionListener(this);
        else
            finish();
    }
    //inisialisasi speech recognizer
    private void setRecogniserIntent() {

        recognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        //melakukan pemilihan bahasa yang akan diolah pada saat penerimaan suara dan diterjemahkan menjadi tulisan
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,
                "in");
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 3);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech);
        //melakukan pengecekan akses microphone pada aplikasi
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED){
            checkPermission();
        }
        editText= findViewById(R.id.editText);
        final ProgressBar progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);
        speech = SpeechRecognizer.createSpeechRecognizer(this);

        setRecogniserIntent();
        manager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        //button untuk menghentikan pengambilan suara pada speechrecognizer
        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speech.stopListening();
                speech.destroy();
                progressBar.setVisibility(View.GONE);
                findViewById(R.id.button1).setVisibility(View.GONE);
                findViewById(R.id.button).setVisibility(View.VISIBLE);
            }
        });
        //button untuk memulai pengambilan suara pada speechrecognizer
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetSpeechRecognizer();
                speech.startListening(recognizerIntent);
                progressBar.setVisibility(View.VISIBLE);
                editText.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
                findViewById(R.id.button1).setVisibility(View.VISIBLE);
                findViewById(R.id.button).setVisibility(View.GONE);
            }
        });
        //button untuk menghapus atau mereset kembali teks hasil dari terjemahan suara
        findViewById(R.id.tv_clear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("Klik untuk mulai berbicara");
                speechString = "";
            }
        });
    }
    //melakukan pengecekan akses sesuai dengan versi android dari hp pengguna
    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.RECORD_AUDIO},RecordAudioRequestCode);
            }
        }
    }
    //methode yang menghandle hasil dari pengecekan akses
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == RecordAudioRequestCode && grantResults.length > 0 ){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED)
                Toast.makeText(this,"Permission Granted",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onResume() {
        Log.i(LOG_TAG, "resume");
        super.onResume();
    }
    //melakukan stop speechrecognizer pada saat keluar dari halaman
    @Override
    protected void onPause() {
        Log.i(LOG_TAG, "pause");
        super.onPause();
        speech.stopListening();
    }
    //melakukan stop speechrecognizer pada saat aplikasi berhenti
    @Override
    protected void onStop() {
        Log.i(LOG_TAG, "stop");
        super.onStop();
        if (speech != null) {
            speech.destroy();
        }
    }
    @Override
    public void onReadyForSpeech(Bundle bundle) {

    }

    @Override
    public void onBeginningOfSpeech() {
        Log.i(LOG_TAG, "onBeginningOfSpeech");
    }
    @Override
    public void onBufferReceived(byte[] buffer) {
        Log.i(LOG_TAG, "onBufferReceived: " + buffer);
    }
    //menghentikan speechrecognizer saat tidak terdeteksi lagi suara yang dapat diterjemahkan
    @Override
    public void onEndOfSpeech() {
        Log.i(LOG_TAG, "onEndOfSpeech");
        speech.stopListening();

    }
    //mengeluarkan hasil dari penerjemahan speechrecognizer yang berupa text
    @Override
    public void onResults(Bundle bundle) {
        Log.i(LOG_TAG, "onResults");
        //getting all the matches
        ArrayList<String> matches = bundle
                .getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        speechString = speechString + " " + matches.get(0);
        //displaying the first match
        //displaying the first match
        editText.setText(speechString );

        speech.startListening(recognizerIntent);
    }
    //methode untuk menghandle saat terjadi error
    @Override
    public void onError(int i) {
        String errorMessage = getErrorText(i);
        Log.i(LOG_TAG, "FAILED " + errorMessage);
        // rest voice recogniser
        resetSpeechRecognizer();
        speech.startListening(recognizerIntent);
    }
    @Override
    public void onRmsChanged(float v) {

    }


    @Override
    public void onPartialResults(Bundle bundle) {

    }

    @Override
    public void onEvent(int i, Bundle bundle) {

    }
    public String getErrorText(int errorCode) {
        String message;
        switch (errorCode) {
            case SpeechRecognizer.ERROR_AUDIO:
                message = "Audio recording error";
                break;
            case SpeechRecognizer.ERROR_CLIENT:
                message = "Client side error";
                break;
            case SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS:
                message = "Insufficient permissions";
                break;
            case SpeechRecognizer.ERROR_NETWORK:
                message = "Network error";
                break;
            case SpeechRecognizer.ERROR_NETWORK_TIMEOUT:
                message = "Network timeout";
                break;
            case SpeechRecognizer.ERROR_NO_MATCH:
                message = "No match";
                break;
            case SpeechRecognizer.ERROR_RECOGNIZER_BUSY:
                message = "RecognitionService busy";
                break;
            case SpeechRecognizer.ERROR_SERVER:
                message = "error from server";
                break;
            case SpeechRecognizer.ERROR_SPEECH_TIMEOUT:
                message = "No speech input";
                break;
            default:
                message = "Didn't understand, please try again.";
                break;
        }
        return message;
    }
}