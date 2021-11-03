package com.example.librarydemo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.librarydemo.DBBook.Book;
import com.example.librarydemo.DBBook.BookAdapter;
import com.example.librarydemo.DBUser.User;
import com.example.librarydemo.Database.SQLBook;
import com.example.librarydemo.Database.SQLSever;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class LayOutAndLisView extends AppCompatActivity

        implements NavigationView.OnNavigationItemSelectedListener {

    private ListView lv;

    public static ArrayList<Book> Book_Deefault;

    public static ArrayList<Book> getBook_Deefault() {
        return Book_Deefault;
    }

    public static void setBook_Deefault(ArrayList<Book> book_Deefault) {
        Book_Deefault = book_Deefault;
    }
    private BookAdapter adapter;
    //---------------User hiện tại------------------------------------
    public static User user_pro;

    public static User getUser() {
        return user_pro;
    }
    public void setUser(User user) {
        this.user_pro = user;
    }

    //--------------Lấy Sách Đưa vào Book information---------------
    public static int Bookid;
    public static int getBookid() {
        return Bookid;
    }
    public static void setBookid(int bookid) {
        Bookid = bookid;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lay_out_and_lis_view);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        AnhXa();
        ArrayBook();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


    }

    public void ArrayBook(){
        SQLBook sqlBook = new SQLBook(this);
        ArrayList<Book> book = new ArrayList<>();
        book.add(new Book(0, "Để Con Được Ốm", "Sách Tự Lực", "Uyên Bùi - BS. Trí Đoàn","2016",R.drawable.book_0, 100));
        book.add(new Book(1, "Đọc Vị Bất Kỳ Ai", "Sách Tự Lực", "TS. David J. Lieberman","2015",R.drawable.book_1, 100));
        book.add(new Book(2, "Nghệ Thuật Bán Hàng Bậc Cao", "Nghề Bán Hàng", "Zig Zig Lar","2008",R.drawable.book_2, 100));
        book.add(new Book(3, "Dấn Thân", "Tiểu Sử", "Sheryl Sandberg","2014",R.drawable.book_3, 100));
        book.add(new Book(4, "Sức Mạnh Của Ngôn Từ", "Văn học", "Vô Danh","TB-2018",R.drawable.book_4, 100));
        book.add(new Book(5, "Đắc Nhân Tâm", "Phi Hư Cấu", "Dale Carnegie","2013",R.drawable.book_5, 100));
        book.add(new Book(6, "Nhà Giả Kim", "Tiểu Thuyết", "Paulo Coelho","2013",R.drawable.book_6, 100));

        for(Book x: book){
            sqlBook.AddBook(x);
        }
        adapter = new BookAdapter(this, R.layout.elemen_book, book);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ArrayBook();
                LayOutAndLisView.setBookid(position+1);
                OpenThongTinSach();
            }
        });
    }

    public void OpenThongTinSach(){
        Intent intent = new Intent(this, BookInformation.class);
        ArrayBook();
        startActivity(intent);
    }
    public void AnhXa(){
        lv= (ListView) findViewById(R.id.arraybook);
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen( GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.lay_out_and_lis_view, menu);


        Intent intent = getIntent();
        String name = Login.EXTRA_USER;
        final SQLSever sqlSever = new SQLSever(this);
        TextView ten = (TextView) findViewById(R.id.Text_Name);
        TextView email = (TextView) findViewById(R.id.Text_Gmail);
        TextView trangthai = (TextView) findViewById(R.id.Text_TrangThai);

        User s = sqlSever.getUser(name);

        ten.setText(s.getFullname());
        email.setText(s.getGmail());
        trangthai.setText(s.getStatus());
        this.setUser(s);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_Log) {
            Intent intent = new Intent(this, ArrayLog.class);
            ArrayBook();
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_camera) {
            Intent intent = new Intent(this, UserInformation.class);
            startActivity(intent);
        } else if (id == R.id.nav_gallery) {
            Intent intent = new Intent(this, ChangPass.class);
            startActivity(intent);
        } else if (id == R.id.nav_slideshow) {
            AlertDialog.Builder b=new AlertDialog.Builder(LayOutAndLisView.this);
            b.setTitle("Đăng Xuất");
            b.setMessage("Bạn có muốn đăng xuất?");
            b.setIcon(R.drawable.icons_out);
            b.setPositiveButton("Yes", new DialogInterface. OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    OpenLogin();
                    finish();
                }});
            b.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    dialog.cancel();
                }
            });
            b.create().show();
        } else {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void OpenLogin(){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
}
