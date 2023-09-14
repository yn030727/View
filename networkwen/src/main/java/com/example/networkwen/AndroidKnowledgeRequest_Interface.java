package com.example.networkwen;

import static com.example.networkwen.MainActivity.getAccessToken;


import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface AndroidKnowledgeRequest_Interface {
    @POST("rpc/2.0/ai_custom/v1/wenxinworkshop/chat/completions")
    Call<AndroidKnowledgeResponse> getCall(@Body RequestBody requestBody , @Query("access_token") String accessToken);

}
