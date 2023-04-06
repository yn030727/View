package com.example.httpurlconnection;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RequestQueue mQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest mStringRequest = new StringRequest(Request.Method.GET, "http://192.168.1.117:8084/ping", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Ning" , response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Ning" , error.getMessage() , error);
            }
        });

        mQueue.add(mStringRequest);

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