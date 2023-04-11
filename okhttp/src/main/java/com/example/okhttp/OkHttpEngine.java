package com.example.okhttp;

import android.content.Context;
import android.os.Handler;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpEngine {
    private static volatile OkHttpEngine mInstance;
    private OkHttpClient mOkHttpClient;
    private Handler mHandler;

    public OkHttpEngine(Context context) {
        File sdCache = context.getExternalCacheDir();
        int cacheSize = 10 * 1024 * 1024;
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(15 , TimeUnit.SECONDS)
                .writeTimeout(20 , TimeUnit.SECONDS)
                .readTimeout(20 , TimeUnit.SECONDS)
                .cache(new Cache(sdCache.getAbsoluteFile() , cacheSize));
        mOkHttpClient = builder.build();
        mHandler = new Handler();
    }

    public static OkHttpEngine getInstance(Context context){
        if(mInstance == null){
            synchronized (OkHttpEngine.class){
                if(mInstance == null){
                    mInstance = new OkHttpEngine(context);
                }
            }
        }
        return mInstance;
    }

    //异步GET请求
    public void getAsyncHttp(String url , ResultCallback callback){
        final Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = mOkHttpClient.newCall(request);
        dealResult(call , callback);
    }

    private void dealResult(Call call , final ResultCallback callback){
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                sendFailedCallback(call.request() , e , callback);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                sendSuccessCallback(response.body().string() , callback);
            }
        });
    }

    private void sendSuccessCallback(final String str , final ResultCallback callback){
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    callback.onResponse(str);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void sendFailedCallback(final Request request , final Exception e , final  ResultCallback callback){
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if(callback != null){
                    callback.onError(request , e);
                }
            }
        });
    }
}
