package com.example.eventbus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.squareup.otto.Bus;

import org.greenrobot.eventbus.EventBus;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);

        EventBus.getDefault().unregister(this);
        Bus bus = new Bus();
        bus.register(this);
        bus.post(this);
        bus.unregister(this);
    }
}