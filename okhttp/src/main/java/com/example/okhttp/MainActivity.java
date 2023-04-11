package com.example.okhttp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        okHttpRequest();
    }

    public void okHttpRequest(){
        Request.Builder requestBuilder = new Request.Builder().url("https://www.baidu.com/");
        requestBuilder.method("GET" , null);
        Request request = requestBuilder.build();
        OkHttpClient mOkHttpClient = new OkHttpClient();
        Call mCall = mOkHttpClient.newCall(request);
        mCall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("Ning" , e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String str = response.body().string();
                Log.d("Ning" , str);
            }
        });
    }
}