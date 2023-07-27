package com.example.androidreview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import java.util.concurrent.BlockingDeque;

public class MainActivity extends AppCompatActivity {
    //1.获取Binder
    private MyService.DownloadBinder downloadBinder;
    //2.获取connection
    private ServiceConnection connection = new ServiceConnection() {
        //这两个方法会在活动与服务成功绑定以及解除绑定前后调用
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //向下转型获得mBinder
            downloadBinder = (MyService.DownloadBinder) service;
            downloadBinder.startDownload();
            downloadBinder.getProgress();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };





    LocationReceiver locationReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationReceiver = new LocationReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("location.report");
        registerReceiver(locationReceiver , intentFilter);
        Log.d("Ning", "onReceive: 11111");

        Intent intent = new Intent(this , MyService.class);
        startService(intent);
//        bindService(intent , connection , BIND_AUTO_CREATE);
//        unbindService(connection);
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(locationReceiver);
        super.onDestroy();
    }

    //内部类，实现BroadcastReceiver，创建内部类作为广播接收器
    public class LocationReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            String intentAction = intent.getAction();
            if(intentAction.equals("location.report")){
                Log.d("Ning", "onReceive: 111111111");
            }
        }
    }

}