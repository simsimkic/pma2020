package com.example.myapplication.interfaces;

import com.example.myapplication.dto.request.ActivityDto;
import com.example.myapplication.dto.request.EditUserRequest;
import com.example.myapplication.dto.request.NotificationSettingsDto;
import com.example.myapplication.dto.request.PrivacySettingsDto;
import com.example.myapplication.dto.request.ThemeSettingsDto;
import com.example.myapplication.dto.request.UserLogin;
import com.example.myapplication.dto.request.UserRequest;
import com.example.myapplication.dto.response.PostResponse;
import com.example.myapplication.dto.response.UserResponse;
import com.example.myapplication.dto.response.UserSettingsResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

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

    @Headers({
            "User-Agent: Mobile-Android",
            "Content-Type:application/json"
    })
    @PUT("settings/privacy")
    Call<UserSettingsResponse> updatePrivacySettings(@Body PrivacySettingsDto privacySettingsDto);

    @Headers({
            "User-Agent: Mobile-Android",
            "Content-Type:application/json"
    })
    @PUT("settings/notifications")
    Call<UserSettingsResponse> updateNotificationSettings(@Body NotificationSettingsDto notificationSettingsDto);

    @Headers({
            "User-Agent: Mobile-Android",
            "Content-Type:application/json"
    })
    @PUT("settings/theme")
    Call<UserSettingsResponse> updateThemeSettings(@Body ThemeSettingsDto themeSettingsDto);

    @Headers({
            "User-Agent: Mobile-Android",
            "Content-Type:application/json"
    })
    @GET("settings/{Username}")
    Call<UserSettingsResponse> getUserSettings(@Path("Username") String username);
}