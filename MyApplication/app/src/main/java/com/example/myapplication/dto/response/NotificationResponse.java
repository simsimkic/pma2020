package com.example.myapplication.dto.response;

import com.example.myapplication.model.NotificationType;
import com.google.gson.annotations.SerializedName;

public class NotificationResponse {
    @SerializedName("id")
    private Long id;
    @SerializedName("description")
    private String description;
    @SerializedName("type")
    private NotificationType type;
    @SerializedName("friend_username")
    private String friend_username;

    public NotificationResponse() {
    }

    public NotificationResponse(Long id, String description, NotificationType type) {
        this.id = id;
        this.description = description;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public NotificationType getType() {
        return type;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }

    public String getFriend_username() {
        return friend_username;
    }

    public void setFriend_username(String friend_username) {
        this.friend_username = friend_username;
    }
}
