package com.example.retrofit;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface UpLoadFileForPart {
    @Multipart
    @POST("user/photo")
    Call<User> updateUser(@Part MultipartBody.Part photo , @Part("description")RequestBody description);

}
