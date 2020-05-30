package com.pma.running.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Data
public class Activity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double duration;
    private double distance;
    private Date timestamp;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;




}
