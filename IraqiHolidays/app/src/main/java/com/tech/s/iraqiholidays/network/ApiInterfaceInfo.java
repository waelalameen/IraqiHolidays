package com.tech.s.iraqiholidays.network;

import com.tech.s.iraqiholidays.model.PackInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.POST;

public interface ApiInterfaceInfo {
    @POST("search_package_service.php")
    Call<List<PackInfo>> getInfo();
}
