package com.example.thuoc.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thuoc.R;
import com.example.thuoc.fragment.giohangfragment;
import com.example.thuoc.model.giohang;
import com.example.thuoc.model.product;

public class giohangAdapter extends RecyclerView.Adapter<giohangAdapter.GioHangViewHolder> {
    private static giohang gio;
    private ObData obData;
    private Context context;
    private TotalPriceUpdateListener listener;




    public giohangAdapter(giohang gio, Context context ) {
        this.gio = gio;
        this.context =context;


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
        holder.quantity.setText(String.valueOf(p.getAmount()));
        holder.them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tăng số lượng sản phẩm lên 1 khi người dùng ấn vào nút cộng
                p.setAmount(p.getAmount() + 1);

                // Cập nhật số lượng mới lên giao diện
                holder.quantity.setText(String.valueOf(p.getAmount()));

                // Tính toán lại tổng giá trị của giỏ hàng
                updateTotalPrice();
            }
        });

        holder.xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Giảm số lượng sản phẩm đi 1 khi người dùng ấn vào nút trừ
                if (p.getAmount() > 0) {
                    p.setAmount(p.getAmount() - 1);

                    // Cập nhật số lượng mới lên giao diện
                    holder.quantity.setText(String.valueOf(p.getAmount()));

                    // Tính toán lại tổng giá trị của giỏ hàng
                    updateTotalPrice();
                }
            }
        });

    }
    private void updateTotalPrice() {
        float total = 0;
        for (product p : gio.getListProduct()) {
            total += p.getAmount() * p.getPrice();
        }
        if (listener != null) {
            listener.onUpdateTotalPrice(total);
        }
    }
    public interface TotalPriceUpdateListener {
        void onUpdateTotalPrice(float totalPrice);
    }
    public void setTotalPriceUpdateListener(TotalPriceUpdateListener listener) {
        this.listener = listener;
    }
    @Override
    public int getItemCount() {
        return obData.getData().getListProduct().size();
    }

    public class GioHangViewHolder extends RecyclerView.ViewHolder {
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
