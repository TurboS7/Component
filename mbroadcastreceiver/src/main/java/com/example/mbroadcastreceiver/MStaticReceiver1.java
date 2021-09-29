package com.example.mbroadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MStaticReceiver1 extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("MStaticBroadCastReceiver", "onReceive: ");
        Toast.makeText(context, "收到了静态注册的广播！", Toast.LENGTH_SHORT).show();
        abortBroadcast();
    }


}
