package com.example.mbroadcastreceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private MDynamicReceiver receiver;
    private MDynamicReceiver1 receiver1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        receiver = new MDynamicReceiver();
        receiver1 = new MDynamicReceiver1();
        IntentFilter intentFilter = new IntentFilter("dynamic");
        IntentFilter intentFilter1 = new IntentFilter("dynamic");
        intentFilter.setPriority(100);
        intentFilter1.setPriority(900);
        registerReceiver(receiver,intentFilter);
        registerReceiver(receiver1,intentFilter1);
    }
    /*发送无序广播（动态注册）*/
    public void onDisOrderBrocast(View view) {
        System.out.println("发送无序广播（动态注册）");
        Intent intent = new Intent("dynamic");
        intent.putExtra("msg","来自MainActivity动态注册的无序广播");
        sendBroadcast(intent);
    }

    /*发送有序广播（动态注册）*/
    public void onOrderBrocast(View view) {
        System.out.println("发送有序广播（动态注册）");
        Intent intent = new Intent("dynamic");
        intent.putExtra("msg","来自MainActivity动态注册的有序广播");
        sendOrderedBroadcast(intent,null);
    }
    /*发送有序广播（静态注册）*/
    public void onOrderBrocast1(View view) {
        System.out.println("发送有序广播（静态注册）");
        Intent intent = new Intent();
        intent.setAction("com.zs.staticreceiver");
        intent.setComponent(new ComponentName(MainActivity.this,MStaticReceiver.class));//显示指定组件名称
//        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        sendOrderedBroadcast(intent,null);
    }

    public void onDisOrderBrocast1(View view) {
        System.out.println("发送无序广播（静态注册）");
        Intent intent = new Intent();
        intent.setAction("com.zs.staticreceiver");
        intent.setComponent(new ComponentName(MainActivity.this,MStaticReceiver1.class));//显示指定组件名称
//        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        sendBroadcast(intent);
    }

}