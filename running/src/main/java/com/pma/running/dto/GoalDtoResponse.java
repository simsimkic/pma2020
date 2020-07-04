package com.pma.running.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GoalDtoResponse {

    private Long id;
    private double duration;
    private double distance;
    private String end_time;
    private String timestamp;
    private String title;
    private Boolean archived;

}
