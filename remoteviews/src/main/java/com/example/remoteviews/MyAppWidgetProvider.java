package com.example.remoteviews;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.SystemClock;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

public class MyAppWidgetProvider extends android.appwidget.AppWidgetProvider {
    public static final String TAG = "MyAppWidgetProvider";
    public static final String CLICK_ACTION = "com.example.remoteviews.CLICK";

    public MyAppWidgetProvider(){
        super();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        Log.i(TAG, "onReceive: action = " + intent.getAction());
        //这里判断自己的action，做自己的事情
        //比如小部件被单击了要干什么，这里是做一个动画效果。
        if(intent.getAction().equals(CLICK_ACTION)){
            Toast.makeText(context , "clicked it" , Toast.LENGTH_SHORT).show();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    Bitmap srcbBitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.icon1);
                    AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
                    for(int i=0 ; i<37 ; i++){
                        float degree = (i * 10) % 360 ;
                        RemoteViews remoteViews = new RemoteViews(context.getPackageName() , R.layout.widget);
                        remoteViews.setImageViewBitmap(R.id.imageView1 , rotateBitmap(context , srcbBitmap , degree));
                        Intent intent1 = new Intent();
                        intent1.setAction(CLICK_ACTION);
                        PendingIntent pi = PendingIntent.getBroadcast(context , 0 ,intent1 , 0);
                        //点击remoteViews发送Intent广播
                        remoteViews.setOnClickPendingIntent(R.id.imageView1 , pi);
                        //通过小组件管理者来进行组件更新！！！！
                        appWidgetManager.updateAppWidget(new ComponentName(context , MyAppWidgetProvider.class) , remoteViews);
                        SystemClock.sleep(30);
                    }
                }
            }).start();
        }
    }

    //每次桌面小组件更新时都调用一次该方法
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        Log.i(TAG, "onUpdate");

        final int counter = appWidgetIds.length;
        Log.i(TAG, "onUpdate: " + counter);
        for(int i = 0 ; i < counter ; i++){
            int appWidgetId = appWidgetIds[i];
            onWidgetUpdate(context , appWidgetManager , appWidgetId);
        }
    }


    //桌面小组件的更新
    private void onWidgetUpdate(Context context , AppWidgetManager appWidgetManager , int appWidgetId){
        Log.i(TAG, "onWidgetUpdate: " + appWidgetId);
        RemoteViews remoteViews = new RemoteViews(context.getPackageName()  ,R.layout.widget);

        //桌面小部件单击事件发送的Intent广播
        Intent intentClick = new Intent();
        intentClick.setAction(CLICK_ACTION);
        PendingIntent pi = PendingIntent.getBroadcast(context , 0 ,intentClick , 0);
        remoteViews.setOnClickPendingIntent(R.id.imageView1 , pi);
        appWidgetManager.updateAppWidget(appWidgetId , remoteViews);
    }


    //Bitmap旋转方法
    private Bitmap rotateBitmap(Context context , Bitmap srcbBitmap , float degree){
        Matrix matrix = new Matrix();
        matrix.reset();
        matrix.setRotate(degree);
        Bitmap tmpBitmap = Bitmap.createBitmap(srcbBitmap , 0 , 0 ,srcbBitmap.getWidth() , srcbBitmap.getHeight() , matrix ,true);
        return tmpBitmap;
    }

}
