package com.pma.running.dto;

import com.pma.running.model.ActivityRequest;
import lombok.Data;

import java.io.Serializable;




@Data
public class NotificationDto implements Serializable {

    private Long id;
    private NotificationType type;
    private String description;
    private String date;
    private String friend_username;
    private GroupActivityDto activityDto;


    public NotificationDto() {
    }

    public NotificationDto(Long id, NotificationType type, String description, String date) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.date = date;
    }

    public NotificationDto(Long id, NotificationType type, String description, String date, String friend_username) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.date = date;
        this.friend_username = friend_username;
    }

    public NotificationDto(Long id, NotificationType type, String description, String date, String friend_username, GroupActivityDto activityDto) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.date = date;
        this.friend_username = friend_username;
        this.activityDto = activityDto;
    }
}
