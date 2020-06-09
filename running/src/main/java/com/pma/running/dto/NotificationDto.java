package com.pma.running.dto;

import java.io.Serializable;




public class NotificationDto implements Serializable {

    private Long id;
    private NotificationType type;
    private String description;

    public NotificationDto() {
    }

    public NotificationDto(Long id, NotificationType type, String description) {
        this.id = id;
        this.type = type;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public NotificationType getType() {
        return type;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
