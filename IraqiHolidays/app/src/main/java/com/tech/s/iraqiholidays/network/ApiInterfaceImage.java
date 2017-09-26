package com.tech.s.iraqiholidays.network;

import com.tech.s.iraqiholidays.model.PackInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterfaceImage {
    @FormUrlEncoded
    @POST("get_package_images_service.php")
    Call<List<PackInfo>> getInfo(@Field("pid") String pid);
}
