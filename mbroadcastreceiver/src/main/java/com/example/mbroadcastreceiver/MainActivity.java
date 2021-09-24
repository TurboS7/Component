package com.example.mbroadcastreceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private MBroadCastReceiver receiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        receiver = new MBroadCastReceiver();
        IntentFilter filter = new IntentFilter("test");
        filter.setPriority(10);
        registerReceiver(receiver,filter);
    }

//    private void init() {
//
//
//    }

    public void click(View view) {
        Intent intent = new Intent();
        intent.setAction("test");
        intent.putExtra("data","2321321321");
        sendBroadcast(intent);
    }
}