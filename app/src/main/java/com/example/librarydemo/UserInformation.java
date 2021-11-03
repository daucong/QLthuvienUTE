package com.example.librarydemo;


import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.librarydemo.DBUser.User;
import com.example.librarydemo.Database.SQLSever;

public class UserInformation extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_information);

        TextView infor_acccount = (TextView) findViewById(R.id.info_account1);
        TextView infor_fullname = (TextView) findViewById(R.id.info_fullname);
        TextView infor_email = (TextView) findViewById(R.id.info_email);
        TextView infor_status = (TextView) findViewById(R.id.info_status);
        TextView infor_quyen = (TextView) findViewById(R.id.info_quyen);
        ImageView anh = (ImageView) findViewById(R.id.image_Avatar);
        anh.setImageResource(R.drawable.user_icon);
        String name = Login.EXTRA_USER;
        final SQLSever sqlSever = new SQLSever(this);
        User s = sqlSever.getUser(name);
        infor_acccount.setText(s.getAccount());
        infor_fullname.setText(s.getFullname());
        infor_email.setText(s.getGmail());
        infor_status.setText(s.getStatus());
        infor_quyen.setText(String.valueOf(s.getQuyen()));

    }
}
