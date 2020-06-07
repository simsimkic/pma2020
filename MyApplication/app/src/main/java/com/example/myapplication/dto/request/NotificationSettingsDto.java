package com.example.myapplication.dto.request;

import com.google.gson.annotations.SerializedName;

public class NotificationSettingsDto {

    @SerializedName("username")
    private String username;
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

    public NotificationSettingsDto() {
    }

    public NotificationSettingsDto(String username, boolean newComments, boolean newLikes,
                                   boolean friendshipRequest, boolean acceptedFriendship,
                                   boolean activityRequest, boolean acceptedActivity,
                                   boolean canceledActivity) {
        this.username = username;
        this.newComments = newComments;
        this.newLikes = newLikes;
        this.friendshipRequest = friendshipRequest;
        this.acceptedFriendship = acceptedFriendship;
        this.activityRequest = activityRequest;
        this.acceptedActivity = acceptedActivity;
        this.canceledActivity = canceledActivity;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isNewComments() {
        return newComments;
    }

    public void setNewComments(boolean newComments) {
        this.newComments = newComments;
    }

    public boolean isNewLikes() {
        return newLikes;
    }

    public void setNewLikes(boolean newLikes) {
        this.newLikes = newLikes;
    }

    public boolean isFriendshipRequest() {
        return friendshipRequest;
    }

    public void setFriendshipRequest(boolean friendshipRequest) {
        this.friendshipRequest = friendshipRequest;
    }

    public boolean isAcceptedFriendship() {
        return acceptedFriendship;
    }

    public void setAcceptedFriendship(boolean acceptedFriendship) {
        this.acceptedFriendship = acceptedFriendship;
    }

    public boolean isActivityRequest() {
        return activityRequest;
    }

    public void setActivityRequest(boolean activityRequest) {
        this.activityRequest = activityRequest;
    }

    public boolean isAcceptedActivity() {
        return acceptedActivity;
    }

    public void setAcceptedActivity(boolean acceptedActivity) {
        this.acceptedActivity = acceptedActivity;
    }

    public boolean isCanceledActivity() {
        return canceledActivity;
    }

    public void setCanceledActivity(boolean canceledActivity) {
        this.canceledActivity = canceledActivity;
    }
}
