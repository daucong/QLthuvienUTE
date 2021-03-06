package com.example.QLThuVien;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.QLThuVien.DBBook.Book;
import com.example.QLThuVien.Database.SQLBook;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class UpdateBook extends AppCompatActivity {
    Button btnThem, btnThoat;
    ImageView imvHinh;
    Uri mPhotoUri;
    EditText edTensach, edTheLoai, edNamXB, edTacGia, edSoluong;
    SQLBook sqlBook = new SQLBook(this);

    private boolean mContactHasChanged = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_book);

        Anhxa();


        imvHinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trySelector();
                mContactHasChanged = true;
            }
        });
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String BookTitle_Book = edTensach.getText().toString();
                String TheLoai_Book = edTheLoai.getText().toString();
                String TacGia_Book = edTacGia.getText().toString();
                String NamXB_Book = edNamXB.getText().toString();
                String SoLuong_Book = edSoluong.getText().toString() ;


                String hinh = mPhotoUri.toString();
                if(BookTitle_Book.equals("") || TheLoai_Book.equals("")|| TacGia_Book.equals("")|| NamXB_Book.equals("")|| SoLuong_Book.equals("")|| hinh.equals("")){
                    Toast.makeText( UpdateBook.this, "Vui L??ng ??i???n ????? Th??ng tin!!!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Book book = new Book(BookTitle_Book,TheLoai_Book,TacGia_Book,NamXB_Book,hinh,Integer.parseInt(SoLuong_Book));

                    sqlBook.AddBook(book);
                    Toast.makeText(UpdateBook.this, "Th??m Th??nh C??ng", Toast.LENGTH_SHORT).show();
                    Log.v("Cong", hinh);
                    open();
                }

            }
        });
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UpdateBook.this, LayOutAndLisView.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (data != null) {
            mPhotoUri = data.getData();
            imvHinh.setImageURI(mPhotoUri);
            imvHinh.invalidate();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
    public void trySelector() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            return;
        }
        openSelector();
    }
    private void openSelector() {
        Intent intent;
        if (Build.VERSION.SDK_INT < 19) {
            intent = new Intent(Intent.ACTION_GET_CONTENT);
        } else {
            intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
        }
        intent.setType(getString(R.string.intent_type));
        startActivityForResult(Intent.createChooser(intent, getString(R.string.select_image)), 0);
    }
    public void Anhxa(){
        btnThem = (Button) findViewById(R.id.tt_btThem);
        btnThoat = (Button) findViewById(R.id.tt_bthuy);
        imvHinh = (ImageView) findViewById(R.id.tt_img_Anh);
        edTensach = (EditText) findViewById(R.id.edit_tensach);
        edTheLoai = (EditText) findViewById(R.id.edit_theloai);
        edNamXB = (EditText) findViewById(R.id.edit_nxb);
        edTacGia = (EditText) findViewById(R.id.edit_tacgia);
        edSoluong = (EditText) findViewById(R.id.edit_soluong);

    }
    public void open(){
        Intent intent = new Intent(this, LayOutAndLisView.class);
        startActivity(intent);
    }
}
