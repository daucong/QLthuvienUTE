package com.example.librarydemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;

import java.util.List;

public class PhotoAdapter extends PagerAdapter {

    private final Context mContext;
    private final List<Photo> mListPhotos;

    public PhotoAdapter(Context mContext, List<Photo> mListPhotos) {
        this.mContext = mContext;
        this.mListPhotos = mListPhotos;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_photo, container, false);
        ImageView imgPhotos = view.findViewById(R.id.img_photo);


        Photo  photo = mListPhotos.get(position);
        if(photo != null){
            Glide.with(mContext).load(photo.getResourceID()).into(imgPhotos);
        }

        //Add view to ViewGroup
        container.addView(view);

        return view;
    }

    @Override
    public int getCount() {
        if(mListPhotos != null){
            return mListPhotos.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
