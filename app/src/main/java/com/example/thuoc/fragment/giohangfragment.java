package com.example.thuoc.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thuoc.R;
import com.example.thuoc.adapter.giohangAdapter;
import com.example.thuoc.model.giohang;
import com.example.thuoc.model.product;


public class giohangfragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private View myview1;
    RecyclerView rvgiohang ;
    Button btnmuahang;
    TextView txtTongtien;


    public static giohang gio = new giohang();

    giohangAdapter GiohangAdapter;
    public giohangfragment(){};

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
        GiohangAdapter = new giohangAdapter(gio, this.getContext());
        GiohangAdapter.setTotalPriceUpdateListener(new giohangAdapter.TotalPriceUpdateListener() {
            @Override
            public void onUpdateTotalPrice(float totalPrice) {
                txtTongtien.setText(String.valueOf(totalPrice));
            }
        });
        rvgiohang = myview1.findViewById(R.id.rvgiohang);
        rvgiohang.setLayoutManager(new LinearLayoutManager(this.getContext()));
        rvgiohang.setAdapter(GiohangAdapter);
        btnmuahang=myview1.findViewById(R.id.btnmuahang);
        txtTongtien=myview1.findViewById(R.id.txttongtien);
        btnmuahang.setOnClickListener(v -> {
            if(gio.getListProduct().isEmpty()){
                Toast.makeText(this.getContext(),"Không có sản phẩm nào",Toast.LENGTH_SHORT).show();
            }else{
                float total = calculateTotalPrice();
                AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                builder.setTitle("Thông tin sản phẩm mua");
                // Tạo một StringBuilder để xây dựng nội dung cho AlertDialog
                StringBuilder message = new StringBuilder();
                for (product p : gio.getListProduct()) {
                    message.append("Tên sản phẩm: ").append(p.getName()).append("\n");
                    message.append("Số lượng: ").append(p.getAmount()).append("\n");
                    message.append("Giá tiền: ").append(p.getPrice()).append("\n");
                    message.append("\n");
                }
                // Thêm tổng giá tiền vào nội dung
                message.append("Tổng tiền: ").append(total).append(" đ");
                builder.setMessage(message.toString());
                builder.setPositiveButton("Đóng", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                // Hiển thị Dialog
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        return myview1;
    }



    @Override
    public void onResume() {
        updateAdapter(gio);
        super.onResume();
        txtTongtien.setText(Float.toString(calculateTotalPrice()));

    }



    void updateAdapter(giohang data){
        GiohangAdapter.update(data);
    }

    public static giohangfragment newInstance(String param1, String param2) {
        giohangfragment fragment = new giohangfragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    private float calculateTotalPrice() {
        float total =0;
        for(product p: gio.getListProduct()){
            total+=p.getAmount()*p.getPrice();
        }
        return total;
    }



}