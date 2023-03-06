package com.example.remoteviews;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button  = (Button)findViewById(R.id.button1);
        button.setOnClickListener(this);
    }

    @SuppressLint("RemoteViewLayout")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button1:
                Intent intent = new Intent(MainActivity.this,DemoActivity2.class);
                PendingIntent pi = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                Notification notification = new Notification.Builder(this)
                        .setContentTitle("This is title")
                        .setContentText("hello world")
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher_background))
                        .setContentIntent(pi)
                        .setAutoCancel(true)
                        .build();
                manager.notify(1,notification);


                //创建RemoteViews对象我们只需要知道·当前应用包名和布局文件的资源id
                RemoteViews remoteViews = new RemoteViews(getPackageName(),R.layout.layout_notification);
                remoteViews.setTextViewText(R.id.msg , "chapter_5");
                remoteViews.setImageViewResource(R.id.icon,R.drawable.icon1);
                PendingIntent pi2 = PendingIntent.getActivity(this , 0 , new Intent(this , DemoActivity2.class) , PendingIntent.FLAG_UPDATE_CURRENT);
                remoteViews.setOnClickPendingIntent(R.id.button_click,pi2);
                Notification notification1 = new Notification.Builder(this)
                        .setWhen(System.currentTimeMillis())
                        .setAutoCancel(true)
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .build();
                notification1.contentView = remoteViews;
                notification1.contentIntent = pi2;
                NotificationManager manager1 = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                manager1.notify(2,notification1);
                break;
            default:
                break;
        }
    }
}