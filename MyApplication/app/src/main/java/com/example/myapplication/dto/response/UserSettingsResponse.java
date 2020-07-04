package com.example.myapplication.dto.response;

import com.google.gson.annotations.SerializedName;

public class UserSettingsResponse {

    @SerializedName("userInfoPrivacy")
    private String userInfoPrivacy;
    @SerializedName("postPrivacy")
    private String postPrivacy;
    @SerializedName("goalPrivacy")
    private String goalPrivacy;
    @SerializedName("newComments")
    private boolean newComments;
    @SerializedName("newLikes")
    private boolean newLikes;
    @SerializedName("friendshipRequest")
    private boolean friendshipRequest;
    @SerializedName("acceptedFriendship")
    private boolean acceptedFriendship;
    @SerializedName("activityRequest")
    private boolean activityRequest;
    @SerializedName("acceptedActivity")
    private boolean acceptedActivity;
    @SerializedName("canceledActivity")
    private boolean canceledActivity;
    @SerializedName("nightTheme")
    private boolean nightTheme;

    public String getUserInfoPrivacy() {
        return userInfoPrivacy;
    }

    public String getPostPrivacy() {
        return postPrivacy;
    }

    public String getGoalPrivacy() {
        return goalPrivacy;
    }

    public boolean isNewComments() {
        return newComments;
    }

    public boolean isNewLikes() {
        return newLikes;
    }

    public boolean isFriendshipRequest() {
        return friendshipRequest;
    }

    public boolean isAcceptedFriendship() {
        return acceptedFriendship;
    }

    public boolean isActivityRequest() {
        return activityRequest;
    }

    public boolean isAcceptedActivity() {
        return acceptedActivity;
    }

    public boolean isCanceledActivity() {
        return canceledActivity;
    }

    public boolean isNightTheme() {
        return nightTheme;
    }
}
