package com.example.mservice;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

import com.example.mservice.service.MyService;

public class MainActivity extends AppCompatActivity {
    private String TAG = "MainActivity";
    private MyService myService;
    private ServiceConnection connection;
    private MyService.MBinder binder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.i(TAG, "onServiceConnected: name = "+name);
                binder = (MyService.MBinder)service;
               myService = binder.getService();
                binder.doTask();
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.i(TAG, "onServiceDisconnected: name = "+name);
                myService= null;
            }
        };

    }
    /*开始服务*/
    public void onStartService(View view) {
        Intent intent = new Intent(this,MyService.class);
        startService(intent);
    }
    /*停止服务*/
    public void onStopService(View view) {
        Intent intent = new Intent(this,MyService.class);
        stopService(intent);
    }
    /*绑定服务*/
    public void onBindService(View view) {
        Intent intent = new Intent(this,MyService.class);
        bindService(intent,connection, Service.BIND_AUTO_CREATE);
    }
    public void onUnbindService(View view) {
        // 解除绑定
        if(myService!=null) {
            myService = null;
            unbindService(connection);
        }

    }
}