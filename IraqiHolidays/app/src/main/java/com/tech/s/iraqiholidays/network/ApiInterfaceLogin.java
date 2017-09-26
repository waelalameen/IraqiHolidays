package com.tech.s.iraqiholidays.network;

import com.tech.s.iraqiholidays.model.LoginInfo;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterfaceLogin {
    @FormUrlEncoded
    @POST("login_service.php")
    Call<LoginInfo> postInfo(@Field("email") String email, @Field("password") String password);
}
