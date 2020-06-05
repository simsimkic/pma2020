package com.pma.running.dto;

import lombok.Data;

@Data
public class ActivityDto {

    private String description;
    private double duration;
    private double distance;
    private int steps;
    private String dateTime;
    private String username;

    public String getDescription() {
        return description;
    }

    public double getDuration() {
        return duration;
    }

    public double getDistance() {
        return distance;
    }

    public int getSteps() {
        return steps;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getUsername() {
        return username;
    }
}
