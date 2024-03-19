package com.example.thuoc.fragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class fragmentAdapter extends FragmentStatePagerAdapter {
    public fragmentAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0: return new Home();
            case 1: return new Uudai();
            case 2: return new Giohang();
            case 3: return new thongbao();
            case 4: return new taikhoan();
            default: return new Home();
        }
    }

    @Override
    public int getCount() {
        return 5;
    }
}
