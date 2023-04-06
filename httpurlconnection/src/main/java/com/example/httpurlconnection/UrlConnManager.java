package com.example.httpurlconnection;

import android.provider.Settings;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.jar.Attributes;

public class UrlConnManager {

    //1.在里面提供getHttpURLConnection方法用于配置默认的参数并且返回HttpURLConnection
    public static HttpURLConnection getHttpURLConnection(String url){
        HttpURLConnection mHttpURLConnection = null;
        try{
            URL mUrl = new URL(url);
            mHttpURLConnection = (HttpURLConnection) mUrl.openConnection();

            //（1）设置连接超时时间
            mHttpURLConnection.setConnectTimeout(15000);
            //（2）设置读取超时时间
            mHttpURLConnection.setReadTimeout(15000);
            //（3）设置请求参数
            mHttpURLConnection.setRequestMethod("POST");
            //（4）添加Header
            mHttpURLConnection.setRequestProperty("Connection" , "Keep-Alive");
            //（5）接收输入流
            mHttpURLConnection.setDoInput(true);
            //（6）传递参数时需要开启
            mHttpURLConnection.setDoOutput(true);
        }catch (IOException e){
            e.printStackTrace();
        }
        return mHttpURLConnection;
    }

}
