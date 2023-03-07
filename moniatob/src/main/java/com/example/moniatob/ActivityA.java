package com.example.moniatob;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import android.provider.SyncStateContract;
import android.widget.RemoteViews;

public class ActivityA extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity);

        RemoteViews remoteViews = new RemoteViews(getPackageName() , R.layout.layout_simulated_notification);
        remoteViews.setTextViewText(R.id.msg , "msg from process ： " + Process.myPid());
        remoteViews.setImageViewResource(R.id.icon , R.drawable.ic_launcher_background);

        PendingIntent pi = PendingIntent.getActivity(this , 0 , new Intent(this , DemoActivity_1.class), PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent openActivity2PendingIntent = PendingIntent.getActivity(this , 0 , new Intent(this , DemoActivity_2.class) , PendingIntent.FLAG_UPDATE_CURRENT);

        remoteViews.setOnClickPendingIntent(R.id.item_holder , pi);
        remoteViews.setOnClickPendingIntent(R.id.open_activity2 , openActivity2PendingIntent);
    }
}