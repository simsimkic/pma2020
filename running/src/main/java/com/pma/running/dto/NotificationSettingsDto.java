package com.pma.running.dto;

import lombok.Data;

@Data
public class NotificationSettingsDto {

    private String username;
    private boolean newComments;
    private boolean newLikes;
    private boolean friendshipRequest;
    private boolean acceptedFriendship;
    private boolean activityRequest;
    private boolean acceptedActivity;
    private boolean canceledActivity;

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
