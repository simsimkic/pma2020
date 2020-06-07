package com.pma.running.dto;

import lombok.Data;

@Data
public class UserSettingsResponseDto {

    private String userInfoPrivacy;
    private String postPrivacy;
    private String goalPrivacy;
    private boolean newComments;
    private boolean newLikes;
    private boolean friendshipRequest;
    private boolean acceptedFriendship;
    private boolean activityRequest;
    private boolean acceptedActivity;
    private boolean canceledActivity;
    private boolean nightTheme;

}
