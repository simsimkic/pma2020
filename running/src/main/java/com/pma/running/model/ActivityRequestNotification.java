package com.pma.running.model;

import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.sql.Date;

@Entity
@DiscriminatorValue("activity")
@Data
public class ActivityRequestNotification extends Notification {
    @OneToOne
    @JoinColumn(name = "activity_id")
    private ActivityRequest activityRequest;

    public ActivityRequestNotification(Date timestamp, NotificationType notificationType, String description, ActivityRequest activityRequest) {
        super(timestamp, notificationType, description);
        this.activityRequest = activityRequest;
    }

    public ActivityRequestNotification(Long id, Date timestamp, NotificationType notificationType, String description, ActivityRequest activityRequest) {
        super(id, timestamp, notificationType, description);
        this.activityRequest = activityRequest;
    }
}
