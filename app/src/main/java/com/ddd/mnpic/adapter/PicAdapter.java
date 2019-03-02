package com.ddd.mnpic.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.ddd.mnpic.R;
import com.ddd.mnpic.util.ScreenUtil;

import java.util.ArrayList;
import java.util.List;

public class PicAdapter extends RecyclerView.Adapter {

    List<String> pics = new ArrayList<>();
    Context mContext;

    public PicAdapter(List<String> pics, Context context) {
        this.pics = pics;
        mContext = context;
    }

    public void setPics(List<String> pics) {
        this.pics = pics;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.pic_item, null);
        return new PicHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        PicHolder holder = (PicHolder) viewHolder;
        holder.set(pics.get(i));
    }

    @Override
    public int getItemCount() {
        return pics.size();
    }


    public class PicHolder extends RecyclerView.ViewHolder{
        ImageView image;
        public PicHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.picture);
        }

        public void set(String url){
            LinearLayout.LayoutParams params= (LinearLayout.LayoutParams) image.getLayoutParams();
            //>>>>>>>>>>>>>>>>设置宽，高很重要<<<<<<<<<<<<<<<<
            params.height=(int)(300+Math.random()*400);
            params.weight=ScreenUtil.getScreenWidth(mContext)/2;
            image.setLayoutParams(params);

            Glide.with(mContext)
                    .load(url)//url

//                    .override(200,200)
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            Log.i("gejun","width = "+resource.getIntrinsicWidth()+", height = "+resource.getIntrinsicHeight());
                            return false;
                        }
                    })
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .into(image);
        }
    }
}
