package com.example.httpurlconnection;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        

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