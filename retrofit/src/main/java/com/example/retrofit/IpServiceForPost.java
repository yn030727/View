package com.example.retrofit;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface IpServiceForPost {
    @POST("getIpInfo.php")
    Call<IpModel> getIpMsg(@Body IP ip);


//    @FormUrlEncoded
//    @POST("getIpInfo.php")
//    Call<IpModel> getIpMsg(@Field("ip") String first);
}
