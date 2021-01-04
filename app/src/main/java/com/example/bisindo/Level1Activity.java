package com.example.bisindo;

import androidx.annotation.NonNull;
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
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.firebase.ui.database.FirebaseRecyclerAdapter;


public class Level1Activity extends YouTubeBaseActivity {
    //API Key untuk Youtube API
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
        setContentView(R.layout.activity_level1);
        headerlayout = findViewById(R.id.headerlayout);
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
        VIDEO_ID =   "XPBTS3VX3vs";
        sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        //inisialisasi youtube API pada halaman activity
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
    //methode untuk handle button abjad
    public void abjad (View view){
        headerlayout.setVisibility(View.VISIBLE);
        linearLayout5.setVisibility(View.GONE);
        judul.setText("Abjad");
        VIDEO_ID =   "WDie3CcLfO8";
        sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        headerlayout.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.abjad));
        bottomsheet.setVisibility(View.VISIBLE);
        RecyclerView.LayoutManager mLayoutManager = new
                GridLayoutManager(getApplicationContext(), 4);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setHasFixedSize(false);
        //melakukan referensi database untuk mengambil id video pada database
        mRef = FirebaseDatabase.getInstance().getReference().child("data").child("abjad");
        //menggunakan firebase recycler adapter untuk menampilkan list judul video dari data base
        FirebaseRecyclerAdapter<videomodel, bottomsheetadapter> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<videomodel, bottomsheetadapter>(
                videomodel.class,
                R.layout.cardview4,
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
                                                      //memainkan video pada saat judul video di klik
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
    public void Angka(View view){
        headerlayout.setVisibility(View.VISIBLE);
        linearLayout5.setVisibility(View.GONE);
        judul.setText("Angka");
        headerlayout.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.angka));
        VIDEO_ID =   "YFhOFqvHhdA";
        mYouTubePlayer.cueVideo(VIDEO_ID);
        bottomsheet.setVisibility(View.VISIBLE);
        sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        RecyclerView.LayoutManager mLayoutManager = new
                GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setHasFixedSize(false);
        mRef = FirebaseDatabase.getInstance().getReference().child("data").child("angka");
        FirebaseRecyclerAdapter<videomodel, bottomsheetadapter> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<videomodel, bottomsheetadapter>(
                videomodel.class,
                R.layout.cardview4,
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
    public void Hari(View view){
        headerlayout.setVisibility(View.VISIBLE);
        linearLayout5.setVisibility(View.GONE);
        judul.setText("Hari");
        headerlayout.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.hari));
        VIDEO_ID =   "DFQDWfDq0s0";
        mYouTubePlayer.cueVideo(VIDEO_ID);
        bottomsheet.setVisibility(View.VISIBLE);
        sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        RecyclerView.LayoutManager mLayoutManager = new
                GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setHasFixedSize(false);
        mRef = FirebaseDatabase.getInstance().getReference().child("data").child("hari");
        FirebaseRecyclerAdapter<videomodel, bottomsheetadapter> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<videomodel, bottomsheetadapter>(
                videomodel.class,
                R.layout.cardview2,
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
    public void Warna(View view){
        headerlayout.setVisibility(View.VISIBLE);
        linearLayout5.setVisibility(View.GONE);
        judul.setText("Warna");
        mYouTubePlayer.cueVideo(VIDEO_ID);
        headerlayout.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.warna));
        VIDEO_ID =   "YFhOFqvHhdA";
        bottomsheet.setVisibility(View.VISIBLE);
        sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        RecyclerView.LayoutManager mLayoutManager = new
                GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setHasFixedSize(false);
        mRef = FirebaseDatabase.getInstance().getReference().child("data").child("warna");
        FirebaseRecyclerAdapter<videomodel, bottomsheetadapter> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<videomodel, bottomsheetadapter>(
                videomodel.class,
                R.layout.cardview4,
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
    public void Buah(View view){
        headerlayout.setVisibility(View.VISIBLE);
        linearLayout5.setVisibility(View.GONE);
        judul.setText("Buah");
        headerlayout.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.buah));
        VIDEO_ID =   "2jdQwE-IRWQ";
        mYouTubePlayer.cueVideo(VIDEO_ID);
        bottomsheet.setVisibility(View.VISIBLE);
        sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        RecyclerView.LayoutManager mLayoutManager = new
                GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setHasFixedSize(false);
        mRef = FirebaseDatabase.getInstance().getReference().child("data").child("buah");
        FirebaseRecyclerAdapter<videomodel, bottomsheetadapter> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<videomodel, bottomsheetadapter>(
                videomodel.class,
                R.layout.cardview2,
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
    public void perkenalan2(View view){
        headerlayout.setVisibility(View.VISIBLE);
        linearLayout5.setVisibility(View.GONE);
        judul.setText("Perkenalan");
        headerlayout.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.perkenalan));
        VIDEO_ID =   "q5coSeZ5xQY";
        mYouTubePlayer.cueVideo(VIDEO_ID);
        sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        bottomsheet.setVisibility(View.VISIBLE);
        RecyclerView.LayoutManager mLayoutManager = new
                GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setHasFixedSize(false);
        mRef = FirebaseDatabase.getInstance().getReference().child("data").child("perkenalan");
        FirebaseRecyclerAdapter<videomodel, bottomsheetadapter> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<videomodel, bottomsheetadapter>(
                videomodel.class,
                R.layout.cardview2,
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