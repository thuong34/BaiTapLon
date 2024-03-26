package com.example.thuoc.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thuoc.R;
import com.example.thuoc.adapter.giohangAdapter;
import com.example.thuoc.adapter.productAdapter;
import com.example.thuoc.databinding.FragmentGiohangBinding;
import com.example.thuoc.model.giohang;
import com.example.thuoc.model.product;

import java.util.ArrayList;
import java.util.List;


public class Giohang extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private View myview1;
    RecyclerView rvgiohang ;
    Button btnmuahang;
    TextView txtTongtien;

    public static giohang gio = new giohang();

    giohangAdapter giohangAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myview1= inflater.inflate(R.layout.fragment_giohang, container, false);
        giohangAdapter = new giohangAdapter(gio, this.getContext());
        rvgiohang = myview1.findViewById(R.id.rvgiohang);
        rvgiohang.setLayoutManager(new LinearLayoutManager(this.getContext()));
        rvgiohang.setAdapter(giohangAdapter);
        btnmuahang=myview1.findViewById(R.id.btnmuahang);
        txtTongtien=myview1.findViewById(R.id.txttongtien);
        btnmuahang.setOnClickListener(v -> {
            float total = calculateTotalPrice();

            // Thực hiện các hành động cần thiết khi thanh toán, ví dụ: mở màn hình thanh toán, lưu đơn hàng, hiển thị thông báo thành công, vv.
            // Ví dụ: Hiển thị thông báo với tổng tiền
            Toast.makeText(this.getContext(), "Tổng tiền: " + total + " đ", Toast.LENGTH_SHORT).show();
        });

        return myview1;
    }

//    }

    @Override
    public void onResume() {
        updateAdapter(gio);
        super.onResume();
        txtTongtien.setText(Float.toString(gio.getTotalprice()));

    }

    void updateAdapter(giohang data){
        giohangAdapter.update(data);
    }

    public static Giohang newInstance(String param1, String param2) {
        Giohang fragment = new Giohang();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    private float calculateTotalPrice() {
        return gio.getTotalprice();
    }
}