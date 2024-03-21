package com.example.thuoc.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.thuoc.R;
import com.example.thuoc.fragment.Home;
import com.example.thuoc.model.product;

public class chitietactivity extends AppCompatActivity {
    TextView tensp, giasp ,mota;
    Button btnthem;
    ImageView imageView;
    Toolbar toolbar;
    product pr;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitietsp);
        initView();
        ActionToolBar();

        Bundle data = getIntent().getExtras();
        if(data != null){
            pr = data.getParcelable("thuoc");
        }

        if( pr != null){

        initDaTa(pr);
        }
        
    }

    private void initDaTa(product thuoc) {
//        Home home = (Home) getIntent().getSerializableExtra("chi tiet");
        tensp.setText(thuoc.getName());
        giasp.setText((int) thuoc.getPrice() + " đ");
        imageView.setImageResource(thuoc.getRes());
        btnthem.setOnClickListener(v -> {

        });


    }
    private void initView(){
        tensp = findViewById(R.id.txttensanpham);
        giasp =findViewById(R.id.txtgiasp);
        mota = findViewById(R.id.txtmotachitiet);
        btnthem  = findViewById(R.id.themvaogiohang);
        imageView = findViewById(R.id.img_chitiet);
        toolbar = findViewById(R.id.toolbar);
    }
    private void ActionToolBar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        
    }

}
