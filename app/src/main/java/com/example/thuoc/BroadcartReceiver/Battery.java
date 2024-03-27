package com.example.thuoc.BroadcartReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class Battery extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String pinyeu = intent.getAction();
        if(pinyeu != null && pinyeu.equals(Intent.ACTION_BATTERY_LOW)){
            Toast.makeText(context,"Pin Yếu",Toast.LENGTH_SHORT).show();
        };
    }
}
