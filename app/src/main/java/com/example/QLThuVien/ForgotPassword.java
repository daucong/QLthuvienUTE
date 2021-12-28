package com.example.QLThuVien;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import com.example.QLThuVien.DBUser.User;
import com.example.QLThuVien.Database.SQLSever;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Random;

public class ForgotPassword extends Activity {
    public static final String EXTRA_CODE =  Login.EXTRA_USER;
    public static String getAccount() {
        return Account;
    }
    private NotificationCompat.Builder mBuilder;
    public static void setAccount(String account) {
        Account = account;
    }
    public static String Account;
    EditText email;
    Button bt_qmk, bt_backqmk;
    SQLSever sqlUser = new SQLSever(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_forgot_password);
          email = (EditText) findViewById(R.id.Et_emailqmk);
          bt_qmk = (Button) findViewById(R.id.bt_qmkc);
          bt_backqmk = (Button) findViewById(R.id.bt_backqmk);

        bt_qmk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyFromSQLite();
          }
        });

        bt_backqmk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenLogin();
            }
        });


    }

    private void verifyFromSQLite(){

        if (email.getText().toString().isEmpty()){
            Toast.makeText(this, "Vui lòng nhập thông tin", Toast.LENGTH_SHORT).show();
            return;
        }


        if (sqlUser.checkUser(email.getText().toString().trim())) {
            Intent accountsIntent = new Intent(this, NewPassword.class);
            accountsIntent.putExtra("EMAIL", email.getText().toString().trim());
            emptyInputEditText();
            startActivity(accountsIntent);
        } else {
            Toast.makeText(ForgotPassword.this, "Email không đúng", Toast.LENGTH_SHORT).show();
        }
    }

    private void emptyInputEditText(){
        email.setText("");
    }

    public void OpenLogin(){
        Intent intent = new Intent(ForgotPassword.this, Login.class);
        startActivity(intent);
    }
}
