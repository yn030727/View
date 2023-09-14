package com.example.networkwen;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
                RequestBody requestBody = RequestBody.create(mediaType , "{\"messages\":[{\"role\":\"user\",\"content\":\"介绍下你自己\"}]}");
                try {
                    String access_token = getAccessToken();
//                    Request request = new Request.Builder()
//                            .url("https://aip.baidubce.com/rpc/2.0/ai_custom/v1/wenxinworkshop/chat/completions?access_token=" + getAccessToken())
//                            .method("POST" , requestBody)
//                            .addHeader("Content-Type", "application/json")
//                            .build();
                    //创建Retrofit构建器
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("https://aip.baidubce.com/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .client(HTTP_CLIENT)
                            .build();
                    //创建接口实例
                    AndroidKnowledgeRequest_Interface request = retrofit.create(AndroidKnowledgeRequest_Interface.class);
                    //封装请求
                    Call<AndroidKnowledgeResponse> call = request.getCall(requestBody , getAccessToken());
                    //发送网络请求
                    call.enqueue(new Callback<AndroidKnowledgeResponse>() {
                        @Override
                        public void onResponse(Call<AndroidKnowledgeResponse> call, retrofit2.Response<AndroidKnowledgeResponse> response) {
                            System.out.println(response.body().toString());
                        }

                        @Override
                        public void onFailure(Call<AndroidKnowledgeResponse> call, Throwable t) {
                            t.printStackTrace();
                            System.out.println("连接失败");
                        }
                    });



//                    HTTP_CLIENT.newCall(request).enqueue(new Callback() {
//                        @Override
//                        public void onFailure(Call call, IOException e) {
//                            e.printStackTrace();
//                            System.out.println("1111111111111111111111111" + "失败了");
//                        }
//
//                        @Override
//                        public void onResponse(Call call, Response response) throws IOException {
//                            System.out.println(response.body().string());
//                        }
//                    });
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