package com.example.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.util.LruCache;

import com.jakewharton.disklrucache.DiskLruCache;

import java.io.File;
import java.util.BitSet;

public class ImageLoader {
    private Context mContext;
    private LruCache<String , Bitmap> mMemoryCache;
    private DiskLruCache mDiskLruCache;

    private ImageLoader(Context context){
        mContext = context.getApplicationContext();
        int maxMemory = (int)(Runtime.getRuntime().maxMemory() / 1024);
        int cacheSize = maxMemory / 8;
        mMemoryCache = new LruCache<String , Bitmap>(cacheSize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight() / 1024;
            }
        };
        File diskCacheDir = getDiskCacheDir(mContext , "bitmap");
        if(!diskCacheDir.exists()){
            diskCacheDir.mkdirs();
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
}
