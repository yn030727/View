package com.example.handler1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.IntentService;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private ThreadLocal<Boolean> mBooleanThreadLocal = new ThreadLocal<Boolean>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String TAG = "Ning";

        DownloadFilesTask dt = new DownloadFilesTask();
        dt.execute();


        mBooleanThreadLocal.set(true);
        Log.d(TAG, "[Thread#main]mBooleanThreadLocal = " + mBooleanThreadLocal.get());


        new Thread("Thread#1"){
            @Override
            public void run() {
                mBooleanThreadLocal.set(false);
                Log.d(TAG, "[Thread#1]mBooleanThreadLocal = " + mBooleanThreadLocal.get());
            }
        }.start();

        new Thread("Thread#2"){
            @Override
            public void run() {
                Looper.prepare();
                Handler handler = new Handler();
                Looper.loop();
            }
        }.start();

    }
}