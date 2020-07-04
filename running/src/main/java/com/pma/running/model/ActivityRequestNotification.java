package com.pma.running.model;

import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("activity")
@Data
public class ActivityRequestNotification extends Notification {
    @OneToOne
    @JoinColumn(name = "activity_id")
    private ActivityRequest activityRequest;

    public ActivityRequestNotification(){}


    public ActivityRequestNotification(ActivityRequest activityRequest) {
        this.activityRequest = activityRequest;
    }

    public ActivityRequestNotification(LocalDateTime timestamp, NotificationType notificationType, String description, User user, ActivityRequest activityRequest) {
        super(timestamp, notificationType, description, user);
        this.activityRequest = activityRequest;
    }

    public ActivityRequestNotification(Long id, LocalDateTime timestamp, NotificationType notificationType, String description, User user, ActivityRequest activityRequest) {
        super(id, timestamp, notificationType, description, user);
        this.activityRequest = activityRequest;
    }
}
