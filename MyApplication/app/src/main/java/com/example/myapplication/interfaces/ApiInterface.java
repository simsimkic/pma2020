package com.example.myapplication.interfaces;

import com.example.myapplication.dto.request.ActivityDto;
import com.example.myapplication.dto.request.EditUserRequest;
import com.example.myapplication.dto.request.UserLogin;
import com.example.myapplication.dto.request.UserRequest;
import com.example.myapplication.dto.response.PostResponse;
import com.example.myapplication.dto.response.UserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterface {

    @Headers({
            "User-Agent: Mobile-Android",
            "Content-Type:application/json"
    })
    @POST("registerUser")
    Call<UserResponse> registerUser(@Body UserRequest userinfo);


    @Headers({
            "User-Agent: Mobile-Android",
            "Content-Type:application/json"
    })
    @POST("loginUser")
    Call<UserResponse> loginUser(@Body UserLogin userLogin);

    @Headers({
            "User-Agent: Mobile-Android",
            "Content-Type:application/json"
    })
    @POST("editUser")
    Call<UserResponse> editUser(@Body EditUserRequest userLogin);

    @Headers({
            "User-Agent: Mobile-Android",
            "Content-Type:application/json"
    })
    @POST("activities/share")
    Call<PostResponse> shareActivity(@Body ActivityDto activityDto);

}
