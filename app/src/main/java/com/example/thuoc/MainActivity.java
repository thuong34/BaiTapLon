package com.example.thuoc;


import android.os.Bundle;

import android.view.Menu;

import android.view.MenuItem;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.thuoc.adapter.productAdapter;
import com.example.thuoc.fragment.Giohang;
import com.example.thuoc.fragment.Home;
import com.example.thuoc.fragment.fragmentAdapter;
import com.example.thuoc.model.product;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    BottomNavigationView mNavigationView;
    ViewPager viewpager;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNavigationView = findViewById(R.id.bottom_navigation);
        viewpager = findViewById(R.id.viewpager);
        fragmentAdapter adapter = new fragmentAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewpager.setAdapter(adapter);
        viewpager.setOffscreenPageLimit(2);


        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position)
                {
                    case 0:
                        mNavigationView.getMenu().findItem(R.id.trangchu).setChecked(true);
                        Home home = (Home) viewpager.getAdapter().instantiateItem(viewpager,0);
                        home.reloadData();
                        break;
                    case 1:
                        mNavigationView.getMenu().findItem(R.id.uudai).setChecked(true);
                        break;
                    case 2:
                        mNavigationView.getMenu().findItem(R.id.giohang).setChecked(true);
                        break;
                    case 3:
                        mNavigationView.getMenu().findItem(R.id.thongbao).setChecked(true);
                        break;
                    case 4:
                        mNavigationView.getMenu().findItem(R.id.taikhoan).setChecked(true);
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
               if(item.getItemId()==R.id.trangchu){
                   viewpager.setCurrentItem(0);
               }
                else if(item.getItemId()==R.id.uudai){
                   viewpager.setCurrentItem(1);
               }
               else if(item.getItemId()==R.id.giohang){
                   viewpager.setCurrentItem(2);
               }
               else if(item.getItemId()==R.id.thongbao){
                   viewpager.setCurrentItem(3);
               }
               else if(item.getItemId()==R.id.taikhoan){
                   viewpager.setCurrentItem(4);
               }
                return true;
            }

        });



}}