package com.example.QLThuVien.DBBook;

import android.graphics.Bitmap;

public class Book {
    private int BookID;
    private String TenSach, TheLoai, TacGia, NamXB,ImgBook;
    private int SoLuong;

    public Book() {
    }

    public Book(int bookID, String tenSach, String theLoai, String tacGia, String namXB, String imgBook, int soLuong) {
        BookID = bookID;
        TenSach = tenSach;
        TheLoai = theLoai;
        TacGia = tacGia;
        NamXB = namXB;
        ImgBook = imgBook;
        SoLuong = soLuong;
    }

    public Book(String tenSach, String theLoai, String tacGia, String namXB, String imgBook, int soLuong) {
        TenSach = tenSach;
        TheLoai = theLoai;
        TacGia = tacGia;
        NamXB = namXB;
        ImgBook = imgBook;
        SoLuong = soLuong;
    }

    public int getBookID() {
        return BookID;
    }

    public void setBookID(int bookID) {
        BookID = bookID;
    }

    public String getTenSach() {
        return TenSach;
    }

    public void setTenSach(String tenSach) {
        TenSach = tenSach;
    }

    public String getTheLoai() {
        return TheLoai;
    }

    public void setTheLoai(String theLoai) {
        TheLoai = theLoai;
    }

    public String getTacGia() {
        return TacGia;
    }

    public void setTacGia(String tacGia) {
        TacGia = tacGia;
    }

    public String getNamXB() {
        return NamXB;
    }

    public void setNamXB(String namXB) {
        NamXB = namXB;
    }

    public String getImgBook() {
        return ImgBook;
    }

    public void setImgBook(String imgBook) {
        ImgBook = imgBook;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int soLuong) {
        SoLuong = soLuong;
    }

    @Override
    public String toString() {
        return "Book{" +
                "BookID=" + BookID +
                ", TenSach='" + TenSach + '\'' +
                ", TheLoai='" + TheLoai + '\'' +
                ", TacGia='" + TacGia + '\'' +
                ", NamXB='" + NamXB + '\'' +
                ", ImgBook='" + ImgBook + '\'' +
                ", SoLuong=" + SoLuong +
                '}';
    }
}
