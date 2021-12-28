package com.example.QLThuVien;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.QLThuVien.Database.SQLSever;
import com.google.android.material.snackbar.Snackbar;

public class NewPassword extends Activity {
    EditText pass1, pass2;
    SQLSever sqlUser = new SQLSever(this);
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_new_password);

          pass1 = (EditText) findViewById(R.id.newpass1);
          pass2 = (EditText) findViewById(R.id.newpass2);
        Button xacnhan = (Button) findViewById(R.id.bt_newpass);
        Intent intent = getIntent();
        email = intent.getStringExtra("EMAIL");

        xacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value1 = pass1.getText().toString().trim();
                String value2 = pass2.getText().toString().trim();

                if (value1.isEmpty() && value2.isEmpty()){
                    Toast.makeText(NewPassword.this, "Vui lòng nhập thông tin ", Toast.LENGTH_LONG).show();
                    return;
                }

                if (!value1.contentEquals(value2)){
                    Toast.makeText(NewPassword.this, "Mật khẩu không đúng", Toast.LENGTH_LONG).show();
                    return;
                }

                if (!sqlUser.checkUser(email)) {

                    Toast.makeText(NewPassword.this, "Email không đúng", Toast.LENGTH_SHORT).show();
                    return;

                } else {
                    sqlUser.updatePassword(email, value1);

                    Toast.makeText(NewPassword.this, "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                    emptyInputEditText();

                    Intent intent = new Intent(NewPassword.this, Login.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
    private void emptyInputEditText()
    {
        pass1.setText("");
        pass2.setText("");
    }
}
