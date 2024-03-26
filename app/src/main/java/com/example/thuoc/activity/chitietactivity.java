package com.example.thuoc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.thuoc.R;
import com.example.thuoc.databinding.ActivityChitietspBinding;
import com.example.thuoc.fragment.Giohang;
import com.example.thuoc.fragment.Home;
import com.example.thuoc.model.giohang;
import com.example.thuoc.model.product;
//import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

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
                Giohang.gio.AddProduct(product);

                Toast.makeText(chitietactivity.this,"Da them vao gio hang",Toast.LENGTH_LONG).show();
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
