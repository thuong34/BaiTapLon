package com.example.thuoc;


import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;

import android.view.MenuItem;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.thuoc.BroadcartReceiver.Battery;
import com.example.thuoc.BroadcartReceiver.Internet;
import com.example.thuoc.fragment.trangchufragment;
import com.example.thuoc.fragment.fragmentAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;





public class MainActivity extends AppCompatActivity {

    BottomNavigationView mNavigationView;
    ViewPager viewpager;
    private Internet internet;
    private Battery pinyeu;




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
                        trangchufragment home = (trangchufragment) viewpager.getAdapter().instantiateItem(viewpager,0);
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
        internet = new Internet();
        pinyeu = new Battery();

    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(internet, intentFilter);
        //Pin yeu
        IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_LOW);
        registerReceiver(pinyeu, filter);


    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(internet);
        unregisterReceiver(pinyeu);
    }
}