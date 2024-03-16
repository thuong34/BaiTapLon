package com.example.thuoc;


import android.os.Bundle;

import android.view.Menu;

import android.widget.Toast;



import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thuoc.adapter.productAdapter;
import com.example.thuoc.model.product;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    SearchView searchView;

    RecyclerView rvproduct;
    ArrayList<product> list;
    productAdapter productadapter;
    BottomNavigationView mNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvproduct = findViewById(R.id.rvproduct);
        mNavigationView = findViewById(R.id.bottom_navigation);

        searchView=findViewById(R.id.search_item);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });
        list =getListproduct();
        productadapter = new productAdapter(list,MainActivity.this);
        rvproduct.setAdapter(productadapter);


    }


    private void filterList(String newText) {
        ArrayList<product> filterList = new ArrayList<product>();
        for(product itemproduct : list){
            if(itemproduct.getName().toLowerCase().contains(newText.toLowerCase())){
                filterList.add(itemproduct);
            }
        }
        if(filterList.isEmpty()){
            Toast.makeText(this,"Không tìm thấy dữ liệu",Toast.LENGTH_LONG).show();
        }else{
            productadapter.setFilterList(filterList);
        }

    }

    private ArrayList<product> getListproduct() {
        ArrayList<product> lst = new ArrayList<>();
       for (int i = 0; i < 10; i++) {
            lst.add(new product(9, "HAPACAL", 30.5F, R.drawable.thuoc9));
            lst.add(new product(8, "PANADOL", 15.6F, R.drawable.thuoc8));
            lst.add(new product(7, "CHELA-FERR FORTE", 48.2F, R.drawable.thuoc7));
            lst.add(new product(6, "GMP-WHO", 35.6F, R.drawable.thuoc6));
            lst.add(new product(5, "MOLMUPIRAVIR", 98.7F, R.drawable.thuoc5));
            lst.add(new product(4, "AZITHROMYCIN", 87.6F, R.drawable.thuoc4));
            lst.add(new product(3, "DEXAMETHASONE", 145.66F, R.drawable.thuoc3));
            lst.add(new product(2, "TERPIN-CODEIN", 120.4F, R.drawable.thuoc2));
            lst.add(new product(1, "POGANIC", 90.5F, R.drawable.thuoc1));
            lst.add(new product(0, "ĐÔNG Y", 80.6F, R.drawable.thuoc0));
        }

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        rvproduct.setLayoutManager(mLayoutManager);
        productAdapter productadapter = new productAdapter(lst,MainActivity.this);
        rvproduct.setAdapter(productadapter);
        return lst;
    }
}