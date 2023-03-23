package com.example.handler1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Log;
import android.util.LruCache;

import com.jakewharton.disklrucache.DiskLruCache;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private ThreadLocal<Boolean> mBooleanThreadLocal = new ThreadLocal<Boolean>();

    private static final long DISK_CACHE_SIZE = 1024 * 1025 * 50;
    File diskCacheDir = getDiskCacheDir(this , "bitmap");
    DiskLruCache mDiskLruCache;
    {
        if(!diskCacheDir.exists()){
            diskCacheDir.mkdirs();
        }
        try {
            mDiskLruCache = DiskLruCache.open(diskCacheDir , 1 , 1 , DISK_CACHE_SIZE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public File getDiskCacheDir(Context context , String uniqueName){
        String cachePath;
        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable()){
            cachePath = context.getExternalCacheDir().getPath();
        }else{
            cachePath = context.getCacheDir().getPath();
        }
        return new File(cachePath + File.separator + uniqueName);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("Ning","1");
        Intent service = new Intent(this , MyIntentService.class);
        service.putExtra("task_action","com.ryg.action.TASK1");
        startService(service);
        service.putExtra("task_action","com.ryg.action.TASK2");
        startService(service);
        service.putExtra("task_action","com.ryg.action.TASK3");
        startService(service);
        Log.d("Ning","2");


        //        String TAG = "Ning";
//
//        DownloadFilesTask dt = new DownloadFilesTask();
//        dt.execute();
//
//
//        mBooleanThreadLocal.set(true);
//        Log.d(TAG, "[Thread#main]mBooleanThreadLocal = " + mBooleanThreadLocal.get());
//
//
//        new Thread("Thread#1"){
//            @Override
//            public void run() {
//                mBooleanThreadLocal.set(false);
//                Log.d(TAG, "[Thread#1]mBooleanThreadLocal = " + mBooleanThreadLocal.get());
//            }
//        }.start();
//
//        new Thread("Thread#2"){
//            @Override
//            public void run() {
//                Looper.prepare();
//                Handler handler = new Handler();
//                Looper.loop();
//            }
//        }.start();


//        Bitmap bitmap = new BitmapFactory();
//
//
//        //总容量的大小为当前进程可用内存的1/8，单位为KB
//        int maxMemory = (int)(Runtime.getRuntime().maxMemory() / 1024);
//        int cacheSize = maxMemory / 8 ;
//        LruCache mMemoryCache = new LruCache<String , Bitmap>(cacheSize){
//            //sizeOf的作用是计算缓存对象的大小
//            @Override
//            protected int sizeOf(String key, Bitmap value) {
//                //除以1024也是为了将其单位转化成KB(缓存的大小单位需要和总容量的单位一致）
//                return bitmap.getRowBytes() * bitmap.getHeight() / 1024;
//            }
//        };


    }


    public static Bitmap decodeSampledBitmapFromResource(Resources res , int resId , int reqWidth , int reqHeight){
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res , resId , options);

        //计算inSampleSize
        options.inSampleSize = calculateInSampleSize(options , reqWidth , reqHeight);

        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res , resId , options);
    }
    public static int calculateInSampleSize(BitmapFactory.Options options , int reqWidth , int reqHeight){
        final int height = options.outHeight;
        final int width = options.outWidth;;
        int inSampleSize = 1;

        if(height > reqHeight || width > reqWidth){
            final int halfHeight = height/2 ;
            final int halfWidth = width/2 ;

            while((halfHeight / inSampleSize) >= reqHeight && (halfWidth / inSampleSize) >= reqWidth){
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }
}