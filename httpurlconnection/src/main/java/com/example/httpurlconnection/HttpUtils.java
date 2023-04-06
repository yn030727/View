package com.example.httpurlconnection;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtils {
    //0.获取实例
    private HttpURLConnection mConnection;

    public HttpUtils(String url) throws IOException{
        URL obj = new URL(url);
        this.mConnection = (HttpURLConnection) obj.openConnection();
    }

    //发送网络请求的方法
    public void sendRequestWithHttpURLConnection(){
        //1.开启线程发起网络请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                BufferedReader mReader = null;
                try{

                    //设置需要的参数
                    mConnection.setConnectTimeout(8000);
                    mConnection.setReadTimeout(8000);
                    mConnection.setRequestMethod("GET");

                    //2.开始读取获取到的输入流
                    InputStream in = mConnection.getInputStream();
                    mReader = new BufferedReader(new InputStreamReader(in));

                    //3.response可变字符序列
                    StringBuilder response = new StringBuilder();
                    String line;

                    while( (line = mReader.readLine()) != null ){
                        response.append(line);
                        Log.d("Ning: " , line);
                    }
                    //4.关闭流
                    in.close();
                    System.out.println("Ning: " + response.toString());


                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    if(mReader != null){
                        try{
                            mReader.close();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                    if(mConnection != null){
                        mConnection.disconnect();
                    }
                }
            }
        }).start();

    }
}
