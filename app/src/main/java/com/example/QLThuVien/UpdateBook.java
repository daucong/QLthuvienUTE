package com.example.QLThuVien;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.QLThuVien.DBBook.Book;
import com.example.QLThuVien.Database.SQLBook;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class UpdateBook extends AppCompatActivity {
    Button btnThem, btnThoat;
    ImageView imvHinh;
    EditText edTensach, edTheLoai, edNamXB, edTacGia, edSoluong;
    SQLBook sqlBook = new SQLBook(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_book);

        Anhxa();


        imvHinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent =new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent,8888);
            }
        });
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String BookTitle_Book = edTensach.getText().toString();
                String TheLoai_Book = edTensach.getText().toString();
                String TacGia_Book = edTensach.getText().toString();
                String NamXB_Book = edTensach.getText().toString();
                int SoLuong_Book = Integer.valueOf(edSoluong.getText().toString()) ;
                Book book = new Book(BookTitle_Book,TheLoai_Book,TacGia_Book,NamXB_Book,Integer.valueOf(ConverttoArrayByte(imvHinh).length),SoLuong_Book);

                sqlBook.AddBook(book);
                Toast.makeText(UpdateBook.this, "Thêm Thành Công", Toast.LENGTH_SHORT).show();
                open();
            }
        });
        ArrayList<Book> book = sqlBook.getAllBook();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode==8888&&resultCode==RESULT_OK)
        {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imvHinh.setImageBitmap(bitmap);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    public byte[] ConverttoArrayByte(ImageView img)
    {
        BitmapDrawable bitmapDrawable = (BitmapDrawable) img.getDrawable();
        Bitmap bitmap=bitmapDrawable.getBitmap();

        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
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
