package com.tech.s.iraqiholidays.network;

import com.tech.s.iraqiholidays.model.PackInfo;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterfacePackageDetails {
    @FormUrlEncoded
    @POST("get_package_information.php")
    Call<PackInfo> getInfo(@Field("pid") String pid);
}
