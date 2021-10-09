package com.example.mservice.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    private static final String TAG = "MyService";
    /**
     * 绑定服务
     * 1、创建一个MBinder类继承Binder
     * 2、在Service中声明一个自己创建的MBinder对象
     * 3、在onCreate中初始化MBinder
     * 4、在onBind中判断MBinder对象，然后返回该对象
     * 5、在Activitu只中声明一个MService和一个ServiceConntection 并进行初始化
     * 6、通过 bindService()进行服务的绑定，unbindService（）解绑服务
     */
    private MBinder mBinder;



    public MyService() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate: " );
        mBinder = new MBinder();
    }

    /**
     * OnStartCommand方法是最重要的方法，因为它在我们需要启动Service的时候被调用。
     * 在这个方法中，我们拥有在运行Service时传递进来的Intent，这样就可以与Service交换一些信息。
     * 在这个方法中，我们实现自己的逻辑：如果不是耗时的操作可以直接在这个方法中执行，否则可以创建一个线程。正如你看到的那样，这个方法需要返回一个整型值。
     * 这个整型代表系统应该怎么样处理这个Service：
     * 1.START_STICKY：使用这个返回值，如果系统杀死我们的Service将会重新创建。但是，发送给Service的Intent不会再投递。这样Service是一直运行的。
     * 2.START_NOT_STICKY：如果系统杀死了Service，不会重新创建，除非客户端显式地调用了onStart命令。
     * 3.START_REDELIVER_INTENT：功能与START_STICKY类似。另外，在这种情况下Intent会重新传递给Service。
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand: " );
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy: " );
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind: " );
        if (mBinder != null){
            return mBinder;
        }

        return null;

    }
    public class MBinder extends Binder {
        private String TAG = "MBinder";
        public void doTask(){
            Log.i(TAG, "doTask: ");
        }
        public MyService getService(){
            return MyService.this;
        }
    }
}
