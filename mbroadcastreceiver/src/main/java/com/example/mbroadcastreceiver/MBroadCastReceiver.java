package com.example.mbroadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MBroadCastReceiver extends BroadcastReceiver {
    private Context content;


    public MBroadCastReceiver() {

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("TAG", "onReceive: ");
        if(intent.getAction().equals("test")){
            System.out.println(intent.getStringExtra("data"));
            Toast.makeText(context, "收到广播", Toast.LENGTH_SHORT).show();
        }
    }
}
