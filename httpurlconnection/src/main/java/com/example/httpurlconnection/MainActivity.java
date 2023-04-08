package com.example.httpurlconnection;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NetworkImageView nv_image = (NetworkImageView) findViewById(R.id.image1);
        RequestQueue mQueue = Volley.newRequestQueue(getApplicationContext());
        ImageLoader imageLoader = new ImageLoader(mQueue, new ImageLoader.ImageCache() {
            @Override
            public Bitmap getBitmap(String url) {
                return null;
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {

            }
        });
        nv_image.setDefaultImageResId(R.drawable.ic_launcher_background);
        nv_image.setErrorImageResId(R.drawable.ic_launcher_background);
        nv_image.setImageUrl("http://www.baidu.com/img/bdlogo.png" , imageLoader);





/*        ImageView imageView = (ImageView)findViewById(R.id.image1);


        RequestQueue mQueue = Volley.newRequestQueue(getApplicationContext());
        ImageLoader imageLoader = new ImageLoader(mQueue, new ImageLoader.ImageCache() {
            @Override
            public Bitmap getBitmap(String url) {
                return null;
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {

            }
        });
        ImageLoader.ImageListener listener = ImageLoader.getImageListener(imageView , R.drawable.ic_launcher_background , R.drawable.ic_launcher_background);
        imageLoader.get("http://rongcloud-web.qiniudn.com/docs_demo_rongcloud_logo.png" , listener);*/








        //        RequestQueue mQueue = Volley.newRequestQueue(getApplicationContext());
//        ImageRequest imageRequest = new ImageRequest("http://www.baidu.com/img/bdlogo.png" , new Response.Listener<Bitmap>() {
//            @Override
//            public void onResponse(Bitmap response) {
//                imageView.setImageBitmap(response);
//            }
//        }, 300, 300, Bitmap.Config.RGB_565, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                imageView.setImageResource(R.drawable.ic_launcher_background);
//            }
//        });
//       mQueue.add(imageRequest);










        //        String TAG = "Ning : ";
//        Log.d(TAG, "onCreate: 11111111111111111111111111");
//        RequestQueue mQueue = Volley.newRequestQueue(getApplicationContext());
//        //JsonRequest 和 StringRequest的使用方法类似
//        JsonObjectRequest mJsonObjectRequest = new JsonObjectRequest(Request.Method.POST,"https://v2.alapi.cn/api/qinghua?token=LwExDtUWhF3rH5ib", null,new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                HappyJoke happyJoke = new Gson().fromJson(response.toString() , HappyJoke.class);
//                Log.d(TAG, "onResponse: begin!!!!!!!!!!");
//                if(happyJoke != null && happyJoke.getData() != null && happyJoke.getAuthor() != null){
//                    String content = happyJoke.getData().getContent();
//                    String name = happyJoke.getAuthor().getName();
//                    String desc = happyJoke.getAuthor().getDesc();
//                    int code = happyJoke.getCode();
//                    String msg = happyJoke.getMsg();
//                    Log.d("Ning : " , content);
//                    Log.d("Ning : " , name);
//                    Log.d("Ning : " , desc);
//                    Log.d("Ning : " , code + "");
//                    Log.d("Ning : " , msg);
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.e("Ning:" , error.getMessage() , error);
//            }
//        });
//        mQueue.add(mJsonObjectRequest);









//        RequestQueue mQueue = Volley.newRequestQueue(getApplicationContext());
//        StringRequest mStringRequest = new StringRequest(Request.Method.GET, "http://192.168.1.117:8084/ping", new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Log.d("Ning" , response);
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.e("Ning" , error.getMessage() , error);
//            }
//        });
//
//        mQueue.add(mStringRequest);

        //        Button button = (Button) findViewById(R.id.button1);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                try {
//                    HttpUtils httpUtils = new HttpUtils("http://www.baidu.com");
//                    httpUtils.sendRequestWithHttpURLConnection();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        });

    }
}