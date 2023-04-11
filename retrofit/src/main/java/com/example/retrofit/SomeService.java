package com.example.retrofit;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

public interface SomeService {
    @GET("some/endpoint")
    @Headers("Accept-Encoding: application/json")
    Call<ResponseBody> getCarType();
}
