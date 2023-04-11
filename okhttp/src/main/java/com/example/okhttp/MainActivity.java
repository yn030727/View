package com.example.okhttp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.os.Environment;
import android.renderscript.ScriptGroup;
import android.util.Log;
import android.widget.ImageView;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imageView = (ImageView)findViewById(R.id.image1);
        OkHttpWithImage();
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



    public void okHttpRequestWithPost(){
        RequestBody formBody = new FormBody.Builder()
                .add("token" , "LwExDtUWhF3rH5ib")
                .build();
        Request request = new Request.Builder()
                .url("https://v2.alapi.cn/api/qinghua")
                .post(formBody)
                .build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String str = response.body().string();
                Log.d("Ning" , str);
            }
        });
    }


    public static final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown ; charset=utf-8");
    public void okHttpRequestWithFile() {
        String filepath = "";
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            //获取根目录并且转化成绝对路径字符串
            filepath = Environment.getExternalStorageDirectory().getAbsolutePath();
        } else {
            return;
        }

        File file = new File(filepath, "ning.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            FileWriter writer = new FileWriter(file);
            writer.write("OkHttp");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        Request request = new Request.Builder()
                .url("https://api.github.com/markdown")
                .post(RequestBody.create(MEDIA_TYPE_MARKDOWN, file))
                .build();
        OkHttpClient mOkHttpClient = new OkHttpClient();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("Ning", e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d("Ning", response.body().string());
            }
        });
    }

    public void OkHttpWithImage(){
        String url = "http://www.baidu.com/img/bdlogo.png";
        Request request = new Request.Builder()
                .url(url)
                .build();
        OkHttpClient mOkHttpClient = new OkHttpClient();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream inputStream = response.body().byteStream();
                final Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                inputStream.close();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ImageView imageView = findViewById(R.id.image1);
                        imageView.setImageBitmap(bitmap);
                    }
                });
            }
        });
    }


    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
    public void sendMultipart(){
        OkHttpClient mOkHttpClient = new OkHttpClient();
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("title" , "Ning")
                .addFormDataPart("image" , "Ning.jpg" , RequestBody.create(MEDIA_TYPE_PNG , new File("/sdcard/ning.jpg")))
                .build();
        Request request = new Request.Builder()
                .header("Authoriaztion" , "Client-ID " + "...")
                .url("http://www.baidu.com/img/bdlogo.png")
                .post(requestBody)
                .build();

        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d("Ning" , response.body().string());
            }
        });


        File sdCache = getExternalCacheDir();
        int cacheSize = 10 * 1024 * 1024;
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(15 , TimeUnit.SECONDS)
                .readTimeout(20 , TimeUnit.SECONDS)
                .writeTimeout(20 , TimeUnit.SECONDS)
                .cache(new Cache(sdCache.getAbsoluteFile() , cacheSize));
        mOkHttpClient = builder.build();
    }
}