package com.example.a2thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MoonRunner moonRunner = new MoonRunner();
        Thread thread = new Thread(moonRunner);
        thread.start();
        try {
            TimeUnit.MILLISECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        moonRunner.cancel();


        Lock mLock = new ReentrantLock();
        mLock.lock();
        try{

        }finally {
            mLock.unlock();
        }

//        TestCallable testCallable = new TestCallable();
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
//        Future future =  executorService.submit(testCallable);
//        try{
//            System.out.println(future.get());
//        }catch (Exception e){
//            e.printStackTrace();
//        }
    }
}