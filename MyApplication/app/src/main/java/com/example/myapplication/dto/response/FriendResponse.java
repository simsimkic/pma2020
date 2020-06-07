package com.example.myapplication.dto.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class FriendResponse implements Serializable {
    @SerializedName("username")
    private String username;
    @SerializedName("name")
    private String name;
    @SerializedName("friend")
    private int friend;

    public FriendResponse(String username, String name, int friend) {
        this.username = username;
        this.name = name;
        this.friend = friend;
    }

    public int getFriend() {
        return friend;
    }

    public void setFriend(int friend) {
        this.friend = friend;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
