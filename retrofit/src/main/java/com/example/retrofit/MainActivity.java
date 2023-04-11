package com.example.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //接下来创建Retrofit，并创建接口文件
        String url = "https://www.baidu.com/" ;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        BaiduService baiduService = retrofit.create(BaiduService.class);
        Call<ResponseBody> call = baiduService.getHomePage();

        //用Call请求网络，并处理回调
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String message = response.body().toString();
                Log.d("Ning" , message);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("Ning" , "onFailure:1111111111");
            }
        });
    }
}