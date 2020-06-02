package com.example.myapplication.interfaces;

import com.example.myapplication.dto.request.UserRequest;
import com.example.myapplication.dto.response.UserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("registerUser")
    Call<UserResponse> registerUser(@Body UserRequest userinfo);

}
