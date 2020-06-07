package com.example.myapplication.interfaces;

import com.example.myapplication.dto.request.ActivityDto;
import com.example.myapplication.dto.request.EditUserRequest;
import com.example.myapplication.dto.request.FriendshipRequest;
import com.example.myapplication.dto.request.UserLogin;
import com.example.myapplication.dto.request.UserRequest;
import com.example.myapplication.dto.response.FriendResponse;
import com.example.myapplication.dto.response.PostResponse;
import com.example.myapplication.dto.response.UserResponse;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

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
    @GET("friends/get/{username}")
    Call<ArrayList<FriendResponse>> getFriends(@Path("username") String username);

    @Headers({
            "User-Agent: Mobile-Android",
            "Content-Type:application/json"
    })
    @GET("friends/get/users/{username}")
    Call<ArrayList<FriendResponse>> getUsers(@Path("username") String username);

    @Headers({
            "User-Agent: Mobile-Android",
            "Content-Type:application/json"
    })
    @POST("friends/sendRequest")
        Call<ResponseBody> sendRequest(@Body FriendshipRequest fr);

    @Headers({
            "User-Agent: Mobile-Android",
            "Content-Type:application/json"
    })
    @POST("friends/acceptOrDeclineRequest")
    Call<ResponseBody> acceptOrDeclineFriendshipRequest(@Body FriendshipRequest fr);
}
