package com.pma.running.dto;

import lombok.Data;

import java.io.Serializable;




@Data
public class NotificationDto implements Serializable {

    private Long id;
    private NotificationType type;
    private String description;
    private String date;

    public NotificationDto() {
    }

    public NotificationDto(Long id, NotificationType type, String description, String date) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.date = date;
    }
}
