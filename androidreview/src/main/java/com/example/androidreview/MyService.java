package com.example.androidreview;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    public MyService() {
    }

    class DownloadBinder extends Binder{
        public void startDownload(){
            Log.d("MyService", "startDownload executed");
        }
        public void getProgress(){
            Log.d("MyService", "getProgress execute");
        }
    }
    //一个Binder对象来对下载功能进行管理
    private DownloadBinder mBinder = new DownloadBinder();


    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("Ning", "onStartCommand: ");
        intent.putExtra("key" , "text");
        intent.setAction("location.report");
        sendBroadcast(intent);
        return super.onStartCommand(intent, flags, startId);
    }
}