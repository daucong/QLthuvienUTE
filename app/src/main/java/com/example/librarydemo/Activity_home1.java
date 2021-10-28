package com.example.librarydemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.librarydemo.DBBook.Book;
import com.example.librarydemo.DBUser.User;
import com.example.librarydemo.Database.SQLBook;

import java.util.ArrayList;
import java.util.List;

public class Activity_home1 extends AppCompatActivity implements OnItemClickListener {
    private RecyclerView rcvData;
    private homeAdapter  Adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home1);
        rcvData =findViewById(R.id.rcvData);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvData.setLayoutManager(linearLayoutManager);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rcvData.addItemDecoration(itemDecoration);
        Adapter= new homeAdapter(getHomeList(), this);
        rcvData.setAdapter(Adapter);

    }
    private List<home> getHomeList(){
        List<home> list = new ArrayList<>();
        list.add(new home(R.drawable.user_icon, "Tài khoản"));
        list.add(new home(R.drawable.home, "Danh mục"));
        list.add(new home(R.mipmap.ic_launcher, "Thêm sách"));
        list.add(new home(R.mipmap.ic_launcher, "Sách đã mượn"));
        list.add(new home(R.mipmap.ic_launcher, "Thông báo"));
        list.add(new home(R.mipmap.ic_launcher, "Liên hệ"));
        return list;
    }
    @Override
    public void onItemClick(home a) {
        if (a.getName()=="Tài khoản"){
            Intent intent = new Intent(this, UserInformation.class); //LayOutAndLisView.class);
            startActivity(intent);

        }
        if (a.getName()=="Danh mục"){
            Intent intent = new Intent(this, LayOutAndLisView.class); //LayOutAndLisView.class);
            startActivity(intent);
        }
        if (a.getName()=="Thêm sách"){
            Intent intent = new Intent(this, UpdateBook.class); //LayOutAndLisView.class);
            startActivity(intent);
        }
        if (a.getName()=="Sách đã mượn"){
            Intent intent = new Intent(this, ArrayLog.class); //LayOutAndLisView.class);
            startActivity(intent);
        }
    }
}