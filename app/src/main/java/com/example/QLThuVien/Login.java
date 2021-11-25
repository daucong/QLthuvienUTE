package com.example.QLThuVien;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.QLThuVien.DBBook.Book;
import com.example.QLThuVien.DBUser.User;
import com.example.QLThuVien.Database.SQLBook;
import com.example.QLThuVien.Database.SQLSever;

import java.util.ArrayList;

public class Login extends Activity {

    public static String EXTRA_USER ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);

        final EditText password = (EditText) findViewById(R.id.pass);
        final EditText username = (EditText) findViewById(R.id.username);
        final Button login = (Button) findViewById(R.id.login);
        final Button signup = (Button) findViewById(R.id.SignUp);
        final Button quenmk = (Button) findViewById(R.id.quenmk);
        final ImageView libraryicon= (ImageView) findViewById(R.id.iconlibrary);

        libraryicon.setImageResource(R.drawable.icons8_library);

        final SQLSever sqlSever = new SQLSever(this);
        ArrayList<User> list = new ArrayList<>();
       User s = new User("daucong", "Đậu Thái Công", "dauthaicong11092001@gmail.com", "110901", "Admin", 3);
       sqlSever.AddUser(s);
//
//        SQLLog sqlLog = new SQLLog(this);
//        Log log= new Log("daucong", 5, "Sức Mạnh Của Ngôn Từ", "20191111");
//        sqlLog.AddLog(log);
        //---------------------------------------------------------------------------
        password.setInputType(InputType.TYPE_CLASS_TEXT |//ẩn Text để làm mật khẩu
                InputType.TYPE_TEXT_VARIATION_PASSWORD);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenSignUp();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = username.getText().toString();
                String pass = password.getText().toString();
                if(name.equals("") || pass.equals("")){
                    Toast.makeText( Login.this, "Vui Lòng Điền Đủ Thông tin!!!", Toast.LENGTH_SHORT).show();
                }else{
                    User s = sqlSever.getUser(name);
                    if(s != null){
                        if(s.getPassword().equals(pass)){
                            EXTRA_USER = name;
                            Toast.makeText( Login.this, "Đăng nhập thành công ^.^", Toast.LENGTH_SHORT).show();
                            AddBook();
                            password.setText("");
                            username.setText("");
                            Login(s);

                        }else {
                            password.setText("");
                            username.setText("");
                            Toast.makeText( Login.this, "Tài khoản hoặc mật khẩu không chính xác!!!", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        password.setText("");
                        username.setText("");
                        Toast.makeText( Login.this, "Tài khoản Không Tồn tại!!!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        quenmk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenForgotPassword();
            }
        });
    }
    public void AddBook(){
        SQLBook sqlBook = new SQLBook(this);
        ArrayList<Book> book = new ArrayList<>();
        book.add(new Book(0, "Để Con Được Ốm", "Sách Tự Lực", "Uyên Bùi - BS. Trí Đoàn","2016",R.drawable.book_0, 100));
        book.add(new Book(1, "Đọc Vị Bất Kỳ Ai", "Sách Tự Lực", "TS. David J. Lieberman","2015",R.drawable.book_1, 100));
        book.add(new Book(2, "Nghệ Thuật Bán Hàng Bậc Cao", "Nghề Bán Hàng", "Zig Zig Lar","2008",R.drawable.book_2, 100));
        book.add(new Book(3, "Dấn Thân", "Tiểu Sử", "Sheryl Sandberg","2014",R.drawable.book_3, 100));
        book.add(new Book(4, "Sức Mạnh Của Ngôn Từ", "Văn học", "Vô Danh","TB-2018",R.drawable.book_4, 100));
        book.add(new Book(5, "Đắc Nhân Tâm", "Phi Hư Cấu", "Dale Carnegie","2013",R.drawable.book_5, 100));
        book.add(new Book(6, "Truyện kiều", "Tiểu Thuyết", "Nguyễn Du","1832",R.drawable.book_6, 100));
        book.add(new Book(7, "Sức Mạnh Của Ngôn Từ", "Văn học", "Vô Danh","TB-2018",R.drawable.book_7, 100));
        book.add(new Book(8, "Đắc Nhân Tâm", "Phi Hư Cấu", "Dale Carnegie","2013",R.drawable.book_8, 100));
        book.add(new Book(9, "Nhà Giả Kim", "Tiểu Thuyết", "Paulo Coelho","2013",R.drawable.book_9, 100));
        book.add(new Book(10, "Nhà Giả Kim", "Tiểu Thuyết", "Paulo Coelho","2013",R.drawable.book_10, 100));
        for(Book x: book){
            sqlBook.AddBook(x);
        }
    }
    public void OpenSignUp(){
        Intent intent = new Intent( Login.this, SignUp.class);
        startActivity(intent);
    }

    public void Login(User s){
        Intent intent = new Intent( Login.this, Activity_home1.class); //LayOutAndLisView.class);
        intent.putExtra(EXTRA_USER, s.getAccount());
        startActivity(intent);
        finish();
    }

    public void OpenForgotPassword(){
        Intent intent = new Intent( Login.this, ForgotPassword.class);
        startActivity(intent);
    }


}
