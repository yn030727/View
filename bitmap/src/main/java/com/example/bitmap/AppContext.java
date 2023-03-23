package com.example.bitmap;

import android.app.Application;
import android.content.Context;
import android.view.Display;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

public class AppContext extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //缓存图片的配置,一般通用配置
        initImageLoader(getApplicationContext());
    }

    private void initImageLoader(Context context){
        //创建DisplayImageOptions对象
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        //创建ImageLoaderConfiguration对象
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(context)
                .defaultDisplayImageOptions(defaultOptions)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO).build();
        //ImageLoader对象的配置
        ImageLoader.getInstance().init(configuration);
    }
}
