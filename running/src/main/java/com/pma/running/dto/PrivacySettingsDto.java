package com.pma.running.dto;

import lombok.Data;

@Data
public class PrivacySettingsDto {

    private String username;
    private String userInfoPrivacy;
    private String postPrivacy;
    private String goalPrivacy;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserInfoPrivacy() {
        return userInfoPrivacy;
    }

    public void setUserInfoPrivacy(String userInfoPrivacy) {
        this.userInfoPrivacy = userInfoPrivacy;
    }

    public String getPostPrivacy() {
        return postPrivacy;
    }

    public void setPostPrivacy(String postPrivacy) {
        this.postPrivacy = postPrivacy;
    }

    public String getGoalPrivacy() {
        return goalPrivacy;
    }

    public void setGoalPrivacy(String goalPrivacy) {
        this.goalPrivacy = goalPrivacy;
    }
}
