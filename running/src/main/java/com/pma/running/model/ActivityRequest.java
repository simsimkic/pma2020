package com.pma.running.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Data
@Entity
@Table(name = "activity_requests")
public class ActivityRequest implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date timestamp;
    private String location;
    private boolean approved;

    @ManyToOne
    @JoinColumn(name = "requestee_id", nullable = false)
    private User activityRequestee;

    @ManyToOne
    @JoinColumn(name = "requestor_id", nullable = false)
    private User activityRequestor;
}
