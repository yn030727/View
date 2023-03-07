package com.example.moniatob;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import android.widget.RemoteViews;

public class ActivityA extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity);

        RemoteViews remoteViews = new RemoteViews(getPackageName() , R.layout.layout_simulated_notification);
        remoteViews.setTextViewText(R.id.msg , "msg from process ： " + Process.myPid());
        remoteViews.setImageViewResource(R.id.icon , R.drawable.ic_launcher_background);


    }
}