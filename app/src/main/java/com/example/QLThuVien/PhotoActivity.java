package com.example.QLThuVien;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

public class PhotoActivity extends AppCompatActivity {


    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    private PhotoAdapter photopAdapter;
    private Button btn_boqua;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider_photo);

        viewPager = findViewById(R.id.viewpager);
        circleIndicator = findViewById(R.id.circleIndicator);
        photopAdapter = new PhotoAdapter(this, getListPhoto());
        viewPager.setAdapter(photopAdapter);
        circleIndicator.setViewPager(viewPager);
        photopAdapter.registerDataSetObserver(circleIndicator.getDataSetObserver());

        btn_boqua = findViewById( R.id.btn_boqua );
        btn_boqua.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenLogin();
            }
        } );
    }

    private void OpenLogin() {
        Intent intent= new Intent(PhotoActivity.this, Login.class );
        startActivity( intent );
    }


    private List<Photo> getListPhoto(){
        List<Photo> list = new ArrayList<>();
        list.add(new Photo(R.drawable.slider_img1));
        list.add(new Photo(R.drawable.slider_img2));
        list.add(new Photo(R.drawable.slider_img3));
        return  list;
    }
}
