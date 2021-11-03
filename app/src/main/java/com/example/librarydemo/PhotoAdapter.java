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

    private Context mContext;
    private List<Photo> mPhotoList;

    public PhotoAdapter(Context mContext, List<Photo> mlPhotoList) {
        this.mContext = mContext;
        this.mPhotoList = mPhotoList;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
       View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_photo,container,false  );
        ImageView imgPhotos = view.findViewById(R.id.img_photo);

        Photo photo = mPhotoList.get(position);
        if (photo != null){
            Glide.with( view ).load( photo.getResourceID() ).into( imgPhotos );
        }

        container.addView(view);
        return  view;
    }

    @Override
    public int getCount() {
        try {
            return mPhotoList.size();
        }catch (NullPointerException exception){
            return 0;
        }

    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
