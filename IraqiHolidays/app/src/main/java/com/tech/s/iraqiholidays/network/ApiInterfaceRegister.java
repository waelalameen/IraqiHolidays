package com.tech.s.iraqiholidays.network;

import com.tech.s.iraqiholidays.model.RegisterInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterfaceRegister {
    @FormUrlEncoded
    @POST("client_register_service.php")
    Call<List<RegisterInfo>> postInfo(@Field("first_name") String firstName, @Field("last_name") String lastName, @Field("email") String email,
                                      @Field("password") String password, @Field("agree") int agree, @Field("is_company") int isCompany,
                                      @Field("gender") int gender, @Field("phone") int phone, @Field("result") String result);
}
