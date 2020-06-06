package com.example.myapplication.dto.request;

import com.google.gson.annotations.SerializedName;

public class PrivacySettingsDto {

    @SerializedName("username")
    private String username;
    @SerializedName("userInfoPrivacy")
    private String userInfoPrivacy;
    @SerializedName("postPrivacy")
    private String postPrivacy;
    @SerializedName("goalPrivacy")
    private String goalPrivacy;

    public PrivacySettingsDto() { }

    public PrivacySettingsDto(String username, String userInfoPrivacy, String postPrivacy, String goalPrivacy) {
        this.username = username;
        this.userInfoPrivacy = userInfoPrivacy;
        this.postPrivacy = postPrivacy;
        this.goalPrivacy = goalPrivacy;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserInfoPrivacy() {
        return userInfoPrivacy;
    }

    public String getPostPrivacy() {
        return postPrivacy;
    }

    public String getGoalPrivacy() {
        return goalPrivacy;
    }

    public void setUserInfoPrivacy(String userInfoPrivacy) {
        this.userInfoPrivacy = userInfoPrivacy;
    }

    public void setPostPrivacy(String postPrivacy) {
        this.postPrivacy = postPrivacy;
    }

    public void setGoalPrivacy(String goalPrivacy) {
        this.goalPrivacy = goalPrivacy;
    }
}
