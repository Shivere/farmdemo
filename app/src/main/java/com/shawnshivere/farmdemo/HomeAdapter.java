package com.shawnshivere.farmdemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder>{
    private Context mContext;
    private List<ModelHome> homeList;

    public HomeAdapter(Context mContext, List<ModelHome> homeList) {
        this.mContext = mContext;
        this.homeList = homeList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, desc;
        public ImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            desc = (TextView) view.findViewById(R.id.desc);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        ModelHome modelHome = homeList.get(position);
        holder.title.setText(modelHome.getName());
        holder.desc.setText(modelHome.getDesc());
        // loading album cover using Glide library
        Glide.with(mContext).load(modelHome.getThumbnail()).into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return homeList.size();
    }

}
