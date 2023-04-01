package com.example.a2thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TestCallable testCallable = new TestCallable();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future future =  executorService.submit(testCallable);
        try{
            System.out.println(future.get());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}