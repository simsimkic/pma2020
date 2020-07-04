package com.example.myapplication.model;

import java.time.Duration;

public class Goal {

    private Long id;
    private String name;
    private Double distance;
    private Double duration;
    private Boolean archived;
    private String end_time;

    public Goal(Long id, String name, Double distance, Double duration, Boolean archived, String end_time) {
        this.id= id;
        this.name = name;
        this.distance = distance;
        this.duration = duration;
        this.archived = archived;
        this.end_time = end_time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }
}
