package com.example.thuoc.BroadcartReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Internet extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ketnoi = connectivityManager.getActiveNetworkInfo();
        boolean isConnected = ketnoi != null && ketnoi.isConnectedOrConnecting();
        if (isConnected) {
            Toast.makeText(context.getApplicationContext(), "Đã kết nối Internet", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context.getApplicationContext(), "Mất kết nối Internet", Toast.LENGTH_SHORT).show();
        }

    }

}
