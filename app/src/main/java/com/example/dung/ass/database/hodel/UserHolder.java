package com.example.dung.ass.database.hodel;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.dung.ass.R;

public class UserHolder extends RecyclerView.ViewHolder {
    public TextView tvId;
    public TextView tvTen;
    public TextView tvSdt;
    public ImageView imgsua;
    public ImageView imgxoa;

    public UserHolder(View itemView) {
        super( itemView );
        tvId =  itemView.findViewById(R.id.tvId);
        tvTen = itemView.findViewById(R.id.tvTen);
        tvSdt = itemView.findViewById(R.id.tvSdt);
        imgxoa = itemView.findViewById(R.id.imgxoa);
    }
}
