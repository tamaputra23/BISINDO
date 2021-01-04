package com.example.bisindo.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.bisindo.R;
//adapter digunakan untuk menampilkan judul video pada bottomsheet
public class bottomsheetadapter extends RecyclerView.ViewHolder  {
    View mView;
    TextView tvhuruf, tv_video;
    ImageView iv_cartholder;
    String gambar;
    public bottomsheetadapter(View itemView) {
        super(itemView);
        mView = itemView;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClickListener.onItemClick(view, getAdapterPosition());
            }
        });
        //item long click
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mClickListener.onItemLongClick(view, getAdapterPosition());
                return true;
            }
        });
    }
    public void setDetails(Context ctx, String judul, String video){
        tvhuruf = mView.findViewById(R.id.tv_judulcard);
        tv_video = mView.findViewById(R.id.tv_Video);
        tvhuruf.setText(judul);
        tv_video.setText(video);
        //Picasso.with(ctx).load(image).into(mImageIv);
    }
    //methode yang melakukan handle pada saat button judul di klik oleh pengguna
    private bottomsheetadapter.ClickListener mClickListener;
    //interface to send callbacks
    public interface ClickListener{
        void onItemClick(View view, int position);
        void onItemLongClick(View  view, int position);
    }
    public void setOnClickListener(bottomsheetadapter.ClickListener clickListener){
        mClickListener = clickListener;
    }
}
