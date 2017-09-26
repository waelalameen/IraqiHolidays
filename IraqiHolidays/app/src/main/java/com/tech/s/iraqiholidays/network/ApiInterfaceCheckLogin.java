package com.tech.s.iraqiholidays.network;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterfaceCheckLogin {
    @FormUrlEncoded
    @POST("check_logged_service.php")
    Call<String> postInfo(@Field("sid") String sid);
}
