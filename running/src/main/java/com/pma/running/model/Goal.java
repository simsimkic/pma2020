package com.pma.running.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;

@Data
@Entity
public class Goal implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private  String title;
    private double duration;
    private double distance;
    private LocalDateTime timestampe;
    private LocalDateTime endTime; //vremensko ogranicenje

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User  user;

}
