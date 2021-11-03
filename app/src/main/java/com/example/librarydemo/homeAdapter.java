package com.example.librarydemo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class homeAdapter extends RecyclerView.Adapter<homeAdapter.HomeViewHoder>  {
    private List<home> homeList;
    private OnItemClickListener listener;
    public homeAdapter(List<home> homeList, OnItemClickListener listener)
    {
        this.homeList = homeList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public HomeViewHoder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_danhmuc, viewGroup, false);

        return new HomeViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHoder homeViewHoder, int i) {
        final home a = homeList.get(i);
        if (a == null){
            return;
        }
        homeViewHoder.imgAvatar.setImageResource(a.getImage());
        homeViewHoder.tv_name.setText(a.getName());
        homeViewHoder.a= a;
    }

    @Override
    public int getItemCount() {
        if (homeList != null){
            return homeList.size();
        }
        return 0;
    }

    public class HomeViewHoder extends RecyclerView.ViewHolder{
        private home a;
        private ImageView imgAvatar;
        private TextView tv_name;
        private RelativeLayout layoutItem;
        public HomeViewHoder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(a);
                }
            });
            layoutItem = itemView.findViewById(R.id.layoutItem);
            imgAvatar = itemView.findViewById(R.id.imgDM);
            tv_name = itemView.findViewById(R.id.tv_DM);

        }
    }
}
