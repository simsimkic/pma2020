package com.pma.running.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "activity_requests")
public class ActivityRequest implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDateTime timestamp;

    @Column
    private String location;

    @Enumerated(EnumType.STRING)
    private ActivityRequestStatus status;

    @ManyToOne
    @JoinColumn(name = "requestee_id", nullable = false)
    private User activityRequestee;

    @ManyToOne
    @JoinColumn(name = "requestor_id", nullable = false)
    private User activityRequestor;

    public ActivityRequest(){}

    public ActivityRequest(LocalDateTime timestamp, String location, ActivityRequestStatus status, User activityRequestee, User activityRequestor) {
        this.timestamp = timestamp;
        this.location = location;
        this.status = status;
        this.activityRequestee = activityRequestee;
        this.activityRequestor = activityRequestor;
    }

    public ActivityRequest(Long id, LocalDateTime timestamp, String location, ActivityRequestStatus status, User activityRequestee, User activityRequestor) {
        this.id = id;
        this.timestamp = timestamp;
        this.location = location;
        this.status = status;
        this.activityRequestee = activityRequestee;
        this.activityRequestor = activityRequestor;
    }
}
