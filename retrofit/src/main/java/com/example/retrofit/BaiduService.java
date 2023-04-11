package com.example.retrofit;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface BaiduService {
    @GET("/")
    Call<ResponseBody> getHomePage();
}
