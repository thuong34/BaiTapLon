package com.example.thuoc.adapter;

import android.content.Context;
import android.icu.text.ListFormatter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thuoc.R;
import com.example.thuoc.fragment.Giohang;
import com.example.thuoc.model.giohang;
import com.example.thuoc.model.product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class giohangAdapter extends RecyclerView.Adapter<giohangAdapter.GioHangViewHolder> {
    private static giohang gio;





    private ObData obData;
    private Context context;

    public giohangAdapter(giohang gio, Context context ) {
        this.gio = gio;
        this.context = context;

        obData = new ObData(gio);
        update(gio);
    }

    @NonNull
    @Override
    public GioHangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemcart, parent, false);
        return new giohangAdapter.GioHangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GioHangViewHolder holder, int position) {
        product p=gio.getListProduct().get(position);
        holder.ten.setText(p.getName());
        holder.gia.setText((int) p.getPrice() + " đ");
        holder.hinhanh.setImageResource(p.getRes());
//        holder.quantity.setText(giohang.getSoluong());

    }

    @Override
    public int getItemCount() {
        return obData.getData().getListProduct().size();
    }

    public static class GioHangViewHolder extends RecyclerView.ViewHolder {
        ImageView hinhanh;
        TextView ten, gia,them, xoa;
        EditText quantity;


        public GioHangViewHolder(@NonNull View itemView) {
            super(itemView);
            hinhanh = itemView.findViewById(R.id.cartimg);
            ten = itemView.findViewById(R.id.cartname);
            gia = itemView.findViewById(R.id.cartgia);
            them = itemView.findViewById(R.id.tvthem);
            xoa = itemView.findViewById(R.id.tvxoa);
            quantity = itemView.findViewById(R.id.amount);
            them.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int currentQuantity = Integer.parseInt(quantity.getText().toString());
                    currentQuantity++;
                    updateQuantity(currentQuantity);
                }
            });

            xoa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int currentQuantity = Integer.parseInt(quantity.getText().toString());
                    if (currentQuantity > 0) {
                        currentQuantity--;
                        updateQuantity(currentQuantity);
                    }
                }
            });



        }






        private void updateQuantity(int newQuantity) {
            quantity.setText(String.valueOf(newQuantity));
        }

    }
//    private static float calculateTotalPrice() {
//        float total = 0;
//        for (giohang gioHang : listgiohang) {
//            // Lấy giá của sản phẩm
//            float gia = gioHang.getGia();
//            // Lấy số lượng của sản phẩm trong giỏ hàng
//            int soLuong = cartList.getOrDefault(gioHang.getId(), 0);
//            // Tính tổng tiền của sản phẩm và cộng vào tổng tiền
//            total += gia * soLuong;
//        }
//        return total;
//    }

    public class ObData implements Observer<giohang> {
        giohang data;
        public ObData (giohang lst){
            this.data=lst;
        }
        @Override
        public void onChanged(giohang giohangs) {
            this.data = giohangs;
        }

        public giohang getData() {
            return data;
        }

        public void update(giohang data) {
            onChanged(data);
        }
    }

    public void update(giohang data) {
        obData.update(data);
        notifyDataSetChanged();
    }

}
