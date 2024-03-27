package com.example.thuoc.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.thuoc.databinding.ActivityChitietspBinding;
import com.example.thuoc.fragment.giohangfragment;
import com.example.thuoc.model.product;
//import com.squareup.picasso.Picasso;


public class chitietactivity extends AppCompatActivity {

    product product;
    ActivityChitietspBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChitietspBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ActionToolBar();
        Bundle data = getIntent().getExtras();
        if(data != null){
            product = data.getParcelable("thuoc");
            binding.txttensanpham.setText(product.getName());
            binding.txtgiasp.setText(String.valueOf(product.getPrice()));
            binding.imgChitiet.setImageResource(product.getRes());
        }

        binding.themvaogiohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    giohangfragment.gio.AddProduct(product);
                    Toast.makeText(chitietactivity.this, "Đã thêm vào giỏ hàng", Toast.LENGTH_LONG).show();

            }
        });

    }
    private void ActionToolBar(){
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        
    }

}
