package com.example.QLThuVien.DBBook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;

import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.QLThuVien.R;

import java.util.ArrayList;

public class BookAdapter extends BaseAdapter implements Filterable {
    private Context context;
    private int layout;
    private ArrayList<Book> modelarrayList = new ArrayList<>();
    private ArrayList<Book> modelarrayListFiler;


    public BookAdapter(Context context, int layout, ArrayList<Book> modelarrayList) {
        this.context = context;
        this.layout = layout;
        this.modelarrayList = modelarrayList;
        this.modelarrayListFiler = modelarrayList;
    }

    @Override
    public int getCount() {
        return modelarrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                ArrayList<Book> bookArrayListfilter = new ArrayList<>();
                String searchText = constraint.toString().toLowerCase();
                String[] sqlit=searchText.split(",");
                ArrayList<String> searchArr = new ArrayList<>(sqlit.length);
                for (String tsqlit: sqlit){
                    String trim = tsqlit.trim();
                    if (trim.length()>0){
                        searchArr.add(trim);
                    }
                    for (Book book : modelarrayListFiler){
                        if(book.getTenSach().toLowerCase().trim().contains(searchText)){
                            bookArrayListfilter.add(book);
                        }
                    }
                }
                filterResults.count= bookArrayListfilter.size();
                filterResults.values = bookArrayListfilter;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                modelarrayList = (ArrayList<Book>)results.values;
                notifyDataSetChanged();
            }
        };
        return filter;
    }

    public class ViewAnhXa{
        TextView tensach, tacgia, theloai, soluong, score, page;
        ImageView imgsach, imgtim;
        RatingBar ratingBar;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewAnhXa viewAnhXa;
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout,null);
            viewAnhXa = new ViewAnhXa();
            //Ánh Xạ View
            viewAnhXa.tensach= (TextView) convertView.findViewById(R.id.ten_sach);
            viewAnhXa.tacgia = (TextView) convertView.findViewById(R.id.tac_gia);
            viewAnhXa.score= (TextView) convertView.findViewById(R.id.item_book_score);
            viewAnhXa.page = (TextView) convertView.findViewById(R.id.item_book_pagesrev);
            viewAnhXa.theloai = (TextView) convertView.findViewById(R.id.the_loai);
            viewAnhXa.soluong = (TextView) convertView.findViewById(R.id.so_luong);
            viewAnhXa.imgtim = (ImageView) convertView.findViewById(R.id.imageView3);
            viewAnhXa.imgsach = (ImageView) convertView.findViewById(R.id.img_hinh);
            viewAnhXa.ratingBar = (RatingBar) convertView.findViewById(R.id.item_book_ratingbar);
            convertView.setTag(viewAnhXa);
        }else{
            viewAnhXa = (ViewAnhXa) convertView.getTag();
        }

        //Gán Giá Trị
        Book book = modelarrayList.get(position);
        //Ghi giá trị vào listview
        viewAnhXa.tensach.setText(book.getTenSach());
        viewAnhXa.tacgia.setText("TG:"+book.getTacGia() + "-Năm XB:" + book.getNamXB());
        viewAnhXa.theloai.setText(book.getTheLoai());
        viewAnhXa.soluong.setText( "SL:"+ String.valueOf(book.getSoLuong()));
        viewAnhXa.imgsach.setImageResource(book.getImgBook());

        return convertView;
    }
}
