package com.tech.s.iraqiholidays.network;

import com.tech.s.iraqiholidays.model.PackInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterfaceInfo {
    @GET("search_package_service.php")
    Call<List<PackInfo>> getInfo();
}
