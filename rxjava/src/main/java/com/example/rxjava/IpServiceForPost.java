package com.example.rxjava;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IpServiceForPost {
    @GET("users/{user}")
    Single<User> getUser(@Path("user") String userId);
}
