package com.example.bisindo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bisindo.adapter.bottomsheetadapter;
import com.example.bisindo.model.videomodel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PerkenalanActivity extends YouTubeBaseActivity {
    public static final String API_KEY = "AIzaSyCzmM_Fh3CxZMdXyFkauBrPz0gdjYGsPuA";
    LinearLayout headerlayout, linearLayout5, relativeLayout;
    TextView judul;
    FirebaseDatabase mDatabase;
    DatabaseReference mRef;
    FrameLayout content;
    YouTubePlayerView youTubePlayerView;
    View bottomsheet;
    RecyclerView recyclerView;
    String VIDEO_ID;
    YouTubePlayer.PlayerStyle style;
    YouTubePlayer mYouTubePlayer;
    LinearLayout bottomSheetLayout;
    LinearLayout gestureLayout;
    BottomSheetBehavior sheetBehavior;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perkenalan);
        judul = findViewById(R.id.tv_judul);
        linearLayout5 = findViewById(R.id.linearLayout5);
        youTubePlayerView= findViewById(R.id.player);
        headerlayout = findViewById(R.id.headerlayout4);
        relativeLayout = findViewById(R.id.relativeLayout);
        bottomsheet = findViewById(R.id.layout_bottomsheet);
        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference();
        recyclerView = findViewById(R.id.recyclerView);
        judul.setText("Aturan Bahasa Isyarat");
        VIDEO_ID =   "XPBTS3VX3vs";
        youTubePlayerView.initialize(API_KEY,
                new YouTubePlayer.OnInitializedListener() {
                    @Override
                    public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                        YouTubePlayer youTubePlayer, boolean b) {
                        style = YouTubePlayer.PlayerStyle.MINIMAL;
                        youTubePlayer.setPlayerStyle(style);
                        mYouTubePlayer = youTubePlayer;
                        // do any work here to cue video, play video, etc.
                        mYouTubePlayer.cueVideo(VIDEO_ID);
                    }
                    @Override
                    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                        YouTubeInitializationResult youTubeInitializationResult) {

                    }
                });
    }
}
