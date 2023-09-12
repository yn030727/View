package com.example.networkwen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    public static final String API_KEY = "5GHSlGSkqlDiGA57zWmilkwp";
    public static final String SECRET_KEY = "UAbadAXq7Evl1qbg9j9aWGcXibZ8oNH8";
    static final OkHttpClient HTTP_CLIENT = new OkHttpClient().newBuilder().connectTimeout(60000, TimeUnit.MILLISECONDS).readTimeout(60000 , TimeUnit.MILLISECONDS).build();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Runnable mt = new Runnable() {
            @Override
            public void run() {
                MediaType mediaType = MediaType.parse("application/json");
                RequestBody requestBody = RequestBody.create(mediaType , "{\"messages\":[{\"role\":\"user\",\"content\":\"Android中的SharedPreference是什么？给出代码使用\"}]}");
                try {
                    Request request = new Request.Builder()
                            .url("https://aip.baidubce.com/rpc/2.0/ai_custom/v1/wenxinworkshop/chat/completions?access_token=" + getAccessToken())
                            .method("POST" , requestBody)
                            .addHeader("Content-Type", "application/json")
                            .build();
                    HTTP_CLIENT.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            e.printStackTrace();
                            System.out.println("1111111111111111111111111" + "失败了");
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            System.out.println(response.body().string());
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        Thread mt1 = new Thread(mt);
        mt1.start();

    }



    static String getAccessToken() throws Exception{
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody requestBody = RequestBody.create(mediaType , "grant_type=client_credentials&client_id=" + API_KEY + "&client_secret=" + SECRET_KEY);
        Request request = new Request.Builder()
                .url("https://aip.baidubce.com/oauth/2.0/token")
                .method("POST" , requestBody)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .build();
        Response response = HTTP_CLIENT.newCall(request).execute();
        return new JSONObject(response.body().string()).getString("access_token");
    }
}