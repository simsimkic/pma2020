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

}
