package com.example.myapplication.model;

import java.time.Duration;

public class Goal {
    private String name;
    private Double distance;
    private Double duration;
    private Boolean archived;

    public Goal(String name, Double distance, Double duration, Boolean archived) {
        this.name = name;
        this.distance = distance;
        this.duration = duration;
        this.archived = archived;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }

    public Boolean getArchived() {
        return archived;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
    }
}
