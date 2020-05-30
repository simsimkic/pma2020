package com.pma.running.model;

import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("activity")
@Data
public class ActivityRequestNotification extends Notification {
    @OneToOne
    @JoinColumn(name = "activity_id")
    private ActivityRequest activityRequest;
}
