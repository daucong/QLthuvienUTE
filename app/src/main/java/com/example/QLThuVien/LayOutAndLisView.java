package com.example.QLThuVien;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.QLThuVien.DBBook.Book;
import com.example.QLThuVien.DBBook.BookAdapter;
import com.example.QLThuVien.DBUser.User;
import com.example.QLThuVien.Database.SQLBook;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
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
    BookAdapter adapter;
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
        FloatingActionButton fab =findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LayOutAndLisView.this, UpdateBook.class);
                startActivity(intent);
            }
        });

    }



    public void ArrayBook(){
        SQLBook sqlBook = new SQLBook(this);
        ArrayList<Book> book = sqlBook.getAllBook();
        adapter = new BookAdapter(this, R.layout.elemen_book, book);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LayOutAndLisView.setBookid(position+1);
                OpenThongTinSach();
            }
        });
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder b=new AlertDialog.Builder(LayOutAndLisView.this);
                b.setTitle("Delete");
                b.setMessage("Bạn có muốn Xóa Sách \"" + book.get(position).getBookID() + "\" ?");
                b.setIcon(R.drawable.icon_delete);
                b.setPositiveButton("Yes", new DialogInterface. OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        sqlBook.DeleteBook(book.get(position));
                        Toast.makeText(LayOutAndLisView.this, "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                        reset();
                    }});
                b.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.cancel();
                    }
                });
                b.create().show();
                return false;
            }
        });
    }

    public void reset(){
        Intent intent = new Intent(this, LayOutAndLisView.class);
        startActivity(intent);
        finish();
    }
    public void OpenThongTinSach(){
        Intent intent = new Intent(this, BookInformation.class);
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.search, menu);
        MenuItem menuItem = menu.findItem(R.id.search);
        SearchView searchView= (SearchView)menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);

                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}
