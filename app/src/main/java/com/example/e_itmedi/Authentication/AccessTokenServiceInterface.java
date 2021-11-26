package com.example.e_itmedi.Authentication;

import com.example.e_itmedi.Login.LoginResponse;
import com.example.e_itmedi.Authentication.SignUp.ResponseData;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface AccessTokenServiceInterface {

    @POST("/api/v1/register")
    @FormUrlEncoded
    Call<ResponseData> registeruser(@Field("email")   String email,
                                    @Field("password") String password,
                                    @Field("c_password")  String cPassword,
                                    @Field("name") String name);

    @POST("/api/v1/login")
    @FormUrlEncoded
    Call<LoginResponse>logijUser(@Field("email")   String email,
                                 @Field("password") String password);



}