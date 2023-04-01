package com.example.a2thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TestThread mThread = new TestThread();
        mThread.start();

        TestRunnable runnable = new TestRunnable();
        Thread mThread2 = new Thread(runnable);
        mThread2.start();
    }
}