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

public class Level3Activity extends YouTubeBaseActivity {
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
        setContentView(R.layout.activity_level3);
        headerlayout = findViewById(R.id.headerlayout3);
        judul = findViewById(R.id.tv_judul);
        linearLayout5 = findViewById(R.id.linearLayout5);
        youTubePlayerView= findViewById(R.id.player);
        relativeLayout = findViewById(R.id.relativeLayout);
        bottomsheet = findViewById(R.id.layout_bottomsheet);
        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference();
        recyclerView = findViewById(R.id.recyclerView);
        bottomSheetLayout = findViewById(R.id.bottom_sheet_layout);
        gestureLayout = findViewById(R.id.gesture_layout);
        sheetBehavior = BottomSheetBehavior.from(bottomsheet);
        VIDEO_ID =   "6jxekDJGLUc";
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
    public void Satwa (View view){
        headerlayout.setVisibility(View.VISIBLE);
        linearLayout5.setVisibility(View.GONE);
        judul.setText("Satwa");
        VIDEO_ID =   "6jxekDJGLUc";
        mYouTubePlayer.cueVideo(VIDEO_ID);
        sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        headerlayout.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.satwa));
        bottomsheet.setVisibility(View.VISIBLE);
        RecyclerView.LayoutManager mLayoutManager = new
                GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setHasFixedSize(false);
        mRef = FirebaseDatabase.getInstance().getReference().child("data").child("satwa");
        FirebaseRecyclerAdapter<videomodel, bottomsheetadapter> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<videomodel, bottomsheetadapter>(
                videomodel.class,
                R.layout.cardview2_3,
                bottomsheetadapter.class,
                mRef) {
            @Override
            protected void populateViewHolder(bottomsheetadapter viewHolder, final videomodel model, int position) {
                viewHolder.setDetails(getApplicationContext(), model.getJudul(), model.getVideo());
            }
            @Override
            public bottomsheetadapter onCreateViewHolder(ViewGroup parent, int viewType) {
                final bottomsheetadapter viewHolder = super.onCreateViewHolder(parent, viewType);
                viewHolder.setOnClickListener(new bottomsheetadapter.ClickListener(){

                                                  @Override
                                                  public void onItemClick(View view, int position) {
                                                      final DatabaseReference postRef = getRef(position);
                                                      TextView mVideoid   = view.findViewById(R.id.tv_Video);
                                                      TextView mJudul = view.findViewById(R.id.tv_judulcard);
                                                      String sJudul = mJudul.getText().toString();
                                                      judul.setText(sJudul);
                                                      VIDEO_ID = mVideoid.getText().toString();
                                                      mYouTubePlayer.cueVideo(VIDEO_ID);


                                                  }

                                                  @Override
                                                  public void onItemLongClick(View view, int position) {

                                                  }
                                              }
                );
                return viewHolder;
            }

        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
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
    public void Pekerjaan(View view){
        headerlayout.setVisibility(View.VISIBLE);
        linearLayout5.setVisibility(View.GONE);
        judul.setText("Pekerjaan");
        headerlayout.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.pekerjaan));
        VIDEO_ID =   "w40RE_dFisI";
        mYouTubePlayer.cueVideo(VIDEO_ID);
        bottomsheet.setVisibility(View.VISIBLE);
        sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        RecyclerView.LayoutManager mLayoutManager = new
                GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setHasFixedSize(false);
        mRef = FirebaseDatabase.getInstance().getReference().child("data").child("pekerjaan");
        FirebaseRecyclerAdapter<videomodel, bottomsheetadapter> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<videomodel, bottomsheetadapter>(
                videomodel.class,
                R.layout.cardview2_3,
                bottomsheetadapter.class,
                mRef) {
            @Override
            protected void populateViewHolder(bottomsheetadapter viewHolder, final videomodel model, int position) {
                viewHolder.setDetails(getApplicationContext(), model.getJudul(), model.getVideo());
            }
            @Override
            public bottomsheetadapter onCreateViewHolder(ViewGroup parent, int viewType) {
                final bottomsheetadapter viewHolder = super.onCreateViewHolder(parent, viewType);
                viewHolder.setOnClickListener(new bottomsheetadapter.ClickListener(){

                                                  @Override
                                                  public void onItemClick(View view, int position) {
                                                      final DatabaseReference postRef = getRef(position);
                                                      TextView mVideoid   = view.findViewById(R.id.tv_Video);
                                                      TextView mJudul = view.findViewById(R.id.tv_judulcard);
                                                      String sJudul = mJudul.getText().toString();
                                                      judul.setText(sJudul);
                                                      VIDEO_ID = mVideoid.getText().toString();
                                                      mYouTubePlayer.cueVideo(VIDEO_ID);


                                                  }

                                                  @Override
                                                  public void onItemLongClick(View view, int position) {

                                                  }
                                              }
                );
                return viewHolder;
            }

        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
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
    public void Pakaian(View view){
        headerlayout.setVisibility(View.VISIBLE);
        linearLayout5.setVisibility(View.GONE);
        judul.setText("Pakaian");
        headerlayout.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.pakaian));
        VIDEO_ID =   "KnT4hbtOO5Y";
        mYouTubePlayer.cueVideo(VIDEO_ID);
        bottomsheet.setVisibility(View.VISIBLE);
        sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        RecyclerView.LayoutManager mLayoutManager = new
                GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setHasFixedSize(false);
        mRef = FirebaseDatabase.getInstance().getReference().child("data").child("pakaian");
        FirebaseRecyclerAdapter<videomodel, bottomsheetadapter> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<videomodel, bottomsheetadapter>(
                videomodel.class,
                R.layout.cardview2_3,
                bottomsheetadapter.class,
                mRef) {
            @Override
            protected void populateViewHolder(bottomsheetadapter viewHolder, final videomodel model, int position) {
                viewHolder.setDetails(getApplicationContext(), model.getJudul(), model.getVideo());
            }
            @Override
            public bottomsheetadapter onCreateViewHolder(ViewGroup parent, int viewType) {
                final bottomsheetadapter viewHolder = super.onCreateViewHolder(parent, viewType);
                viewHolder.setOnClickListener(new bottomsheetadapter.ClickListener(){

                                                  @Override
                                                  public void onItemClick(View view, int position) {
                                                      final DatabaseReference postRef = getRef(position);
                                                      TextView mVideoid   = view.findViewById(R.id.tv_Video);
                                                      TextView mJudul = view.findViewById(R.id.tv_judulcard);
                                                      String sJudul = mJudul.getText().toString();
                                                      judul.setText(sJudul);
                                                      VIDEO_ID = mVideoid.getText().toString();
                                                      mYouTubePlayer.cueVideo(VIDEO_ID);


                                                  }

                                                  @Override
                                                  public void onItemLongClick(View view, int position) {

                                                  }
                                              }
                );
                return viewHolder;
            }

        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
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
    public void Perasaan(View view){
        headerlayout.setVisibility(View.VISIBLE);
        linearLayout5.setVisibility(View.GONE);
        judul.setText("Perasaan Emosi");
        headerlayout.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.perasaan));
        VIDEO_ID =   "ekFvIBBxHKQ";
        mYouTubePlayer.cueVideo(VIDEO_ID);
        bottomsheet.setVisibility(View.VISIBLE);
        sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        RecyclerView.LayoutManager mLayoutManager = new
                GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setHasFixedSize(false);
        mRef = FirebaseDatabase.getInstance().getReference().child("data").child("perasaan");
        FirebaseRecyclerAdapter<videomodel, bottomsheetadapter> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<videomodel, bottomsheetadapter>(
                videomodel.class,
                R.layout.cardview2_3,
                bottomsheetadapter.class,
                mRef) {
            @Override
            protected void populateViewHolder(bottomsheetadapter viewHolder, final videomodel model, int position) {
                viewHolder.setDetails(getApplicationContext(), model.getJudul(), model.getVideo());
            }
            @Override
            public bottomsheetadapter onCreateViewHolder(ViewGroup parent, int viewType) {
                final bottomsheetadapter viewHolder = super.onCreateViewHolder(parent, viewType);
                viewHolder.setOnClickListener(new bottomsheetadapter.ClickListener(){

                                                  @Override
                                                  public void onItemClick(View view, int position) {
                                                      final DatabaseReference postRef = getRef(position);
                                                      TextView mVideoid   = view.findViewById(R.id.tv_Video);
                                                      TextView mJudul = view.findViewById(R.id.tv_judulcard);
                                                      String sJudul = mJudul.getText().toString();
                                                      judul.setText(sJudul);
                                                      VIDEO_ID = mVideoid.getText().toString();
                                                      mYouTubePlayer.cueVideo(VIDEO_ID);


                                                  }

                                                  @Override
                                                  public void onItemLongClick(View view, int position) {

                                                  }
                                              }
                );
                return viewHolder;
            }

        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
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
    public void Negara(View view){
        headerlayout.setVisibility(View.VISIBLE);
        linearLayout5.setVisibility(View.GONE);
        judul.setText("Nama Negara");
        headerlayout.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.negara));
        VIDEO_ID =   "_UTUvU3C1nQ";
        mYouTubePlayer.cueVideo(VIDEO_ID);
        bottomsheet.setVisibility(View.VISIBLE);
        sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        RecyclerView.LayoutManager mLayoutManager = new
                GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setHasFixedSize(false);
        mRef = FirebaseDatabase.getInstance().getReference().child("data").child("negara");
        FirebaseRecyclerAdapter<videomodel, bottomsheetadapter> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<videomodel, bottomsheetadapter>(
                videomodel.class,
                R.layout.cardview2_3,
                bottomsheetadapter.class,
                mRef) {
            @Override
            protected void populateViewHolder(bottomsheetadapter viewHolder, final videomodel model, int position) {
                viewHolder.setDetails(getApplicationContext(), model.getJudul(), model.getVideo());
            }
            @Override
            public bottomsheetadapter onCreateViewHolder(ViewGroup parent, int viewType) {
                final bottomsheetadapter viewHolder = super.onCreateViewHolder(parent, viewType);
                viewHolder.setOnClickListener(new bottomsheetadapter.ClickListener(){

                                                  @Override
                                                  public void onItemClick(View view, int position) {
                                                      final DatabaseReference postRef = getRef(position);
                                                      TextView mVideoid   = view.findViewById(R.id.tv_Video);
                                                      TextView mJudul = view.findViewById(R.id.tv_judulcard);
                                                      String sJudul = mJudul.getText().toString();
                                                      judul.setText(sJudul);
                                                      VIDEO_ID = mVideoid.getText().toString();
                                                      mYouTubePlayer.cueVideo(VIDEO_ID);


                                                  }

                                                  @Override
                                                  public void onItemLongClick(View view, int position) {

                                                  }
                                              }
                );
                return viewHolder;
            }

        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
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
    public void Kota(View view){
        headerlayout.setVisibility(View.VISIBLE);
        linearLayout5.setVisibility(View.GONE);
        judul.setText("Nama Kota");
        headerlayout.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.kota));
        VIDEO_ID =   "IzKno-SB3fs";
        mYouTubePlayer.cueVideo(VIDEO_ID);
        bottomsheet.setVisibility(View.VISIBLE);
        sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        RecyclerView.LayoutManager mLayoutManager = new
                GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setHasFixedSize(false);
        mRef = FirebaseDatabase.getInstance().getReference().child("data").child("kota");
        FirebaseRecyclerAdapter<videomodel, bottomsheetadapter> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<videomodel, bottomsheetadapter>(
                videomodel.class,
                R.layout.cardview2_3,
                bottomsheetadapter.class,
                mRef) {
            @Override
            protected void populateViewHolder(bottomsheetadapter viewHolder, final videomodel model, int position) {
                viewHolder.setDetails(getApplicationContext(), model.getJudul(), model.getVideo());
            }
            @Override
            public bottomsheetadapter onCreateViewHolder(ViewGroup parent, int viewType) {
                final bottomsheetadapter viewHolder = super.onCreateViewHolder(parent, viewType);
                viewHolder.setOnClickListener(new bottomsheetadapter.ClickListener(){

                                                  @Override
                                                  public void onItemClick(View view, int position) {
                                                      final DatabaseReference postRef = getRef(position);
                                                      TextView mVideoid   = view.findViewById(R.id.tv_Video);
                                                      TextView mJudul = view.findViewById(R.id.tv_judulcard);
                                                      String sJudul = mJudul.getText().toString();
                                                      judul.setText(sJudul);
                                                      VIDEO_ID = mVideoid.getText().toString();
                                                      mYouTubePlayer.cueVideo(VIDEO_ID);


                                                  }

                                                  @Override
                                                  public void onItemLongClick(View view, int position) {

                                                  }
                                              }
                );
                return viewHolder;
            }

        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
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